package com.me.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.chart.PieChart.Data;
import oracle.net.aso.l;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;

import com.me.bean.SongBean;
import com.me.dao.LocalMusicDao;
import com.me.dao.RecentListDao;
import com.me.dao.SongSheetDao;
import com.me.utils.DataDic;
import com.me.utils.Player;
import com.me.utils.ToolUtil;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

public class LocalMusic extends Composite {
	private Table table;
	private Display display;
	private Label label_1;
	private Text text;
	LocalMusicDao lDao = new LocalMusicDao();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LocalMusic(Composite parent, int style) {
		super(parent, style);

		setLayout(new FillLayout(SWT.HORIZONTAL));


		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/bgcolor_2.png"));
		//选择文件对话框

		Label lblNewLabel = new Label(composite, SWT.NONE);
		
		lblNewLabel.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/play_all.png"));
		lblNewLabel.setBounds(55, 63, 91, 26);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		
		lblNewLabel_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		lblNewLabel_1.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/add.png"));
		lblNewLabel_1.setBounds(146, 63, 31, 26);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));  
		lblNewLabel_2.setFont(new Font(display,"微软雅黑",13,SWT.BORDER));
		lblNewLabel_2.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/bgcolor_2.png"));
		lblNewLabel_2.setBounds(55, 14, 99, 26);
		lblNewLabel_2.setText("本地音乐");

		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));

		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));  
		lblNewLabel_3.setFont(new Font(display,"微软雅黑",8,SWT.BORDER));
		lblNewLabel_3.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/bgcolor_2.png"));
		lblNewLabel_3.setBounds(250, 19, 63, 17);
		lblNewLabel_3.setText("选择目录");

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/bgcolor_4.png"));
		lblNewLabel_4.setBounds(0, 49, 1059, 1);

		Label label = new Label(composite, SWT.NONE);
		label.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/bgcolor_4.png"));
		label.setBounds(0, 100, 825, 1);

		table = new Table(composite, SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(0, 104, 1049, 630);

		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(61);
		tblclmnNewColumn_5.setText("     ");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(314);
		tblclmnNewColumn.setText("音乐标题");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(243);
		tblclmnNewColumn_1.setText("歌手");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(232);
		tblclmnNewColumn_2.setText("专辑");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(116);
		tblclmnNewColumn_3.setText("时长");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(84);
		tblclmnNewColumn_4.setText("大小");

		label_1 = new Label(composite, SWT.NONE);
		label_1.setBackgroundImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/bgcolor_2.png"));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		label_1.setBounds(160, 18, 91, 20);
		label_1.setText("0 首音乐");

		text = new Text(composite, SWT.BORDER);

		text.setBounds(850, 15, 176, 26);
		
		Label label_2 = new Label(composite, SWT.NONE);
		
		label_2.setImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/pipei.png"));
		label_2.setBounds(201, 63, 108, 26);
       
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				ChooseDia cDia = new ChooseDia(parent.getShell(), SWT.DIALOG_TRIM|SWT.APPLICATION_MODAL);
				cDia.open();
			}
		});
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String name =text.getText().trim();
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				if (DataDic.localPath!=null) {
					list = lDao.seachAll(name, DataDic.localPath);
					initUI(list);	
				}
				
			}
		});

		lblNewLabel.addMouseTrackListener(new MouseTrackAdapter() {
			// 播放全部按钮
			@Override
			public void mouseHover(MouseEvent e) {
				lblNewLabel.setImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/play_all_1.png"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				lblNewLabel.setImage(SWTResourceManager.getImage(LocalMusic.class, "/images2/play_all.png"));
			}
		});
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				DataDic.table = table;
				TableItem[] item = table.getItems();
				DataDic.table = table;
				//System.out.println(item.getText(1)+" "+item.getText(4));
				String time = item[0].getText(4);
				int tt = ToolUtil.timeToInt(time);
				List<String> list = lDao.findOne(item[0].getText(1), String.valueOf(tt), DataDic.localPath);
				if (list!=null && list.size()>0) {
					System.out.println(list.get(1));//播放路径
					 
					 //将当前歌曲信息保存
					DataDic.sBean = new SongBean(item[0].getText(1),
							item[0].getText(2), item[0].getText(3), tt, item[0].getText(5), list.get(1));
					DataDic.mainPage.lblNewLabel_10.setText( DataDic.sBean.getMname()); //显示到主界面
					DataDic.mainPage.lblNewLabel_11.setText( DataDic.sBean.getSinger());
					DataDic.playing.initUI();//加载歌词，正在播放界面
					RecentListDao rDao = new RecentListDao(); 
					rDao.insertTo(list.get(0));//更新最近播放列表
					DataDic.mainPage.playmusic(list.get(1));
				}
			}
		});
		
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem item = (TableItem)e.item;
				DataDic.table = table;
				//System.out.println(item.getText(1)+" "+item.getText(4));
				String time = item.getText(4);
				int tt = ToolUtil.timeToInt(time);
				List<String> list = lDao.findOne(item.getText(1), String.valueOf(tt), DataDic.localPath);
				if (list!=null && list.size()>0) {
					System.out.println(list.get(1));//播放路径
					 
					 //将当前歌曲信息保存
					DataDic.sBean = new SongBean(item.getText(1),
							item.getText(2), item.getText(3), tt, item.getText(5), list.get(1));
					DataDic.mainPage.lblNewLabel_10.setText( DataDic.sBean.getMname()); //显示到主界面
					DataDic.mainPage.lblNewLabel_11.setText( DataDic.sBean.getSinger());
					DataDic.playing.initUI();//加载歌词，正在播放界面
					RecentListDao rDao = new RecentListDao(); 
					rDao.insertTo(list.get(0));//更新最近播放列表
					DataDic.mainPage.playmusic(list.get(1));
				}
				
			}
		});
		/**
		 * 匹配音乐
		 */
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				initUI(null);
			}
		});
		
	}

	public void initUI(List<Map<String, String>> list) {
		table.removeAll();

		//执行默认本地列表添加
		if (!(list!=null && list.size()>0)) {
			if (DataDic.localPath!=null && DataDic.localPath.length>0) {
				list= lDao.findAll(DataDic.localPath);
			}
		}
		if (list!=null && list.size()>0) { //搜索结果中添加的
			int i =1;
			label_1.setText(String.valueOf(list.size())+"首音乐"); 
			TableItem tItem = null;
			for (Map<String, String> map:list) {
				//int time =Integer.valueOf( );
				String time = ToolUtil.timeTo(map.get("mtime"));
				tItem = new TableItem(table, SWT.CHECK);
				tItem.setText(0,String.valueOf(i++));
				tItem.setText(1,map.get("mname"));
				tItem.setText(2,map.get("singer"));
				tItem.setText(3,map.get("collection"));
				tItem.setText(4,time);
				tItem.setText(5,map.get("msize"));
			}
		}else{
			TableItem tItem = new TableItem(table, SWT.CHECK);
			tItem.setText(2,"没有找到歌曲");
		}
	}

	
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
