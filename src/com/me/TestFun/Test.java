package com.me.TestFun;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.me.utils.LrcAnalyze;
import com.me.utils.Player;
import com.me.utils.LrcAnalyze.LrcData;
import com.tulskiy.musique.audio.formats.mp3.MP3FileReader;

import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;

public class Test {

	protected Shell shell;
	private Player player;
	private Player player2;
    int sound = -40;
    private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Test window = new Test();
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
		shell = new Shell();
		
		shell.setSize(947, 682);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(null);
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(103, 94, 98, 30);
		button.setText("播放");
		
		Button button_1 = new Button(composite, SWT.NONE);
		
	
		button_1.setBounds(251, 94, 98, 30);
		button_1.setText("暂停");
		
		Button button_2 = new Button(composite, SWT.NONE);
	
		button_2.setBounds(103, 170, 98, 30);
		button_2.setText("音量加10");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.setBounds(251, 170, 98, 30);
		btnNewButton.setText("音量减20");
		
		Button button_3 = new Button(composite, SWT.NONE);
	
		button_3.setBounds(103, 218, 98, 30);
		button_3.setText("最大音量");
		
		Button button_4 = new Button(composite, SWT.NONE);
		
		button_4.setBounds(251, 218, 98, 30);
		button_4.setText("加载歌词");
		
		Button button_5 = new Button(composite, SWT.NONE);
	
		button_5.setBounds(103, 281, 98, 30);
		button_5.setText("扫描音乐");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(261, 285, 73, 26);

		/**
		 * 开始播放
		 */
		button.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				//音乐文件路径
				 player =new Player("E:\\音乐\\Apologize (Timbaland Remix)-OneRepublic.mp3");
				 player2=player;
				//Music music = new Music();
				//music.setPath("E:\\音乐\\Apologize (Timbaland Remix)-OneRepublic.mp3");
				//player.setMusic(music);
				player.start();
				
			}
		});
		
		/**
		 * 暂停和继续播放
		 */
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (player.isPaused()) {
					
					player=player2;
					//player.start();
					//继续播放
					player.Pause();
					//System.out.println("继续播放");
				}else {
					//暂停
					player.setPaused(true);
					
				}
			}
		});
		
		//加音量，最小为-40，最大6
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sound+=10;
				System.out.println((float)sound);
				//super.widgetSelected(e);
				player.getVolume().setValue((float)sound);
				
			}
		});
		//减音量，最小为-40，最大6
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sound-=10;
				//super.widgetSelected(e);
				player.getVolume().setValue((float)sound);
			}
		});
		
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sound-=10;
				//super.widgetSelected(e);
				player.getVolume().setValue((float)6);
				
			}
		});
		
		
		button_4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				LrcAnalyze lrcAnalyze = new LrcAnalyze("F:\\a\\曾轶可 - 狮子座.lrc",0);
				java.io.File f=new java.io.File("F:\\a\\可惜没如果-林俊杰.lrc");  
				try {
					String str=getFileCharset("F:\\a\\曾轶可 - 狮子座.lrc");
					System.out.println(str);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				List<LrcData> list = lrcAnalyze.LrcGetList();
				for (int i = 0; i < list.size(); i++) {
					//System.out.print(list.get(i).LrcLine+"  "+list.get(i).TimeMs+"   ");
					
				}
			}
		});
		
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = "F:\\a";
				searchMusic( path);
			}
		});
		
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
					System.out.println(file.getName());
				}
			}
				
		}
	}
	
	public boolean isMusic(File file){
		String str = file.getAbsolutePath();
		return str.endsWith(".mp3");
	}
	/**
	 * <div>
	 * 利用第三方开源包cpdetector获取文件编码格式.<br/>
	 * --1、cpDetector内置了一些常用的探测实现类,这些探测实现类的实例可以通过add方法加进来,
	 *   如:ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector. <br/>
	 * --2、detector按照“谁最先返回非空的探测结果,就以该结果为准”的原则. <br/>
	 * --3、cpDetector是基于统计学原理的,不保证完全正确.<br/>
	 * </div>
	 * @param filePath
	 * @return 返回文件编码类型：GBK、UTF-8、UTF-16BE、ISO_8859_1
	 * @throws Exception 
	 */
	public static String getFileCharset(String filePath) throws Exception {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/*ParsingDetector可用于检查HTML、XML等文件或字符流的编码,
		 * 构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。
	    */
		detector.add(new ParsingDetector(false));
		/*JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码测定。
		 * 所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，
		 * 比如下面的ASCIIDetector、UnicodeDetector等。
        */
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		File file = new File(filePath);
		try {
			//charset = detector.detectCodepage(file.toURI().toURL());
			InputStream is = new BufferedInputStream(new FileInputStream(filePath));
			charset = detector.detectCodepage(is, 8);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
 
		String charsetName = "GBK";
		if (charset != null) {
			if (charset.name().equals("US-ASCII")) {
				charsetName = "ISO_8859_1";
			} else if (charset.name().startsWith("UTF")) {
				charsetName = charset.name();// 例如:UTF-8,UTF-16BE.
			}
		}
		return charsetName;
	}
}

