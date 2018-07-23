package mypoject;

import java.awt.AWTException;
import java.awt.Robot;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;

public class Search extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	Bgimage bg = new Bgimage();
	public Search(Composite parent, int style) {
		super(parent,style);//SWT.INHERIT_DEFAULT

		this.setBackgroundMode(SWT.INHERIT_DEFAULT); 
		setBackgroundImage(SWTResourceManager.getImage(Search.class, "/image/30.png"));
		setLayout(null);
		
		
		
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("黑"+robot.getPixelColor(e.x,e.y) );
				bg.seRGB();
				setBackgroundImage(SWTResourceManager.getImage(Search.class, "/image/30.png"));

				
			}
		});
		lblNewLabel.setBounds(20, 50, 110, 110);
		
		
		Label label = new Label(this, SWT.NONE);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("红"+robot.getPixelColor(e.x,e.y).getRGB());
			}
		});
		label.setBounds(140, 50, 110, 110);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("粉"+robot.getPixelColor(e.x,e.y).getRGB());
				System.out.println("");
			}
		});
		label_1.setBounds(263, 50, 110, 110);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("蓝"+robot.getPixelColor(e.x,e.y).getRGB());
				System.out.println("");
			}
		});
		label_2.setBounds(20, 180, 110, 110);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("绿"+robot.getPixelColor(e.x,e.y).getRGB());
				System.out.println("");
			}
		});
		label_3.setBounds(140, 180, 110, 110);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("金"+robot.getPixelColor(e.x,e.y).getRGB());
				System.out.println("");
			}
		});
		label_4.setBounds(263, 180, 110, 110);
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

