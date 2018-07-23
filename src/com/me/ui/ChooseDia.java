package com.me.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import com.me.dao.LocalMusicDao;
import com.me.utils.DataDic;
import com.me.utils.RegisterUtils;
import com.me.utils.ThreadMannager;

import org.eclipse.swt.layout.FillLayout;

public class ChooseDia extends Dialog {

	protected Object result;
	protected Shell shell;
	private Display display;
	private Table tables;
	LocalMusicDao lDao = new LocalMusicDao();
	String[] mp3Info;
	float mp3Length=0;
	private ArrayList<String> mList = new ArrayList<String>() ;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChooseDia(Shell parent, int style) {
		super(parent, SWT.DIALOG_TRIM|SWT.APPLICATION_MODAL);

	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(306, 353);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(ChooseDia.class, "/images2/bgcolor_2.png"));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(ChooseDia.class, "/images2/bgcolor_2.png"));
		lblNewLabel.setFont(new Font(display,"微软雅黑",13,SWT.BORDER));
		lblNewLabel.setBounds(10, 14, 218, 29);
		lblNewLabel.setText("选择本地音乐文件夹");

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(ChooseDia.class, "/images2/bgcolor_2.png"));

		tables = new Table(composite_1, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		tables.setBounds(0, 0, 300, 227);
		tables.setHeaderVisible(true);
		tables.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(tables, SWT.NONE);
		tblclmnNewColumn.setWidth(296);
		tblclmnNewColumn.setText("                               目录");

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(ChooseDia.class, "/images2/bgcolor_1.png"));

		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);

		lblNewLabel_2.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel_2.setBackgroundImage(SWTResourceManager.getImage(ChooseDia.class, "/images2/confirm.png"));
		lblNewLabel_2.setBounds(48, 13, 80, 30);

		Label lblNewLabel_3 = new Label(composite_2, SWT.NONE);

		lblNewLabel_3.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel_3.setBackgroundImage(SWTResourceManager.getImage(ChooseDia.class, "/images2/add_folders.png"));
		lblNewLabel_3.setBounds(172, 13, 82, 30);
		sashForm.setWeights(new int[] {53, 253, 48});

		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				System.out.println("fdfd");
				shell.setVisible(false);
			}
		});
		//确认按钮
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				DataDic.localPath = null;
				TableItem[] t = tables.getItems();
				for(int i=0,len =t.length;i<len;i++ ){
					if (t[i].getChecked()) {
						mList.add(t[i].getText().trim()); 
						//System.out.println(t[i].getText().trim());
					}
				}
				DataDic.localPath = new String[mList.size()];
				try {
					RegisterUtils.clearValue();
				} catch (BackingStoreException e1) {
					e1.printStackTrace();
				} //清空注册表
				for (int i=0,len=mList.size();i<len;i++) {
					DataDic.localPath[i]=mList.get(i); 
					//System.out.println(DataDic.localPath[i]);
					RegisterUtils.writeValue(mList.get(i), mList.get(i));
				}
				mList.clear();
				//匹配是一个耗时操作
				piPei();

			}

			public void piPei() {
				mList.clear();
				//匹配是一个耗时操作
				new Thread(new Runnable() {
					String str=null;
					File file = null;
					public void run() {
						List<String> li= null;

						for(int i=0,len =DataDic.localPath.length;i<len;i++ ){
							//System.out.println(DataDic.localPath[i]);
							searchMusic(DataDic.localPath[i]); //搜索歌曲
							//查询数据库中的结果进
						}
						li= lDao.findSeg(DataDic.localPath);
						int lens = mList.size()>li.size()?mList.size():li.size();
						int j=0;
						while(j<lens) {
							//数据库不存在这首歌
							if (j<mList.size() && !li.contains(mList.get(j))) {
								str = mList.get(j);
								//插入
								file = new File(str);
								mp3Info = readMp3Info(file);
								if (mp3Info!=null) {
									mp3Length = file.length();
									mp3Length/=1024.0f;
									lDao.insertTo(file.getName(), mp3Info[0],
											mp3Info[1],mp3Info[2], 
											String.valueOf((float)mp3Length/1024.0f), file.getAbsolutePath());	
								}
							}
							//本地不存在数据库中的歌曲
							if (j<li.size() && !mList.contains(li.get(j))) {
								//不存在这首歌，说明歌曲已经删除，也要从数据库中删除
								lDao.delete(li.get(j));
							}
							j++;
						}
					}
				}).start();;

			}
		});

		//添加文件夹
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				DirectoryDialog fd = new DirectoryDialog(shell);
				String path =fd.open();
				if (path!=null) {
					LocalMusicDao lDao = new LocalMusicDao();
					int result = lDao.insertLoPath(path);
					if (result>0) {
						MessageDialog.openInformation(shell, "提示", "添加成功！");
						initUI();
					}else{
						MessageDialog.openInformation(shell, "提示", "添加失败，已经存在！");
					}
				}


			}
		});

		initUI();
	}

	public void initUI() {
		tables.removeAll();

		List<Map<String, String>> list = lDao.findPath();
		TableItem tItem = null;
		if (list!=null && list.size()>0) {
			for (Map<String, String> map:list) {
				tItem = new TableItem(tables, SWT.RADIO);
				tItem.setText(map.get("filepath"));
			}
		}else{
			tItem = new TableItem(tables, SWT.CHECK);
			tItem.setText(2,"没有添加文件夹");
		}
	}


	/**
	 * 递归搜索本地音乐并将数据插入到数据库
	 */
	public void searchMusic(String path){
		File[] files = (new File(path)).listFiles();
		for (File file:files) {
			if (file.isDirectory()) {
				searchMusic(file.getAbsolutePath());
			}else{
				if (isMusic(file)) { //如果是音乐
					mList.add(file.getAbsolutePath());
				}
			}

		}
	}

	public boolean isMusic(File file){
		String str = file.getAbsolutePath();
		return str.endsWith(".mp3");
	}



	public String[] readMp3Info(File file){
		//	MP3File f;
		//	MP3AudioHeader header = null;
		//	try {
		//		f = new MP3File(file.getAbsolutePath());
		//		 header= (MP3AudioHeader)f.getAudioHeader(); //获得头部信息
		//		//System.out.println(header.getTrackLength());
		//	//	AbstractID3v2Tag id3v2tag=  f.getID3v2Tag();
		//		String singer=f.getID3v2Tag().frameMap.get("TPE1").toString();
		//		String coll=f.getID3v2Tag().frameMap.get("TALB").toString(); 
		//		//System.out.println("歌名："+songName.substring(6, songName.length()-3));  
		//		//System.out.println("歌手:"+singer.substring(6,singer.length()-3));  
		//		//System.out.println("专辑名："+author.substring(6,author.length()-3));  
		//
		//		return new String[]{singer.substring(6, singer.length()-3),
		//				coll.substring(6, coll.length()-3),String.valueOf(header.getTrackLength()),
		//				};
		//	} catch (Exception e) {
		//		String time;
		//		if (header==null) {
		//			time="10";
		//		}else{
		//			time= String.valueOf(header.getTrackLength());
		//		}
		//		return new String[]{"不详","不详",time};

		//	}
		byte[] buf = new byte[128];//初始化标签信息的byte数组

		RandomAccessFile raf;
		MP3AudioHeader header = null;
		try {
			raf = new RandomAccessFile(file.getAbsolutePath(), "r");//随机读写方式打开MP3文件
			raf.seek(raf.length() - 128);//移动到文件MP3末尾
			raf.read(buf);//读取标签信息
			raf.close();//关闭文件
			if(buf.length != 128){//数据长度是否合法
			}
			if(!"TAG".equalsIgnoreCase(new String(buf,0,3))){//标签头是否存在
			}
			String Artist = new String(buf,33,30,"GBK").trim();//歌手名字
			String Album = new String(buf,63,30,"GBK").trim();//专辑名称
			header= (MP3AudioHeader)new MP3File(file.getAbsolutePath()).getAudioHeader(); //获得头部信息
			return new String[]{Artist,Album,String.valueOf(header.getTrackLength())};
		}  catch (Exception e) {
			try {
				header= (MP3AudioHeader)new MP3File(file.getAbsolutePath()).getAudioHeader();
			} catch (Exception e1) {
			} //获得头部信息
			String time;
			if (header==null) {
				time="0";
			}else{
				time= String.valueOf(header.getTrackLength());
			}
			return  new String[]{"不详","不详",time};
		}


	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}


