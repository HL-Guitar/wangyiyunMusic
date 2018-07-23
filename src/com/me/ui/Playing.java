package com.me.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.eclipse.swt.events.MouseTrackAdapter;

import com.me.dao.SongSheetDao;
import com.me.utils.DataDic;
import com.me.utils.LrcAnalyze;
import com.me.utils.LrcAnalyze.LrcData;
import com.me.utils.ThreadMannager;

import java.util.Timer;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Playing extends Composite {
	private StyledText text;
	private Label mnameLabel;
	private Label collLable;
	private Label singerLabel;
	private Label label_2;
	private Label label_3;
	private Timer timer = new Timer();
	public List<LrcData> list;
	private Composite composite;
	StyleRange styleRange =null;
	String str=null;
	String str2 = null;
	int index=0;
	Color c1 = new Color(getDisplay(), 220, 20, 60);
	Color c2 = new Color(getDisplay(), 0, 0, 0);
	private String lrcPath=null;
	private float goTime=0;
	public LinkedHashMap<Float, String> lrcMap;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Playing(Composite parent, int style) {
		super(parent, SWT.NONE);
		setBackgroundMode(SWT.INHERIT_FORCE);
		AddToCollection addToCollection = new AddToCollection(this, SWT.BORDER | SWT.H_SCROLL );
		addToCollection.setVisible(false);
		addToCollection.setBounds(119,107, 470, 397);

		mnameLabel = new Label(this, SWT.NORMAL);
		mnameLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		mnameLabel.setBounds(475, 38, 440, 36);
		mnameLabel.setText("快快播放歌曲吧");

		collLable = new Label(this, SWT.NONE);
		collLable.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		collLable.setBounds(475, 90, 158, 23);
		collLable.setText("专辑：未知");

		singerLabel = new Label(this, SWT.NONE);
		singerLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		singerLabel.setBounds(667, 90, 197, 23);
		singerLabel.setText("歌手：未知");

		label_2 = new Label(this, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_2.setBounds(870, 92, 146, 23);
		label_2.setText("来源：本地音乐");

		label_3 = new Label(this, SWT.NONE);

		label_3.setImage(SWTResourceManager.getImage(Playing.class, "/image/min_play.png"));
		label_3.setBounds(943, 38, 37, 36);

		text = new StyledText(this, SWT.READ_ONLY|SWT.CENTER|SWT.V_SCROLL|SWT.H_SCROLL);
		//text.setAlignment(SWT.CENTER);
		text.setForeground(SWTResourceManager.getColor(0, 0, 0));
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		text.setBounds(488, 154, 544, 571);


		Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setImage(SWTResourceManager.getImage(Playing.class, "/image/btn_like.png"));
		btnNewButton.setBounds(125, 458, 87, 36);

		Button button = new Button(this, SWT.NONE);

		button.setImage(SWTResourceManager.getImage(Playing.class, "/image/btn_collect.png"));
		button.setBounds(239, 458, 87, 36);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(Playing.class, "/image/play_img.png"));

		label_4.setBounds(24, 38, 395, 401);
		setBackgroundImage(SWTResourceManager.getImage(Playing.class, "/images2/bac_n.jpg"));
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setToolTipText("后退0.5S");
	
		lblNewLabel.setImage(SWTResourceManager.getImage(Playing.class, "/images2/up.png"));
	
		lblNewLabel.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel.setBounds(460, 197, 22, 22);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setToolTipText("前进0.5S");
		
		lblNewLabel_1.setImage(SWTResourceManager.getImage(Playing.class, "/images2/down.png"));
	
		lblNewLabel_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel_1.setBounds(460, 225, 22, 22);


		//rotate(filePath, filePath, 60);
     
		//添加音乐到我喜欢
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				SongSheetDao mDao = new SongSheetDao();
				//将数据插入到songsheet表中
				int result = mDao.insertTo("我喜欢的音乐", DataDic.sBean.getMname(), DataDic.sBean.getSinger(),
						DataDic.sBean.getCollection(), String.valueOf(DataDic.sBean.getMtime()), 
						DataDic.sBean.getFilepath());
				if (result>0) {
					System.out.println("添加成功");
				}
			}
		});
		composite = this;
		//最小化
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
			//	composite.setVisible(false);
				DataDic.stackLayout.topControl=DataDic.localMusic;
				parent.layout();
			}
		});
		//添加到收藏

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addToCollection.showData();//刷新页面
				addToCollection.setVisible(true); //显示添加收藏窗口
				//   System.out.println("显示");
			}
		});
		lblNewLabel.addMouseTrackListener(new MouseTrackAdapter() {
			// 前进按钮
			
			public void mouseExit(MouseEvent e) {
				lblNewLabel.setImage(SWTResourceManager.getImage(Playing.class, "/images2/up.png"));
			}
			
			public void mouseHover(MouseEvent e) {
				lblNewLabel.setImage(SWTResourceManager.getImage(Playing.class, "/images2/up_1.png"));
			}
		});
		lblNewLabel_1.addMouseTrackListener(new MouseTrackAdapter() {
			// 后退按钮
			
			public void mouseExit(MouseEvent e) {
				lblNewLabel_1.setImage(SWTResourceManager.getImage(Playing.class, "/images2/down.png"));
			}
			
			public void mouseHover(MouseEvent e) {
				lblNewLabel_1.setImage(SWTResourceManager.getImage(Playing.class, "/images2/down_1.png"));
			}
		});
		//后退,控制歌词的延迟
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (lrcPath!=null) {
				LrcAnalyze lrcAnalyze = new LrcAnalyze(lrcPath,goTime+=0.5f);
				lrcMap = lrcAnalyze.getLrcMap();
				System.out.println("后退"+goTime);
				}
			}
		});
		//前进
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (lrcPath!=null) {
					//ThreadMannager.lrcThread.notify();;
					LrcAnalyze lrcAnalyze = new LrcAnalyze(lrcPath,goTime-=0.5f);
					lrcMap = lrcAnalyze.getLrcMap();
					System.out.println("前进"+goTime);
					//ThreadMannager.lrcThread.notify();
//					for (int i = 0; i < list.size(); i++) {
//						System.out.println(list.get(i).TimeMs+"  "+list.get(i).LrcLine);
//					}
				}
			}
		});
	}
	/**
	 * 初始化界面信息
	 */
	public void initUI() {
		//DataDic.sBean中存放了当前播放歌曲的全部信息，将当前歌曲信息显示到界面
		mnameLabel.setText(DataDic.sBean.getMname()); 
		singerLabel.setText("歌手："+DataDic.sBean.getSinger());
		collLable.setText("专辑："+DataDic.sBean.getCollection());
		if (ThreadMannager.lrcThread!=null) {//歌曲已切换，当前歌词线程关闭
			ThreadMannager.lrcThread.stop();
			lrcPath=null;
			goTime=0;
		}
		text.setText("");//清空歌词显示
		String lrc = DataDic.sBean.getMname();
		lrc = lrc.substring(0,lrc.lastIndexOf("."))+".lrc";
	    lrcPath =DataDic.sBean.getFilepath();
		lrcPath = lrcPath.substring(0,lrcPath.lastIndexOf("\\"));
		lrcPath+="\\"+lrc;  //str为歌词路径
	
		//加载lrc歌词
		if (new File(lrcPath).exists()) {
			LrcAnalyze lrcAnalyze = new LrcAnalyze(lrcPath,0);
			lrcMap = lrcAnalyze.getLrcMap();
			  for (Float key : lrcMap.keySet()) {
				  text.append(lrcMap.get(key)+"\n");
			   }
 
			//歌词同步线程
			ThreadMannager.lrcThread =new Thread (new Runnable() {
				float prossSe = 0; //当前秒数，毫秒级，一位小数
				BigDecimal   b  =   null;  

				public void run() {
					while (true) {
						b=new BigDecimal(prossSe);
						prossSe = b.setScale(1,  BigDecimal.ROUND_HALF_UP).floatValue();
							if (lrcMap.containsKey(prossSe)){ //唱到这一句了，显示出来
								System.out.println(prossSe+"  +"+prossSe+"  "+lrcMap.get(prossSe));
								Display.getDefault().asyncExec(new Runnable() {//子线程刷新界面
									public void run() {
										textChange(prossSe);
									}
								});
							}
						
						try {
							Thread.sleep(100);
							prossSe+=0.1f; //线程每休眠0.1s当前秒数加0.1s
//                            if (prossSe%4==0) {
//                            	prossSe+=0.1f;
//							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			ThreadMannager.lrcThread.start(); //开启线程

		}else {
			text.setText("未找到歌词文件");
		}

	}

	public void  textChange(float key) {
		try {
//			BigDecimal b=new BigDecimal(key);
//			key = b.setScale(1,  BigDecimal.ROUND_HALF_UP).floatValue();
			str2 = lrcMap.get(key); 
			if (!("\r".equals(str2) || str2==null)) {
				str = text.getText();
				//System.out.println(key+str2);
				styleRange = new StyleRange();
				if (str.indexOf(str2)<index) {//说明歌词不对，查找正确的
					index=str.indexOf(str2,index);
				}
				index=str.indexOf(str2,index);
				styleRange.fontStyle=SWT.BOLD;
			//	System.out.println("index"+index);
				styleRange.start = index;
				styleRange.length = str2.length();
				styleRange.foreground =c1;
				text.setStyleRange(styleRange);
				//System.out.println(str2);
				textChangePre(index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将原来的歌词换为黑色
	 * @param i
	 */
	public void  textChangePre(int ind) {
		
		styleRange = new StyleRange();
		styleRange.start = 0;
		styleRange.length = ind;
		styleRange.foreground =c2;
		text.setStyleRange(styleRange);
	}

	// 调整图片大小
	private Image scaledImageSize(Composite lbl,InputStream is) {
		ImageData id = new ImageData(is);
		int width, height;
		if (id.width > lbl.getBounds().width) {
			width = lbl.getBounds().width;
		} else{
			width = id.width;
		}
		if (id.height > lbl.getBounds().height) {
			height = lbl.getBounds().height;
		} else{
			height = id.height;
		}
		id = id.scaledTo(width, height);

		return new Image(null, id);
	}


	//旋转图片
//	public boolean rotate(String imagePath, String newPath, double degree) {
//		boolean flag = false;
//		try {
//
//			// 1.将角度转换到0-360度之间
//
//			degree = degree % 360;
//
//			if (degree <= 0) {
//
//				degree = 360 + degree;
//
//			}
//
//			IMOperation op = new IMOperation();
//
//			op.addImage(imagePath);
//
//			op.rotate(degree);
//
//			op.addImage(newPath);
//
//			ConvertCmd cmd = new ConvertCmd(true);
//
//			cmd.run(op);
//
//			flag = true;
//
//		} catch (Exception e) {
//
//			flag = false;
//			e.printStackTrace();
//
//			System.out.println("图片旋转失败!");
//		}
//		return flag;
//	}



	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
