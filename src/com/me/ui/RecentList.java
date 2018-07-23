package com.me.ui;

import java.util.List;
import java.util.Map;

import oracle.net.aso.r;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.me.bean.SongBean;
import com.me.dao.RecentListDao;
import com.me.utils.DataDic;
import com.me.utils.ToolUtil;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RecentList extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RecentList(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(RecentList.class, "/images2/bgcolor_1.png"));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		
		Label label = new Label(this, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(MainPage.class, "/images2/historical_1.png"));		label.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		label.setFont(SWTResourceManager.getFont("华文中宋", 13, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label.setBounds(410, 28, 108, 26);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		table.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		table.setBounds(0, 72, 1054, 657);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(308);
		tableColumn.setText("总19首");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(387);
		tableColumn_1.setText("歌手");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(307);
		tableColumn_2.setText("播放次数");
		openData();
		//表格事件
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tItem = (TableItem)e.item;
				RecentListDao rDao = new RecentListDao();
				List<Map<String, String>> list = rDao.findMno(tItem.getText(0));
				if (list!=null && list.size()>0){
					list = rDao.findDetail(list.get(0).get("mno"));
					
					DataDic.sBean = new SongBean(list.get(0).get("mname"),
							list.get(0).get("singer"),list.get(0).get("collection"), Integer.valueOf(list.get(0).get("mtime")), 
							list.get(0).get("msize"), list.get(0).get("filepath"));
					DataDic.mainPage.lblNewLabel_10.setText( DataDic.sBean.getMname());
					DataDic.mainPage.lblNewLabel_11.setText( DataDic.sBean.getSinger());
					DataDic.playing.initUI();
					rDao.insertTo(list.get(0).get("mno"));
					openData();
					DataDic.mainPage.playmusic(list.get(0).get("filepath"));
					//System.out.println(list.get(0).get("filepath"));
				}
			}
		});
		/**
		 * 最近播放
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				//table.removeAll();
				
				
			}
		});
	}

	public void  openData() {
		table.removeAll();
		RecentListDao reDao = new RecentListDao();
		List<Map<String,String>> list  = reDao.findAll();
		if (list!=null && list.size()>0) {
			TableItem tItem ;
			for (Map<String, String> map:list) {
				tItem = new TableItem(table, SWT.NONE);
				tItem.setText(new String[]{map.get("mname"),map.get("singer"), map.get("frequency")});
			}
		}
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
