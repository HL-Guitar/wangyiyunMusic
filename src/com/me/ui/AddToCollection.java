package com.me.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.me.dao.SongSheetDao;
import com.me.utils.DataDic;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class AddToCollection extends Composite {


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	Composite co = null;
	private Table table;
	public AddToCollection(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setBackgroundImage(SWTResourceManager.getImage(AddToCollection.class, "/images2/bgcolor_2.png"));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(null);

	
		
		
		

		table = new Table(this, SWT.BORDER);
		
		table.setLinesVisible(true);
		table.setBounds(10, 118, 440, 262);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(50);
		tblclmnNewColumn.setText("New Column");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(385);

		Label label = new Label(this, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label.setBackgroundImage(SWTResourceManager.getImage(AddToCollection.class, "/images2/bgcolor_2.png"));
		label.setBounds(10, 10, 124, 30);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		label.setText("收藏到歌单");

		Label label_1 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(0, 46, 465, 2);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBackgroundImage(SWTResourceManager.getImage(AddToCollection.class, "/images2/catalog_add.png"));
		label_2.setBounds(10, 54, 40, 40);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setBackgroundImage(SWTResourceManager.getImage(AddToCollection.class, "/images2/bgcolor_2.png"));

		label_3.setBounds(56, 58, 89, 27);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setText("新建歌单");

		Label label_4 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(0, 110, 465, 2);

		Button button = new Button(this, SWT.NONE);
		button.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		button.setImage(SWTResourceManager.getImage(AddToCollection.class, "/images2/catalog_close.png"));
		button.setBounds(440, 5, 12, 11);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
		});

		
		co=this;

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				co.setVisible(false);
			}
		});
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				AddSheet addSheet = new AddSheet(getShell(), SWT.DIALOG_TRIM);
				addSheet.open();
			}
		});

		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem item = (TableItem)e.item;
//				System.out.println(label.getText());
				SongSheetDao mDao = new SongSheetDao();
				System.out.println(item.getText(1));
				if (!"".equals(item.getText(1))) {
					int result = mDao.insertTo(item.getText(1), DataDic.sBean.getMname(), DataDic.sBean.getSinger(),
							DataDic.sBean.getCollection(), String.valueOf(DataDic.sBean.getMtime()), 
							DataDic.sBean.getFilepath());
					
					if (result>0) {
						MessageDialog.openInformation(getShell(), "提示", "添加成功");
					}else{
						MessageDialog.openInformation(getShell(), "提示", "添加失败，歌单中已存在");

					}
				}
				
			}
			
		});
		showData();

	}

	public void showData() {
		table.removeAll();
		SongSheetDao mDao =new SongSheetDao();
		List<Map<String, String>> list = mDao.showList();

		if (list!=null && list.size()>0) {
			TableItem tItem ;
			for (Map<String, String> map:list) {
				tItem = new TableItem(table, SWT.NONE);
			    tItem.setImage(SWTResourceManager.getImage(AddToCollection.class, "/image/minLove.png"));
				tItem.setText(1,map.get("sname"));
				//System.out.println(map.get("sname"));
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
