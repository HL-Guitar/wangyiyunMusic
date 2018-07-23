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


import com.me.dao.SongSheetDao;

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

public class Test1 extends Composite {
	private Table table;
	private Text text;
    private Label label_1;
    private Label label_6;
    private TableColumn tblclmnNewColumn_5;
    Download d;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Test1(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(null);
		 d = new Download(this, SWT.NONE);
		d.setBounds(10, 10, 500, 500);
		d.setVisible(false);
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		sashForm.setBackgroundImage(SWTResourceManager.getImage(Test1.class, "/images2/bgcolor_2.png"));
		sashForm.setBounds(0, 0, 1026, 709);
		
	    
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(Test1.class, "/images2/bgcolor_2.png"));
		composite.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		composite.setToolTipText("搜索歌单音乐");
		composite.setLayout(null);
		JProgressBar jProgressBar = new JProgressBar();
		
		Label label = new Label(composite, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(Test1.class, "/images2/lovelist.png"));
		label.setBounds(45, 43, 248, 248);
		
		 label_1 = new Label(composite, SWT.NONE);
		 label_1.setBackgroundImage(SWTResourceManager.getImage(Test1.class, "/images2/bgcolor_2.png"));
		label_1.setText("我喜欢的音乐");
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 16, SWT.NORMAL));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_1.setBounds(388, 56, 159, 38);
		
		Label label_2 = new Label(composite, SWT.BORDER);
		label_2.setBackgroundImage(SWTResourceManager.getImage(Test1.class, "/images2/bgcolor_2.png"));
		label_2.setText("歌单");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(337, 63, 39, 25);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("歌曲数");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_3.setBounds(854, 56, 51, 20);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("播放数");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label_4.setBounds(918, 56, 51, 20);
		
		Label label_5 = new Label(composite, SWT.SEPARATOR);
		label_5.setBounds(911, 56, 2, 45);
		
		 label_6 = new Label(composite, SWT.NONE);
		label_6.setText("0");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(854, 82, 51, 20);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("0");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_7.setAlignment(SWT.RIGHT);
		label_7.setBounds(918, 82, 51, 20);
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("播放全部");
		button.setImage(SWTResourceManager.getImage(Test1.class, "/images2/bt_ic.png"));
		button.setBounds(337, 158, 108, 30);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setToolTipText("添加全部到播放列表");
		button_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		button_1.setBounds(442, 158, 32, 30);
		button_1.setText("+");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.setAlignment(SWT.LEFT);
		btnNewButton.setImage(SWTResourceManager.getImage(Test1.class, "/images2/share_btn.png"));
		btnNewButton.setBounds(490, 158, 108, 30);
		btnNewButton.setText("分享");
		
		text = new Text(composite, SWT.BORDER);
		
		
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		text.setText("搜索本地音乐");
		text.setToolTipText("搜索本地音乐");
	
		text.setBounds(551, 358, 193, 30);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(Test1.class, "/images2/bgcolor_2.png"));
		lblNewLabel.setForeground(SWTResourceManager.getColor(255, 69, 0));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(76, 360, 97, 26);
		lblNewLabel.setText("歌曲列表");
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(255, 69, 0));
		label_8.setBounds(71, 390, 90, 5);
		
	
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(Test1.class, "/images2/bgcolor_2.png"));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
	
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		table.setToolTipText("音乐列表");
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setToolTipText("编号");
		tblclmnNewColumn.setWidth(54);
		
		 tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(61);
		tblclmnNewColumn_5.setText("状态");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(167);
		tblclmnNewColumn_1.setText("音乐标题");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(105);
		tblclmnNewColumn_2.setText("歌手");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(260);
		tblclmnNewColumn_3.setText("专辑");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(177);
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
				TableItem tItem = (TableItem)e.item;
				//System.out.println(tItem.getText());
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
				}else if (!("搜索本地音乐".equals(str))) {
					System.out.println("文本值修改了");
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
				String eno = tItem.getText(2);
				SongSheetDao mDao = new SongSheetDao();
				mDao.deleteSong(label_1.getText(), tItem.getText(2));
				showData(null);
			}
		});
		
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				d.setVisible(true);
			}
		});
		
	}

	/**
	 * 
	 * @param list 空值代表显示全部数据,非空代表搜索结果集
	 */
	public void  showData(List<Map<String, String>> list) {
		table.removeAll();
		com.me.dao.SongSheetDao mDao = new com.me.dao.SongSheetDao();
        if (!(list!=null && list.size()>0)) {
        	list = mDao.findAll(label_1.getText());
		}
		
		
		if (list!=null && list.size()>0) {
			
			int i =1;
			label_6.setText(String.valueOf(list.size())); 
			
			for (Map<String, String> map:list) {
				int time =Integer.valueOf( map.get("mtime"));
				int min= time/60;
				int Seconds = time % 60;
				TableItem tItem = new TableItem(table, SWT.CHECK);
				tItem.setText(0,String.valueOf(i++));
//				TableEditor tEditor = new TableEditor(table);
//				Button button = new Button(table, SWT.PUSH);
//				button.setText("取消喜欢");
//				button.computeSize(SWT.DEFAULT,table.getItemHeight());
//				tEditor.grabHorizontal = true;//自动填充表格
//				tEditor.setEditor(button, tItem, 1);
				tItem.setText(1,"已收藏");
				tItem.setText(2,map.get("mname"));
				tItem.setText(2,map.get("mname"));
				tItem.setText(3,map.get("singer"));
				tItem.setText(4,map.get("collection"));
				tItem.setText(5, String.valueOf(min)+":"+String.valueOf(Seconds));
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
