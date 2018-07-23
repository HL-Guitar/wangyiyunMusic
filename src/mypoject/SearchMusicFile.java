package mypoject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;

public class SearchMusicFile {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SearchMusicFile window = new SearchMusicFile();
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
		shell = new Shell(SWT.NONE);
		shell.setSize(261, 300);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text.setBounds(0, 54, 259, 1);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 259, 53);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(226, 0, 33, 20);
		label.setImage(SWTResourceManager.getImage(SearchMusicFile.class, "/image/24.png"));

	}
}
