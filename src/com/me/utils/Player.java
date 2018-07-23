package com.me.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;







import javax.sound.sampled.*;
import javax.swing.JSlider;
import javax.swing.JTable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

import com.me.bean.SongBean;
import com.me.dao.LocalMusicDao;
import com.me.dao.RecentListDao;




/*"duration"	
"author"	
"title"	
"copyright"	
"date"	
"comment"*/
public class Player extends Thread{
	public int Minimum=0,Maximum=100,nowValue=0;
	public Player(String path) {
		super();
		this.path=path;
	}

	LocalMusicDao lDao = new LocalMusicDao();

	private Player p;
	private String path;
	public int next=0;

	private long time = 0;
	// some lock somewhere...
	Object lock = new Object();
	// some paused variable   暂停 继续
	private boolean paused = false;


	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	// private JSlider jSliderPlayProgress;//播放进度条


	// some paused variable   开始 结束
	private boolean over = false;

	//是否自动播放下一曲
	private boolean isNext=true;


	AudioInputStream din = null;
	SourceDataLine line=null;

	private FloatControl volume = null;

	//	private JSlider jSliderVolume; 

	//	public JSlider getjSliderVolume() {
	//		return jSliderVolume;
	//	}

	//	public void setjSliderVolume(JSlider jSliderVolume) {
	//		this.jSliderVolume = jSliderVolume;
	//	
	//		
	//	}


	public FloatControl getVolume(){
		return volume;
	}

	public void setVolume()
	{
		if(line!=null)
		{
			if(line.isControlSupported(FloatControl.Type.MASTER_GAIN))
			{
				//jSliderVolume.setEnabled(true);
				volume= (FloatControl)line.getControl( FloatControl.Type.MASTER_GAIN );
				Minimum=(int)volume.getMinimum();
				Maximum=(int)volume.getMaximum();
				nowValue=(int)(volume.getMinimum()+(4*(volume.getMaximum()-volume.getMinimum()))/5);
				volume.setValue((float)(volume.getMinimum()+(4*(volume.getMaximum()-volume.getMinimum()))/5));
			}
			//System.out.println("各时间："+Minimum+":"+Maximum+":"+volume+":"+nowValue);  //   音量键
		}
		else
		{
			volume=null;
			//	jSliderVolume.setEnabled(false);
			System.out.println("2");
		}	
	}


