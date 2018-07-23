package com.me.ui;

import mypoject.BgColor;
import mypoject.Bgimage;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.chart.PieChart.Data;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;



import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.me.bean.SongBean;
import com.me.dao.LocalMusicDao;
import com.me.dao.RecentListDao;
import com.me.dao.SongSheetDao;
import com.me.utils.DataDic;
import com.me.utils.Player;
import com.me.utils.RegisterUtils;
import com.me.utils.ThreadMannager;
import com.me.utils.ToolUtil;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MainPage {

	protected Shell shell;
	private Display display;
	private Text text;
	private Tray tray;
	
	private boolean isDown; // 鼠标是否按下去了
	private int downX;
	private int downY;
	private Table table;
	public Label lblNewLabel_10;
	public Label lblNewLabel_11;
	private boolean bofang = false;
	private boolean mdown = false;
	private int xx,start;
	boolean isExit = false;
	public int nowstyle=0;
	private boolean jingyin = false;
	private int yinliang = -11;
	SongBean songbean;
	private double maxProgressBar = 1;
	private double nowProgressBar = 0;
	private Label label_3;
	private Label label_4;
	private Label label_5 ;
	private Label lblNewLabel_5;
	private Image image;
	private Image image1;
	private Label lblNewLabel_13;  //播放键
	private Label label_7;
	private Label label_8;
	private int next = 0;
	public BgColor composite_bg ;
	private Label lblNewLabel_12;
	private Label lblNewLabel_14;
	private Label lblNewLabel;
	private Composite composite;
	private Label lblNewLabel_3;
	private Label label_1;
	private Label label_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainPage window = new MainPage();
			DataDic.mainPage=window;
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/logo_1.png"));
		shell.setSize(1277, 837);
		shell.setText("网易云音乐");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tray = Display.getDefault().getSystemTray(); // 获取系统托盘对象
		ShellUtil.setTray(shell, tray);
		
		Composite composit1 = new Composite(shell, SWT.NONE);
		
		composit1.setLayout(null);
		
		//composit1.setBackground(SWTResourceManager.getColor(255,0,240));
		
		composite_bg = new BgColor(composit1, SWT.NONE);
		composite_bg.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				composite_bg.setVisible(false);
	
			}
		});
		DataDic.Blue = RegisterUtils.getIntValue("Blue");
		DataDic.Red = RegisterUtils.getIntValue("Red");
		DataDic.Green = RegisterUtils.getIntValue("Green");

		composite_bg.setBounds(800, 56, 404, 266);
		composite_bg.setBackground(SWTResourceManager.getColor(250,250,250));
		composite_bg.setVisible(false);
		composite_bg.setCc(composite_bg);
		
		
		
		SashForm sashForm = new SashForm(composit1, SWT.NONE);
		sashForm.setBounds(0, 0, 1275, 835);
		sashForm.SASH_WIDTH=0;
		sashForm.setOrientation(SWT.VERTICAL);
		composite = new Composite(sashForm, SWT.NONE);
		image = new Image(display, "src/images2/bgcolor.png");
		composite.setBackgroundImage(image);
		
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setLayout(new FormLayout());
		
		label_1 = new Label(composite, SWT.NONE);
		image = new Image(display, "src/images2/Min.png");
		label_1.setImage(image);
		label_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(0, 18);
		label_1.setLayoutData(fd_label_1);
		label_1.addMouseTrackListener(new MouseTrackAdapter() {
			// 最小化按钮
			@Override
			public void mouseEnter(MouseEvent e) {
				image = new Image(display,"src/images2/Min_1.png");
				label_1.setImage(image);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				image = new Image(display,"src/images2/Min.png");
				label_1.setImage(image);
			}
		});
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
			@Override
			public void mouseUp(MouseEvent e) {
				shell.setMinimized(true);
			}
		});
		
		label_2 = new Label(composite, SWT.NONE);
		fd_label_1.right = new FormAttachment(label_2, -27);
		label_2.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		FormData fd_label_2 = new FormData();
		fd_label_2.bottom = new FormAttachment(100, -23);
		fd_label_2.top = new FormAttachment(0, 18);
		fd_label_2.right = new FormAttachment(100, -23);
		fd_label_2.left = new FormAttachment(0, 1235);
		label_2.setLayoutData(fd_label_2);
		label_2.addMouseTrackListener(new MouseTrackAdapter() {
			//关闭按钮
			@Override
			public void mouseEnter(MouseEvent e) {
				image = new Image(display,"src/images2/close_1.png");
				label_2.setImage(image);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				image = new Image(display,"src/images2/close.png");
				label_2.setImage(image);
			}
		});
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				System.exit(0);
			}
		});
		image = new Image(display, "src/images2/close.png");
		label_2.setImage( image);
		
		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 5);
		fd_lblNewLabel.left = new FormAttachment(0);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		image = new Image(display, "src/images2/logo.png");
		lblNewLabel.setImage(image);
		
		lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		FormData fd_lblNewLabel_3 = new FormData();
		fd_lblNewLabel_3.top = new FormAttachment(0, 18);
		fd_lblNewLabel_3.left = new FormAttachment(lblNewLabel, 996);
		fd_lblNewLabel_3.right = new FormAttachment(label_1, -30);
		lblNewLabel_3.setLayoutData(fd_lblNewLabel_3);
		lblNewLabel_3.addMouseTrackListener(new MouseTrackAdapter() {
			// 主题按钮
			@Override
			public void mouseExit(MouseEvent e) {
				image = new Image(display,"src/images2/theme.png");
				lblNewLabel_3.setImage(image);
			}
			@Override
			public void mouseEnter(MouseEvent e) {
				image = new Image(display,"src/images2/theme_1.png");
				lblNewLabel_3.setImage(image);
			}
		});
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				composite_bg.setVisible(true);
			}
		});
		image = new Image(display, "src/images2/theme.png");
		lblNewLabel_3.setImage(image);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_3, SWT.NONE);
		sashForm_2.setOrientation(SWT.VERTICAL);
		
		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setBackgroundImage(SWTResourceManager.getImage(MainPage.class, "/images2/bgcolor_1.png"));
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(composite_5, SWT.NONE);
		sashForm_3.setOrientation(SWT.VERTICAL);
		
		Composite composite_7 = new Composite(sashForm_3, SWT.NONE);
		
		Label lblNewLabel_4 = new Label(composite_7, SWT.NONE);
		lblNewLabel_4.setLocation(0, 39);
		lblNewLabel_4.setSize(190, 30);
		lblNewLabel_4.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		
		lblNewLabel_4.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/local_music.png"));
		
		//lblNewLabel_5.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/download.png"));
		
		Label lblNewLabel_6 = new Label(composite_7, SWT.NONE);
		lblNewLabel_6.setLocation(0, 10);
		lblNewLabel_6.setSize(190, 26);
		lblNewLabel_6.setBackgroundImage(SWTResourceManager.getImage(MainPage.class, "/images2/mymusic.png"));
		
		Composite composite_8 = new Composite(sashForm_3, SWT.NONE);
		
		Label lblNewLabel_7 = new Label(composite_8, SWT.NONE);
		lblNewLabel_7.setLocation(0, 10);
		lblNewLabel_7.setSize(94, 26);
		lblNewLabel_7.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel_7.setBackgroundImage(SWTResourceManager.getImage(MainPage.class, "/images2/creat_song_sheet.png"));
		
		Label lblNewLabel_8 = new Label(composite_8, SWT.NONE);
		lblNewLabel_8.setLocation(100, 14);
		lblNewLabel_8.setSize(16, 16);
		lblNewLabel_8.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		
		lblNewLabel_8.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				lblNewLabel_8.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/new_song_sheet_1.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				lblNewLabel_8.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/new_song_sheet.png"));
			}
		});
		lblNewLabel_8.setBackgroundImage(SWTResourceManager.getImage(MainPage.class, "/images2/new_song_sheet.png"));
		
		table = new Table(composite_8, SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				composite_bg.setVisible(false);

			}
		});
		
		table.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		table.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		table.setBackground(SWTResourceManager.getColor(245,245,247));
		table.setBounds(10, 42, 214, 425);
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("删除歌单");
		
		
		
		sashForm_3.setWeights(new int[] {1, 3});
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		DataDic.stackLayout = new  StackLayout();
		composite_4.setLayout(DataDic.stackLayout);
		
		//初始化界面为本地音乐
		LocalMusic localMusic = new LocalMusic(composite_4, SWT.NONE);
		localMusic.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		DataDic.localMusic = localMusic;
		DataDic.recentList = new RecentList(composite_4, SWT.NONE);
		DataDic.stackLayout.topControl = DataDic.localMusic ;
		DataDic.playerList = new ArrayList<Player>();
		DataDic.localPath = RegisterUtils.getValue(); //获去上次选的文件夹
		DataDic.localMusic.initUI(null);
		

		
		DataDic.bgImage = new Bgimage();
		
		composite_4.setBackgroundImage(SWTResourceManager.getImage(MainPage.class, "/images2/bgcolor_2.png"));
		composite_4.setLayout(DataDic.stackLayout);

		Composite composite_6 = new Composite(sashForm_2, SWT.NONE);
	
		composite_6.setBackgroundImage(SWTResourceManager.getImage(MainPage.class, "/images2/bgcolor_1.png"));
		
		// 专辑封面
		Label lblNewLabel_9 = new Label(composite_6, SWT.NONE);
		
		lblNewLabel_9.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/playSmall.png"));
		lblNewLabel_9.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel_9.setBounds(10, 5, 57, 64);
		
		// 歌名
		 lblNewLabel_10 = new Label(composite_6, SWT.NONE);
		//lblNewLabel_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));  字体颜色
		//Font font=new Font(Display.getCurrent(),"宋体",43,3);
		lblNewLabel_10.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		lblNewLabel_10.setText("歌曲名");
		lblNewLabel_10.setBounds(87, 12, 137, 22);
		
		// 歌手名
		 lblNewLabel_11 = new Label(composite_6, SWT.NONE);
		lblNewLabel_11.setBounds(87, 40, 137, 17);
		lblNewLabel_11.setText("歌手");
		sashForm_2.setWeights(new int[] {502, 62});
		sashForm_1.setWeights(new int[] {234, 1038});
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(MainPage.class, "/images2/bgcolor_3.png"));
		
		lblNewLabel_12 = new Label(composite_2, SWT.NONE);
		lblNewLabel_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if(DataDic.table!=null){
					ThreadMannager.player.next=-1;
					ThreadMannager.player.stop();
					ThreadMannager.player.nextmusic();
					ThreadMannager.player.next=0;
				}
			}
		});
		
		//lblNewLabel_12.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		image = new Image(display, "src/images2/the_last.png");
		lblNewLabel_12.setImage(image);
		lblNewLabel_12.setBounds(20, 14, 32, 32);
		
		lblNewLabel_13 = new Label(composite_2, SWT.NONE);
		lblNewLabel_13.addMouseListener(new MouseAdapter() {    //  播放按钮
			@SuppressWarnings("deprecation")
			@Override
			public void mouseUp(MouseEvent e) {
				
				if(ThreadMannager.player==null){
					//为空不执行
				} else if (ThreadMannager.player.isPaused()) {//   如果是暂停
					bofang = false;
					//player.start();
					//继续播放
					ThreadMannager.player.Pause();
					image1 = new Image(display, "src/images2/play.png");
					lblNewLabel_13.setImage(image1);
					ThreadMannager.lrcThread.resume();
					//ThreadMannager.lrcThread.notifyAll();
					//System.out.println("继续播放");
				}else {
					//暂停
					bofang = true;
					ThreadMannager.player.setPaused(true);
					image1 = new Image(display, "src/images2/suspend.png");
					lblNewLabel_13.setImage(image1);
					ThreadMannager.lrcThread.suspend();
						
				}
			}
		});
	
		lblNewLabel_13.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		image1 = new Image(display, "src/images2/suspend.png");
		lblNewLabel_13.setImage(image1);
		lblNewLabel_13.setBounds(77, 12, 36, 36);
		
		lblNewLabel_14 = new Label(composite_2, SWT.NONE);
		lblNewLabel_14.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseUp(MouseEvent e) {
				if(DataDic.table!=null){
					ThreadMannager.player.next=1;
					ThreadMannager.player.stop();
					ThreadMannager.player.nextmusic();
					ThreadMannager.player.next=0;
				}
			}
		});

		lblNewLabel_14.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		image = new Image(display, "src/images2/the_next.png");
		lblNewLabel_14.setImage(image);
		lblNewLabel_14.setBounds(138, 14, 32, 32);
		
		label_3 = new Label(composite_2, SWT.NONE);
		label_3.setBounds(237, 18, 62, 20);
		label_3.setText("00：00");
		
		label_5 = new Label(composite_2, SWT.NONE);
		
		
		lblNewLabel_5 = new Label(composite_2, SWT.NONE);
		DataDic.bgImage.createimg1(0);
		image = new Image(display, "src/image/35.png");
		lblNewLabel_5.setImage(image);
		lblNewLabel_5.setBounds(312, 19, 540, 20);
		
		label_4 = new Label(composite_2, SWT.NONE);
		label_4.setBounds(875, 18, 55, 20);
		label_4.setText("00:00");
		
		label_5.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if(mdown){
					xx = arg0.x - start;  //  鼠标偏移量
					int X = lblNewLabel_5.getBounds().x;//获取lblNewLabel_5初始位置
					int X1 = label_5.getBounds().x;//获取label_1初始位置
					if(X1+xx+9>X && X1+xx<X+lblNewLabel_5.getBounds().width-9){  //538*X1+304
						label_5.setBounds(X1+xx,label_5.getBounds().y,label_5.getBounds().width,label_5.getBounds().height);
						if(DataDic.bgImage.createimg1((double)(X1+xx-304)/538)){
							image = new Image(display, "src/image/35.png");
							lblNewLabel_5.setImage(image);//SWTResourceManager.getImage(MainPage.class, "/image/35.png")
						}
					}
				}
			}
		});
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				start = e.x;
				mdown = true;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				mdown = false;
			}
		});
		label_5.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				image = new Image(display, "src/image/37.png");
				label_5.setImage(image);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				image = new Image(display, "src/image/36.png");
				label_5.setImage(image);
			}
		});
		image = new Image(display, "src/image/36.png");
		label_5.setImage(image);
		label_5.setBounds(303, 19, 19, 19);
		
		Label label_6 = new Label(composite_2, SWT.NONE);
		label_6.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				if(jingyin){
					label_6.setImage(SWTResourceManager.getImage(MainPage.class, "/image/47.png"));
				} else {
					label_6.setImage(SWTResourceManager.getImage(MainPage.class, "/image/44.png"));
				}
			}
			@Override
			public void mouseExit(MouseEvent e) {
				if(jingyin){
					label_6.setImage(SWTResourceManager.getImage(MainPage.class, "/image/46.png"));
				} else {
					label_6.setImage(SWTResourceManager.getImage(MainPage.class, "/image/45.png"));
				}
			}
		});
		
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(jingyin){
					label_6.setImage(SWTResourceManager.getImage(MainPage.class, "/image/44.png"));
					jingyin = false;
					if(ThreadMannager.player!=null){
						ThreadMannager.player.getVolume().setValue((float) yinliang);
					}
					if(DataDic.bgImage.createimg38((double) (yinliang+40)/46)){
						image = new Image(display, "src/image/38.png");
						label_7.setImage(image);
					}
					label_8.setBounds(978+126*(yinliang+40)/46, 19, 19, 19);
					
				} else {					
					label_6.setImage(SWTResourceManager.getImage(MainPage.class, "/image/46.png"));
					jingyin = true;
					if(ThreadMannager.player!=null){
						ThreadMannager.player.getVolume().setValue((float)-50);
					}
					if(DataDic.bgImage.createimg38(0)){
						image = new Image(display, "src/image/38.png");
						label_7.setImage(image);
					}
					label_8.setBounds(978, 19, 19, 19);
				}
			}
			
		});
		image = new Image(display, "src/image/45.png");
		label_6.setImage(image);
		label_6.setBounds(948, 17, 23, 20);
		
		label_8 = new Label(composite_2, SWT.NONE);
		
		label_7 = new Label(composite_2, SWT.NONE);
		label_7.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				image = new Image(display, "src/image/36.png");
				label_8.setImage(image);
				label_8.setVisible(true);
				isExit = true;
			}
			@Override
			public void mouseExit(MouseEvent e) {
				label_8.setVisible(false);
			}
		});
		DataDic.bgImage.createimg38((double)(yinliang+40)/46);
		image = new Image(display, "src/image/38.png");
		label_7.setImage(image);
		label_7.setBounds(987, 18, 130, 20);
		sashForm.setWeights(new int[] {56, 721, 58});
		
		label_8.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if(mdown){
					xx = arg0.x - start;  //  鼠标偏移量
					//int X = label_7.getBounds().x;//获取label_7初始位置
					int X1 = label_8.getBounds().x;//获取label_1初始位置
					if(X1+xx>980 && X1+xx<1106){  //  980-1106
						if(ThreadMannager.player!=null){
							ThreadMannager.player.getVolume().setValue((float)(46*(X1+xx-980)/126-40));
							yinliang = (46*(X1+xx-980)/126-40);
						}
						if(DataDic.bgImage.createimg38((double)(X1+xx-980)/126)){
							image = new Image(display, "src/image/38.png");
							label_7.setImage(image);
						}
						label_8.setBounds(X1+xx,label_8.getBounds().y,label_8.getBounds().width,label_8.getBounds().height);
					}
				}
			}
		});
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				start = e.x;
				mdown = true;
				label_6.setImage(SWTResourceManager.getImage(MainPage.class, "/image/45.png"));
				jingyin=false;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				mdown = false;
			}
		});
		
		label_8.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				image = new Image(display, "src/image/37.png");
				label_8.setImage(image);
				if(isExit){
					label_8.setVisible(true);
				}
				isExit = true;
			}
			@Override
			public void mouseExit(MouseEvent e) {
				image = new Image(display, "src/image/36.png");
				label_8.setImage(image);
				isExit = false;
				label_8.setVisible(false);
			}
		});
		image = new Image(display, "src/image/36.png");
		label_8.setImage(image);
		label_8.setBounds(978+126*(yinliang+40)/46, 19, 19, 19);
		label_8.setVisible(false);
		
		Label lblNewLabel_15 = new Label(composite_2, SWT.NONE);
		lblNewLabel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				nowstyle++;
				if(nowstyle==4){
					nowstyle=0;
				}
				lblNewLabel_15.setImage(SWTResourceManager.getImage(MainPage.class, "/image/4"+nowstyle+".png"));
			}
		});
		lblNewLabel_15.setImage(SWTResourceManager.getImage(MainPage.class, "/image/40.png"));
		lblNewLabel_15.setBounds(1148, 14, 30, 25);

		
       Label lblNewLabel_2 = new Label(composite_7, SWT.NONE);
		
		lblNewLabel_2.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		image = new Image(display, "src/images2/history_playback.png");
		lblNewLabel_2.setImage(image);
		lblNewLabel_2.setBounds(0, 75, 190, 30);
		DataDic.playing = new Playing(composite_4, SWT.NONE);
		lblNewLabel_12.addMouseTrackListener(new MouseTrackAdapter() {
			// 上一首按钮
			@Override
			public void mouseHover(MouseEvent e) {
				//lblNewLabel_12.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/the_last_1.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				//lblNewLabel_12.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/the_last.png"));
			}
		});
		
		lblNewLabel_13.addMouseTrackListener(new MouseTrackAdapter() {
			// 暂停按钮
			@Override
			public void mouseHover(MouseEvent e) {
				//lblNewLabel_13.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/suspend_1.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				//lblNewLabel_13.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/suspend.png"));
			}
		});
		
		lblNewLabel_14.addMouseTrackListener(new MouseTrackAdapter() {
			// 下一首按钮
			@Override
			public void mouseHover(MouseEvent e) {
				//lblNewLabel_14.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/the_next_1.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				//lblNewLabel_14.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/the_next.png"));
			}
		});
		
		composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				if (isDown) {
					// 当前鼠标的位置
					int x = e.x;
					int y = e.y;
					
					// 偏移量
					x = x - downX;
					y = y - downY;
					
					// shell 同样偏移这么多
					shell.setLocation( shell.getLocation().x + x , shell.getLocation().y + y);
				}
			}
		});
		composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				isDown = false;
				composite_bg.setVisible(false);

			}
			@Override
			public void mouseDown(MouseEvent e) {
				isDown = true;
				
				// 记录鼠标按下去时的位置
				downX = e.x;
				downY = e.y;
			}
		});
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			// 本地音乐按钮
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_4.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/local_music_1.png"));
				//lblNewLabel_5.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/download.png"));
				lblNewLabel_2.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/history_playback.png"));

				DataDic.stackLayout.topControl = DataDic.localMusic ;
				initUI();
				composite_4.layout();
			}
		});
		//
		composite_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
			}
		});
		
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			// 歌单添加按钮
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_8.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/new_song_sheet.png"));
			    AddSheet aSheet = new AddSheet(shell, SWT.DIALOG_TRIM);
			    aSheet.open();
			    
			}
		});
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (DataDic.playing==null) {
					DataDic.playing = new Playing(composite_4, SWT.NONE);
				}
				DataDic.stackLayout.topControl = DataDic.playing ;
				composite_4.layout();
			}
		});
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			// 历史播放按钮
			@Override
			public void mouseUp(MouseEvent e) {
				lblNewLabel_4.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/local_music.png"));
				lblNewLabel_2.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/history_playback_1.png"));
				if (DataDic.recentList==null) {
					DataDic.recentList = new RecentList(composite_4, SWT.NONE);
				}
				DataDic.stackLayout.topControl = DataDic.recentList ;
				composite_4.layout();
				initUI();
			}
		});
	
		//歌单点击事件
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tItem = (TableItem)e.item;
				String str = tItem.getText(0);
				SongSheetDao mDao = new SongSheetDao();
				List<Map<String, String>> list = mDao.seachAll(str);
				if (DataDic.myCollection==null) {
					DataDic.myCollection = new MyCollection(composite_4, SWT.NONE);
				}
				DataDic.myCollection.label_1.setText(str);
				DataDic.myCollection.showData(list);
				DataDic.stackLayout.topControl = DataDic.myCollection;
			
				composite_4.layout();
			}
		});
		
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tItem =tableCursor.getRow();
				SongSheetDao sDao = new SongSheetDao();
				int result = sDao.deleteSheet(tItem.getText());
				if (result>0) {
					initUI();

					MessageDialog.openInformation(shell, "提示", "删除成功");
				}else{
					MessageDialog.openInformation(shell, "提示", "删除失败");

				}

			}
		});
		
		initUI();
	}

	public void initUI() {
		table.removeAll();
		SongSheetDao sDao = new SongSheetDao();
		java.util.List<Map<String, String>> list = sDao.showList();
       
		if (list!=null && list.size()>0) {
			TableItem tItem = null;
			for (Map<String, String> map:list) {
			    tItem = new TableItem(table, SWT.CENTER);
				tItem.setText(map.get("sname"));
			}
		}else{
			TableItem tItem = new TableItem(table, SWT.CHECK);
			tItem.setText(2,"没有找到歌单");
		}
	}
	@SuppressWarnings("deprecation")
	public void playmusic(String path){
		if(ThreadMannager.player!=null){
			ThreadMannager.player.stop();
		}
		bofang=true;
		ThreadMannager.player =new Player(path);
		ThreadMannager.player.start();//  开始播放
		image = new Image(display, "src/images2/play.png");
		lblNewLabel_13.setImage(image);

		
	}
	public void setMaxProgressBar(int max){
		this.maxProgressBar = max;
		System.out.println(max/234);
		label_4.setText(ToolUtil.timeTo( String.valueOf(max/1000000)));
	}
	public void setProgressBar(int now){
		if(now == nowProgressBar){
			return;
		} else {
			nowProgressBar = now;
		}
		double X1 = (nowProgressBar/maxProgressBar) ;//获取label_1初始位置  304-842
		//if(X1+xx+9>X && X1+xx<X+lblNewLabel_5.getBounds().width-9){
		label_3.setText(ToolUtil.timeTo( String.valueOf(now/1000000)));
		label_5.setBounds((int)(538*X1+304), 19, 19, 19);
		if(DataDic.bgImage.createimg1(X1)){
			image = new Image(display, "src/image/35.png");
			lblNewLabel_5.setImage(image);
		}
		//System.out.println(maxProgressBar+" : "+nowProgressBar+" : "+(X1+304)+" : "+X1);
		//}
	}
	public void nextmusic() {
				  int j =0;    //下一首歌该有的下标
				  int tt=0;
				  TableItem[] t =DataDic.table.getItems(); //获取所有item
				  for(int i=0,len =t.length;i<len;i++ ){ //遍历item
					  if(t[i].getText(1).trim().equals(DataDic.sBean.getMname())){
						  //  在这里得到下一首歌
						  
						  if(DataDic.mainPage.nowstyle ==0){//循环
							  if(i!=-1){
								  if(i!=len-1){
									  j++;
								  } else {
									  j=0;
								  }
							  } else{
								  if(i!=0){
									  j--;
								  } else {
									  j=len-1;
								  }
							  }
						  } else if(DataDic.mainPage.nowstyle ==1){//单曲
							  if(next==1){
								  if(i!=len-1){
									  j++;
								  } else {
									  j=0;
								  }
							  } else if(next==-1){
								  if(i!=0){
									  j--;
								  } else {
									  j=len-1;
								  }
							  } else {
								  j=i;
							  }
						  } else if(DataDic.mainPage.nowstyle ==2){//随机
							  j=((int) (Math.random() * 100))%len;
						  } else {//；列表
							  if(i!=-1){
								  if(i!=len-1){
									  j++;
								  } else {
									  return;
								  }
							  } else{
								  if(i!=0){
									  j--;
								  } else {
									  j=0;
								  }
							  }
						  }
						  
						  String time = t[j].getText(4);
						tt = ToolUtil.timeToInt(time);
					  }
				  }
				  // item   就是t[j]
					//System.out.println(item.getText(1)+" "+item.getText(4));
					LocalMusicDao lDao = new LocalMusicDao();

					List<String> list = lDao.findOne(t[j].getText(1), String.valueOf(tt), DataDic.localPath);
					if (list!=null && list.size()>0) {
						System.out.println(list.get(1));//播放路径
						 
						 //将当前歌曲信息保存
						DataDic.sBean = new SongBean(t[j].getText(1),
								t[j].getText(2), t[j].getText(3), tt, t[j].getText(5), list.get(1));
						DataDic.mainPage.lblNewLabel_10.setText( DataDic.sBean.getMname()); //显示到主界面
						DataDic.mainPage.lblNewLabel_11.setText( DataDic.sBean.getSinger());
						DataDic.playing.initUI();//加载歌词，正在播放界面
						RecentListDao rDao = new RecentListDao(); 
						rDao.insertTo(list.get(0));//更新最近播放列表
						ThreadMannager.player.stopplay();
						ThreadMannager.player=new Player(list.get(1));
						
						ThreadMannager.player.start();
					}
		
	}
	public void setMoreImage(){
		while(true){
			//if(ThreadMannager.imgThread1.isAlive() &&ThreadMannager.imgThread2.isAlive() &&ThreadMannager.imgThread3.isAlive() ){
				break;
			//}
		}
		image = new Image(display, "src/image/35.png");
		lblNewLabel_5.setImage(image);
		image = new Image(display, "src/image/38.png");
		label_7.setImage(image);
		image = new Image(display, "src/image/36.png");
		label_5.setImage(image);
		
		
		image = new Image(display, "src/images2/the_last.png");
		lblNewLabel_12.setImage(image);
		image1 = new Image(display, "src/images2/play.png");
		System.out.println(lblNewLabel_13.getImage().toString());
		if(bofang){
			image1 = new Image(display, "src/images2/play.png");
			lblNewLabel_13.setImage(image1);
		} else {
			image1 = new Image(display, "src/images2/suspend.png");
			lblNewLabel_13.setImage(image1);
		}
		image = new Image(display,"src/images2/the_next.png");
		lblNewLabel_14.setImage(image);
		image = new Image(display,"src/images2/logo.png");
		lblNewLabel.setImage(image);
		image = new Image(display,"src/images2/bgcolor.png");
		composite.setBackgroundImage(image);
		image = new Image(display,"src/images2/theme.png");
		lblNewLabel_3.setImage(image);
		image = new Image(display,"src/images2/Min.png");
		label_1.setImage(image);
		image = new Image(display,"src/images2/close.png");
		label_2.setImage(image);
	}
}
