package mypoject;

import java.awt.AWTException;
import java.awt.Robot;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.events.MouseTrackAdapter;

import com.me.utils.DataDic;

public class BgColor extends Composite {
	public Bgimage bgc = new Bgimage();
	boolean mousedown = false;
	private Composite cc;
	int start=0;
	int xx = 0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public void setCc(Composite c){
		cc=c;
	}
	public BgColor(Composite parent, int style) {
		super(parent, style);
		setLayout(null);
		
		this.setBackground(SWTResourceManager.getColor(240,240,240));
		
		Label label_1 = new Label(this, SWT.NONE);
		
		Label label = new Label(this, SWT.NONE);
		
		label_1.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				cc.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		label.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				cc.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println(e.x);
				int X = label.getBounds().x;//获取label初始位置
				label_1.setBounds(X+e.x-7,label_1.getBounds().y,label_1.getBounds().width,label_1.getBounds().height);
				int rgb1 = bgc.getRGB(e.x,label.getBounds().width);
				label_1.setBackground(SWTResourceManager.getColor((rgb1 & 0xff0000) >> 16,(rgb1 & 0xff00) >> 8,(rgb1 & 0xff) ));
				bgc.setMoreColor((rgb1 & 0xff0000) >> 16,(rgb1 & 0xff00) >> 8,(rgb1 & 0xff) );
				DataDic.mainPage.setMoreImage();
			}
		});
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				start = e.x;
				mousedown = true;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				mousedown = false;
			}
		});
		label_1.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if(mousedown){
					xx = arg0.x - start;  //  鼠标偏移量
					int X = label.getBounds().x;//获取label初始位置
					int X1 = label_1.getBounds().x;//获取label_1初始位置
					if(X1+xx+5>X && X1+xx<390){
						if(X1+xx<X){
							X=X1+xx;
						}
						label_1.setBounds(X1+xx,label_1.getBounds().y,label_1.getBounds().width,label_1.getBounds().height);
						int rgb1 = bgc.getRGB(X1+xx-X,label.getBounds().width);
						label_1.setBackground(SWTResourceManager.getColor((rgb1 & 0xff0000) >> 16,(rgb1 & 0xff00) >> 8,(rgb1 & 0xff) ));
						bgc.setMoreColor((rgb1 & 0xff0000) >> 16,(rgb1 & 0xff00) >> 8,(rgb1 & 0xff) );
						DataDic.mainPage.setMoreImage();
					}
				}
			}
		});
		
		label.setImage(SWTResourceManager.getImage(BgColor.class, "/image/31.png"));
		label.setBounds(100, 200, 307, 20);
		
		
		
//		label_1.setBounds(arg0.y,label_1.getBounds().y,label_1.getBounds().width,label_1.getBounds().height);

		
		
		Region region = new Region();
		int r=8;
		for (int i = 0; i < 2*r; i++) {
		    for (int j = 0; j < 2*r; j++) {
		        if((i-r)*(i-r)+(j-r)*(j-r)<=r*r){
		            region.add(i, j, 1, 1);
		        }
		    }
		}
		label_1.setRegion(region);
		label_1.setBackground(SWTResourceManager.getColor(240,0,240));
		label_1.setBounds(label.getBounds().x-5, label.getBounds().y, r*2, r*2);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(BgColor.class, "/image/32.png"));
		label_2.setBounds(10, 170, 76, 70);
		label_2.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				cc.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setBounds(10, 144, 76, 20);
		label_3.setText("自定义颜色");
		label_3.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				cc.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		
		class SetBgColor{
			SetBgColor(Label label){
				label.addMouseTrackListener(new MouseTrackAdapter() {
					@Override
					public void mouseEnter(MouseEvent e) {
						cc.setVisible(true);
					}
					@Override
					public void mouseExit(MouseEvent e) {
					}
				});
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
					}
					@Override
					public void mouseUp(MouseEvent e) {
						bgc.setMoreColor(label.getBackground().getRed(),label.getBackground().getGreen(), label.getBackground().getBlue());
						DataDic.mainPage.setMoreImage();
						//  在这里设置背景颜色
					}
				});
			}
		}
	
		
		SetBgColor sbc ;
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(255,92,138));
		label_4.setBounds(10, 10, 52, 51);
		sbc = new SetBgColor(label_4);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(254,118,200));
		label_5.setBounds(76, 10, 52, 51);
		sbc = new SetBgColor(label_5);
		
		Label label_6 = new Label(this, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(117,123,249));
		label_6.setBounds(142, 10, 52, 51);
		sbc = new SetBgColor(label_6);
		
		Label label_7 = new Label(this, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(57,175,234));
		label_7.setBounds(210, 10, 52, 51);
		sbc = new SetBgColor(label_7);
		
		Label label_8 = new Label(this, SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(255,122,158));
		label_8.setBounds(277, 10, 52, 51);
		sbc = new SetBgColor(label_8);
		
		Label label_9 = new Label(this, SWT.NONE);
		label_9.setBackground(SWTResourceManager.getColor(71,145,235));
		label_9.setBounds(343, 10, 52, 51);
		sbc = new SetBgColor(label_9);
		
		Label label_10 = new Label(this, SWT.NONE);
		label_10.setBackground(SWTResourceManager.getColor(43,182,105));
		label_10.setBounds(10, 69, 52, 51);
		sbc = new SetBgColor(label_10);
		
		Label label_11 = new Label(this, SWT.NONE);
		label_11.setBackground(SWTResourceManager.getColor(106,204,34));
		label_11.setBounds(76, 67, 52, 51);
		sbc = new SetBgColor(label_11);
		
		Label label_12 = new Label(this, SWT.NONE);
		label_12.setBackground(SWTResourceManager.getColor(226,171,34));
		label_12.setBounds(142, 67, 52, 51);
		sbc = new SetBgColor(label_12);
		
		Label label_13 = new Label(this, SWT.NONE);
		label_13.setBackground(SWTResourceManager.getColor(255,143,87));
		label_13.setBounds(210, 67, 52, 51);
		sbc = new SetBgColor(label_13);
		
		Label label_14 = new Label(this, SWT.NONE);
		label_14.setBackground(SWTResourceManager.getColor(253,114,109));
		label_14.setBounds(277, 67, 52, 51);
		sbc = new SetBgColor(label_14);
		
		Label label_15 = new Label(this, SWT.NONE);
		label_15.setBackground(SWTResourceManager.getColor(255,84,87));
		label_15.setBounds(343, 69, 52, 51);
		sbc = new SetBgColor(label_15);
		
		
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