	public  void run(){

		AudioInputStream in=null;

		try {

			File file = new File(path);

			//播放不了的歌曲，直接下一首,并且在音乐列表中删除
			try {
				in = AudioSystem.getAudioInputStream(file);
			} catch (Exception e) {
				//删除有点小问题
				//	MusicList.getList().remove(music.getId());

				//System.out.println(music.getId()+"音乐id号"+music.getPath());

				//ViewList.getList().get(0).getJt().setModel(new Model());
				System.out.println("该歌曲不能播放");

				//nextmusic();
			}

			AudioFormat baseFormat = in.getFormat();
			AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
					baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
					false);
			if(baseFormat.getEncoding()==AudioFormat.Encoding.PCM_UNSIGNED || baseFormat.getEncoding()==AudioFormat.Encoding.ULAW ||
					baseFormat.getEncoding()==AudioFormat.Encoding.ALAW || baseFormat.getEncoding()==AudioFormat.Encoding.PCM_SIGNED){
				time=(file.length()*8000000)/((int)(decodedFormat.getSampleRate()*baseFormat.getSampleSizeInBits()));
				System.out.println("时间"+time);//52989353
			}else{
				int bitrate=0;
				if(baseFormat.properties().get("bitrate")!=null){
					//取得播放速度(单位位每秒)
					bitrate=(int)((Integer)(baseFormat.properties().get("bitrate")));
					if(bitrate!=0)
						time=(file.length()*8000000)/bitrate;
					System.out.println("时间2"+time);
				}

			}


			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open();
			setVolume();
			//jSliderPlayProgress.setMaximum((int)time);
			//jSliderPlayProgress.setValue(0);
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					DataDic.mainPage.setMaxProgressBar((int)time);
				}
			});
			if(line!=null){
				line.open(decodedFormat);
				byte[] data = new byte[4096];
				int nBytesRead;

				synchronized (lock) {
					while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
						//System.out.println(line.getMicrosecondPosition());
						while (paused) {
							if(line.isRunning()) {
								line.stop();
								System.out.println("暂停");
							}
							try {
								lock.wait();

								System.out.println("等待");
							}
							catch(InterruptedException e) {
							}
						}
						if(!line.isRunning()&&!over) {
							System.out.println("开始播放");
							line.start();

						}

						if (over&&line.isRunning()) {
							System.out.println("停止播放");
							//	jSliderPlayProgress.setValue(0);
							isNext=false;
							line.drain();
							line.stop();
							line.close();
						}
						Display.getDefault().asyncExec(new Runnable(){
							public void run(){
								DataDic.mainPage.setProgressBar((int)line.getMicrosecondPosition());
							}
						});

						//jSliderPlayProgress.setValue((int)line.getMicrosecondPosition());
						line.write(data, 0, nBytesRead);
					}

					System.out.println("播放完了");
					//根据播放模式选择下一首歌
					Display.getDefault().asyncExec(new Runnable(){
						public void run(){
							nextmusic();
						}
					});

				}

			}
			/*	if(line != null) {
				line.open(decodedFormat);
				byte[] data = new byte[4096];
				// Start
				line.start();
				int nBytesRead;
				while ((nBytesRead = din.read(data, 0, data.length)) != -1) {	
					line.write(data, 0, nBytesRead);
				}
				// Stop
				line.drain();
				line.stop();
				line.close();
				din.close();
			}*/

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(din != null) {
				try { din.close(); } catch(IOException e) { }
			}
		}
	}

	public void nextmusic() {
		if(ThreadMannager.lrcThread!=null){
			ThreadMannager.lrcThread.stop();
		}
		int j =0;    //下一首歌该有的下标
		int tt=0;
		int index=0;
		TableItem[] t =DataDic.table.getItems(); //获取所有item
		for(int i=0,len =t.length;i<len;i++ ){ //遍历item
			if(t[i].getText(1).trim().equals(DataDic.sBean.getMname()) || t[i].getText(2).trim().equals(DataDic.sBean.getMname())){
				if(t[i].getText(2).trim().equals(DataDic.sBean.getMname())){
					index = 1;
				}
				//  在这里得到下一首歌
				j=i;
				if(DataDic.mainPage.nowstyle ==0){//循环
					if(next!=-1){
						if(i!=len-1){
							j++;System.out.println(1+"   &&&&  "+DataDic.sBean.getMname());
						} else {
							j=0;System.out.println(2);
						}
					} else{
						if(i!=0){
							j--;System.out.println(3);
						} else {
							j=len-1;System.out.println(4);
						}
					}
				} else if(DataDic.mainPage.nowstyle ==1){//单曲
					if(next==1){
						if(i!=len-1){
							j++;System.out.println(5);
						} else {
							j=0;System.out.println(6);
						}
					} else if(next==-1){
						if(i!=0){
							j--;System.out.println(7);
						} else {
							j=len-1;System.out.println(8);
						}
					} else {
						j=i;System.out.println(9);
					}
				} else if(DataDic.mainPage.nowstyle ==2){//随机
					j=((int) (Math.random() * 100))%len;System.out.println(10);
				} else {//；列表
					if(i!=-1){
						if(i!=len-1){
							j++;System.out.println(11);
						} else {
							return;
						}
					} else{
						if(i!=0){
							j--;System.out.println(12);
						} else {
							j=0;System.out.println(13);
						}
					}
				}

				String time = t[j].getText(4+index);
				tt = ToolUtil.timeToInt(time);
				System.out.println(i+"    *************        "+j);
			}
		}
		// item   就是t[j]
		//System.out.println(item.getText(1)+" "+item.getText(4));
		List<String> list = lDao.findOne(t[j].getText(1+index), String.valueOf(tt), DataDic.localPath);
		if (list!=null && list.size()>0) {
			System.out.println(list.get(1));//播放路径

			//将当前歌曲信息保存
			DataDic.sBean = new SongBean(t[j].getText(1+index),
					t[j].getText(2+index), t[j].getText(3+index), tt,"", list.get(1));
			DataDic.mainPage.lblNewLabel_10.setText( DataDic.sBean.getMname()); //显示到主界面
			DataDic.mainPage.lblNewLabel_11.setText( DataDic.sBean.getSinger());
			DataDic.playing.initUI();//加载歌词，正在播放界面
			RecentListDao rDao = new RecentListDao(); 
			rDao.insertTo(list.get(0));//更新最近播放列表
			this.stopplay();
			for(Player play : DataDic.playerList){
				play.stop();
			}
			p=new Player(list.get(1));
			DataDic.playerList.add(p);
			ThreadMannager.player=p;

			p.start();
		}

	}



	//开始
	public void startplay(){
		over=false;
	}


	//停止

	public void stopplay(){
		over=true;
	}


	// 暂停
	public void userPressedPause() {
		paused = true;
	}

	//继续
	public void userPressedPlay() {
		synchronized(lock) {
			paused = false;
			lock.notifyAll();
		}

	}

	public void Pause(){
		if (paused) {
			synchronized(lock) {
				paused = false;
				lock.notifyAll();
			}
		}else{
			paused = true;
		}

	}




}