package com.me.ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.me.dao.SongSheetDao;
import com.me.utils.DataDic;

public class AddSheet extends Dialog {
	private Text text;
	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddSheet(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		shell = new Shell(getParent(), SWT.NONE);
		shell.setBackgroundImage(SWTResourceManager.getImage(AddSheet.class, "/images2/bgcolor_2.png"));
		shell.setSize(321, 245);
		shell.setText(getText());

		Label label = new Label(shell, SWT.NONE);
		label.setBackgroundImage(SWTResourceManager.getImage(AddSheet.class, "/images2/bgcolor_2.png"));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		label.setBounds(10, 15, 97, 33);
		label.setText("新建歌单");
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(0, 50, 350, 1);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBackgroundImage(SWTResourceManager.getImage(AddSheet.class, "/images2/catalog_close.png"));
		label_2.setBounds(295, 12, 12, 11);
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		text.setText("请输入歌单名称");
		text.setBounds(22, 105, 271, 30);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnNewButton.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnNewButton.setBounds(52, 173, 98, 33);
		btnNewButton.setText("创建");
		
		Button button = new Button(shell, SWT.NONE);
		
		button.setText("取消");
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setBounds(170, 173, 98, 33);
		
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String str = text.getText();
				SongSheetDao mDao = new SongSheetDao();
				int result  = mDao.AddSheet(str);
				if (result>0) {
					DataDic.mainPage.initUI();
					MessageDialog.openInformation(shell, "提示", "创建成功！");
				}else{
					MessageDialog.openInformation(shell, "提示", "创建失败！");

				}
			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();	
				}
		});
	}


	}

