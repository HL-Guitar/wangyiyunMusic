package com.me.ui;

import java.util.List;

import oracle.net.aso.p;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;

import com.me.utils.DataDic;
import com.me.utils.LrcAnalyze.LrcData;

public class Test {

	protected Shell shell;

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
		shell.setSize(832, 573);
		shell.setText("SWT Application");
		StackLayout stackLayout = new StackLayout();
		shell.setLayout(stackLayout);
		DataDic.playing  = new Playing(shell, SWT.NONE);
		RecentList recentList = new RecentList(shell, SWT.NONE);
		AddToCollection aCollection = new AddToCollection(shell, SWT.NONE);
		stackLayout.topControl = new MyCollection(shell, SWT.NONE);
		stackLayout.topControl = DataDic.playing ;
		
		
	}

}
