package com.me.ui;

import java.util.List;
import java.util.Map;

import javax.swing.JProgressBar;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import java.util.List;
import java.util.Map;

import com.ibm.icu.text.StringTransform;
import com.me.bean.SongBean;
import com.me.dao.LocalMusicDao;
import com.me.dao.RecentListDao;
import com.me.dao.SongSheetDao;
import com.me.utils.DataDic;
import com.me.utils.ToolUtil;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MyCollection extends Composite {
	private Table table;
	private Text text;
	public Label label_1;
	private Label label_6;
	private TableColumn tblclmnNewColumn_5;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyCollection(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(null);

		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		sashForm.setBounds(0, 0, 1026, 709);


		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		composite.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		composite.setToolTipText("搜索歌单音乐");
		composite.setLayout(null);
		JProgressBar jProgressBar = new JProgressBar();

		Label label = new Label(composite, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(MyCollection.class, "/image/lovelist.png"));
		label.setBounds(45, 43, 248, 248);

		label_1 = new Label(composite, SWT.NONE);
		label_1.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		label_1.setText("我喜欢的音乐");
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 16, SWT.NORMAL));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(366, 49, 180, 35);

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		label_3.setText("歌曲数");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_3.setBounds(854, 56, 51, 20);

		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		label_4.setText("播放数");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_4.setBounds(918, 56, 51, 20);

		Label label_5 = new Label(composite, SWT.SEPARATOR);
		label_5.setBounds(911, 56, 2, 45);

		label_6 = new Label(composite, SWT.NONE);
		label_6.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		label_6.setText("0");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(854, 82, 51, 20);

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		label_7.setText("0");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_7.setAlignment(SWT.RIGHT);
		label_7.setBounds(918, 82, 51, 20);

		text = new Text(composite, SWT.BORDER);


		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		text.setText("搜索本地音乐");
		text.setToolTipText("搜索本地音乐");

		text.setBounds(776, 359, 193, 26);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/songlist.png"));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(45, 359, 82, 30);

		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(255, 69, 0));
		label_8.setBounds(71, 390, 90, 5);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/songsheet.png"));
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(321, 60, 38, 20);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);

		lblNewLabel_2.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/play_all.png"));
		lblNewLabel_2.setBounds(321, 157, 91, 26);

		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/add.png"));
		lblNewLabel_3.setBounds(411, 157, 31, 26);

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/share.png"));
		lblNewLabel_4.setBounds(460, 157, 103, 26);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackgroundImage(SWTResourceManager.getImage(MyCollection.class, "/images2/bgcolor_2.png"));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		table.setToolTipText("音乐列表");
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setToolTipText("编号");
		tblclmnNewColumn.setWidth(44);

		tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(76);
		tblclmnNewColumn_5.setText("状态");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(232);
		tblclmnNewColumn_1.setText("音乐标题");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(133);
		tblclmnNewColumn_2.setText("歌手");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(199);
		tblclmnNewColumn_3.setText("专辑");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(128);
		tblclmnNewColumn_4.setText("时长");

		TableCursor tableCursor = new TableCursor(table, SWT.NONE);


		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);

		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);

		mntmNewItem.setText("取消收藏");

		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("播放");
		sashForm.setWeights(new int[] {390, 316});

		showData(null);

		/**
		 * 点击行
		 */
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem item = (TableItem)e.item;
				//System.out.println(item.getText(1)+" "+item.getText(4));
				String time = item.getText(5);
				int tt = ToolUtil.timeToInt(time);
				DataDic.table = table;
				LocalMusicDao lDao = new LocalMusicDao();
				List<String> list = lDao.findOne(item.getText(2), String.valueOf(tt),new String[]{"%"});
				if (list!=null && list.size()>0) {
					System.out.println(list.get(1));//播放路径
					SongSheetDao sDao = new SongSheetDao();
					List<Map<String,String>> lists = sDao.findOne( list.get(1));
					int i=1;
					while ((lists==null || lists.size()<=0) && i<list.size() ) {
						lists = sDao.findOne( list.get(i));
						//System.out.println("测试"+list.get(i)+list.size());
						i++;
					}
					DataDic.sBean = new SongBean(item.getText(2),
							lists.get(0).get("singer"),lists.get(0).get("collection"), tt, lists.get(0).get("msize"), list.get(1));
					DataDic.mainPage.lblNewLabel_10.setText( DataDic.sBean.getMname());
					DataDic.mainPage.lblNewLabel_11.setText( DataDic.sBean.getSinger());
					DataDic.playing.initUI();
					RecentListDao rDao = new RecentListDao();
					rDao.insertTo(list.get(0));
					//System.out.println(list.get(i));
					DataDic.mainPage.playmusic(lists.get(0).get("filepath"));

				}
			}
		});

		/**
		 * 搜索音乐
		 */
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String str = text.getText();
				if ("".equals(str)) {
					showData(null);
				}else if (!("搜索音乐".equals(str))) {
					SongSheetDao mDao = new SongSheetDao();
					List<Map<String, String>> list = mDao.seachAll(str);
					showData(list);
				}

			}
		});

		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				text.setText("搜索本地音乐");
				showData(null);
			}
		});

		//取消收藏
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tItem = tableCursor.getRow();
				String mStr = tItem.getText(2);
				SongSheetDao mDao = new SongSheetDao();
				mDao.deleteSong(label_1.getText(), mStr);
				showData(null);

			}
		});
		//播放全部
		lblNewLabel_2.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseUp(MouseEvent e) {
				TableItem[] item = table.getItems();
				//System.out.println(item.getText(1)+" "+item.getText(4));
				String time = item[0].getText(5);
				int tt = ToolUtil.timeToInt(time);
				DataDic.table = table;
				LocalMusicDao lDao = new LocalMusicDao();
				List<String> list = lDao.findOne(item[0].getText(2), String.valueOf(tt),new String[]{"%"});
				if (list!=null && list.size()>0) {
					System.out.println(list.get(1));//播放路径
					SongSheetDao sDao = new SongSheetDao();
					List<Map<String,String>> lists = sDao.findOne( list.get(1));
					int i=0;
					while ((lists==null || lists.size()<=0) && i<list.size() ) {
						lists = sDao.findOne( list.get(++i));
						//System.out.println(list.get(i));
					}
					DataDic.sBean = new SongBean(item[0].getText(2),
							lists.get(0).get("singer"),lists.get(0).get("collection"), tt, lists.get(0).get("msize"), list.get(1));
					DataDic.mainPage.lblNewLabel_10.setText( DataDic.sBean.getMname());
					DataDic.mainPage.lblNewLabel_11.setText( DataDic.sBean.getSinger());
					DataDic.playing.initUI();
					RecentListDao rDao = new RecentListDao();
					rDao.insertTo(list.get(0));
					DataDic.mainPage.playmusic(list.get(i));

				}
			}
		});

		
	}

	/**
	 * 
	 * @param list 空值代表显示全部数据,非空代表搜索结果集
	 */
	public void  showData(List<Map<String, String>> list) {
		table.removeAll();
		SongSheetDao mDao = new SongSheetDao();
		if (!(list!=null && list.size()>0)) {
			list = mDao.findAll(label_1.getText());
		}


		if (list!=null && list.size()>0) {
			int i =1;
			label_6.setText(String.valueOf(list.size())); 
			for (Map<String, String> map:list) {
				if (!(map.get("mname").equals("0")|| map.get("mtime").equals("0"))) {
					String time =ToolUtil.timeTo( map.get("mtime"));
					TableItem tItem = new TableItem(table, SWT.CHECK);
					tItem.setText(0,String.valueOf(i++));
					//					TableEditor tEditor = new TableEditor(table);
					//					Button button = new Button(table, SWT.PUSH);
					//					button.setText("取消喜欢");
					//					button.computeSize(SWT.DEFAULT,table.getItemHeight());
					//					tEditor.grabHorizontal = true;//自动填充表格
					//					tEditor.setEditor(button, tItem, 1);
					tItem.setText(1,"已收藏");
					tItem.setText(2,map.get("mname"));
					tItem.setText(3,map.get("singer"));
					tItem.setText(4,map.get("collection"));
					tItem.setText(5,time);
				}
			}
		}else{
			TableItem tItem = new TableItem(table, SWT.CHECK);
			tItem.setText(2,"没有找到歌曲");
		}
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
