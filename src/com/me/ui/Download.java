package com.me.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class Download extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Download(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(Download.class, "/images2/bgcolor_2.png"));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackgroundImage(SWTResourceManager.getImage(Download.class, "/images2/play_all.png"));
		lblNewLabel.setBounds(31, 23, 91, 26);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackgroundImage(SWTResourceManager.getImage(Download.class, "/images2/add.png"));
		lblNewLabel_1.setBounds(122, 23, 31, 26);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 833, 489);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(39);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(280);
		tblclmnNewColumn_1.setText("音乐标题");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(136);
		tblclmnNewColumn_2.setText("歌手");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(170);
		tblclmnNewColumn_3.setText("专辑");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("大小");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("下载时间");
		sashForm.setWeights(new int[] {1, 6});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
