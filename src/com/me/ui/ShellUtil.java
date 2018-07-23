package com.me.ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class ShellUtil {
	/**
	 * 托盘
	 * @param shell
	 * @param tray
	 */
	public static void setTray(Shell shell, Tray tray) {
		if (tray == null) {
			MessageDialog.openError(shell, "错误提示", "本系统不支持托盘...");
		} else {
			// 在托盘对象中添加一个选项
			TrayItem item = new TrayItem(tray, SWT.NONE);
			item.setText("网易云音乐");
			item.setToolTipText("网易云音乐");
			item.setImage(SWTResourceManager.getImage(ShellUtil.class, "/images2/logo_mini.png"));
			
			item.addListener(SWT.Selection, new Listener() { // 当用户点击这个托盘项时
				@Override
				public void handleEvent(Event arg0) {
					if (shell.getMinimized()) { // 如果已经最小了,则先取消最小化
						shell.setMinimized(false);
					} else {
						if (shell.getVisible()) { // 说明是可见的
							shell.setVisible(false);
						} else {
							shell.setVisible(true);
						}
					}
				} 	
			});
			
			// 创建一个菜单
			Menu menu = new Menu(shell, SWT.POP_UP); // SWT.BAR:菜单栏  SWT.DROP_DOWN:下拉菜单  POP_UP:右击弹出式菜单
			
			item.addListener(SWT.MenuDetect, new Listener() { // Detect:发现、探测

				@Override
				public void handleEvent(Event arg0) {
					menu.setVisible(true);
				} 
			});
			
			/*
			MenuItem maxItem = new MenuItem(menu, SWT.PUSH); // PUSH:普通型  CHECK:带小勾  RADOP:显示一个圆点  CASCADE:级联式菜单  SEPARATOR:分割线
			maxItem.setText("最大化");
			maxItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					shell.setVisible(true);
					shell.setMaximized(true);
				}
			});
			*/
			
			MenuItem minItem = new MenuItem(menu, SWT.PUSH); // PUSH:普通型  CHECK:带小勾  RADOP:显示一个圆点  CASCADE:级联式菜单  SEPARATOR:分割线
			minItem.setText("最小化");
			minItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					shell.setVisible(true);
					shell.setMinimized(true);
				}
			});
			
			new MenuItem(menu, SWT.SEPARATOR);
			
			MenuItem closeItem = new MenuItem(menu, SWT.PUSH);
			closeItem.setText("关闭");
			closeItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					if (tray != null) {
						tray.dispose();
						shell.dispose();
						System.exit(0);
					}
				}
			});
		}
	}
}
