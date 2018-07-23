package mypoject;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.sun.prism.paint.Color;

public class LabelList {
	private List<Label> labels;
	private static int width;
	private static int height;
	public LabelList(Label label,int ord){
		labels = new ArrayList<Label>();
		labels.add(label);
	}
	public LabelList(){
		labels = new ArrayList<Label>();
	}
	public void setWidth(int width){
		this.width=width;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public void setLabel(Label label){
		labels.add(label);
	}
	
	public void setTitle(boolean bl){
		/*
		 * 是true则在前面设置不可点击  改字体改颜色
		 * 默认为false 点击后返回歌曲
		 * */
	}
	public void setStyle(Label label,String txt,int ord,boolean bl){
		labels.add(label);
		label.setText(txt);
		setBackground(label,bl);
		label.setBounds(0, height*ord, width, height);
	}
	public int getHeight(){
		return this.height;
	}
	public void setBackground(Label label,boolean bl){
		if(!bl){  // 设置为白色，并且添加事件
			label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));//Color.WHITE
			setListener(label);
		} else {  //  是title则设置为灰色
			label.setBackground(SWTResourceManager.getColor(240,240,240));//Color.WHITE
		}
	}
	private void setListener(Label label){
		label.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				label.setBackground(SWTResourceManager.getColor(230,230,240));//Color.WHITE
			}
			@Override
			public void mouseExit(MouseEvent e) {
				label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));//Color.WHITE
			}
		});
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println(label.getText());
			}
		});
	}
}
