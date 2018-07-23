package mypoject;

import java.awt.Robot;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;

import com.me.dao.DBHelper;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class mytest {

	protected Shell shell;
	DBHelper db = new DBHelper();
	private Text text;
	private Table table_1;
	private LabelList ll;
	private Composite composite_3;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mytest window = new mytest();
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
		shell = new Shell();//SWT.NONE
		shell.setSize(1384, 810);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		ll = new LabelList();
		ll.setHeight(25);
		ll.setWidth(150);
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(72, 61, 139));
		composite.setLayout(null);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(114, 45, 76, 20);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(450, 31, 76, 20);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		int height = composite_1.getBounds().height;
		System.out.println(height+"   高");

		
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(mytest.class, "/image/8.png"));
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		//composite_2.setLayout(new FormLayout());
		
		//composite_3 = new Composite(composite_2, SWT.NONE);
		//composite_3.setBounds(0, 0, 150, 500);
		
		text = new Text(composite, SWT.BORDER);
		
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("获得焦点");
				composite_3 = new Composite(composite_2, SWT.NONE);
				//composite_3.setBounds(0, 0, 0, 0);
				searchInfo(composite_2);
			}
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("失去焦点");
				composite_3.dispose();
				//composite_3.setVisible(false);
			}
		});
		
		
		
		text.setBounds(52, 10, 73, 26);
		FormData fd_c = new FormData();
		fd_c.top = new FormAttachment(0, 10);
		fd_c.left = new FormAttachment(0, 205);
		
		
		table_1 = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(500, 0, 150, 400);
		FormData fd_table_1 = new FormData();
		fd_table_1.top = new FormAttachment(0);
		fd_table_1.bottom = new FormAttachment(0, 301);
		table_1.setLayoutData(fd_table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		//TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		//TableColumn tblclmnNewColumn_1 = new TableColumn(table_1, SWT.NONE);
		
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				searchInfo(composite_2);
			}
		});
		
		TableTree tableTree = new TableTree(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_tableTree = new FormData();
		fd_tableTree.top = new FormAttachment(0, 120);
		fd_tableTree.left = new FormAttachment(table_1, 82);
		tableTree.setLayoutData(fd_tableTree);
		
		StyledText styledText = new StyledText(composite_2, SWT.BORDER);
		styledText.setText("氨基酸的\\n adfahl");
		FormData fd_styledText = new FormData();
		fd_styledText.top = new FormAttachment(tableTree, 54);
		fd_styledText.left = new FormAttachment(table_1, 42);
		fd_styledText.bottom = new FormAttachment(tableTree, 124, SWT.BOTTOM);
		fd_styledText.right = new FormAttachment(table_1, 187, SWT.RIGHT);
		styledText.setLayoutData(fd_styledText);
		
		Combo combo = new Combo(composite_2, SWT.NONE);
		FormData fd_combo = new FormData();
		fd_combo.left = new FormAttachment(0, 412);
		combo.setLayoutData(fd_combo);
		
		ComboViewer comboViewer = new ComboViewer(composite_2, SWT.NONE);
		Combo combo_1 = comboViewer.getCombo();
		FormData fd_combo_1 = new FormData();
		fd_combo_1.top = new FormAttachment(composite_3, 124);
		fd_combo_1.left = new FormAttachment(0, 110);
		combo_1.setLayoutData(fd_combo_1);
		
		Composite composite_4 = new BgColor(composite_2, SWT.NONE);
		composite_4.setBounds(68, 320, 404, 266);
		
		
		
		
		
		//composite_3.setVisible(false);
		//composite_3.setRegion( new Region());
		
		sashForm.setWeights(new int[] {61, 444});
		System.out.println(composite_2.getSize()+"   Size");

	}
	private void searchInfo(Composite composite_2){
		String str = text.getText().trim();
		
		if (str.equals("")){
			return;//   一会获取函数显示最近搜索
		}
		String[] sqls = {"select zname,gname from ge where zname='"+str+"'",//精准查询  歌手名
				"select zname,gname from ge where gname='"+str+"'",//精准查询  歌名
				"select zname,gname from ge where zname like '"+str+"%' or gname like '"+str+"%'",//模糊查询 歌名和 歌手名
				"select zname,gname from ge where zname like '%"+str+"%' or gname like '%"+str+"%'"//模糊查询 歌名和 歌手名

		};
		List<Map<String,Object>> list = null;
		Set<String> dataSet = new HashSet<String>();  //  用于去重
		Map<String,List<String>> maps = new HashMap<String,List<String>>();
		maps.put("gname", new ArrayList<String>());//加入歌名
		maps.put("zname", new ArrayList<String>());// 加入歌手名
		
		int count = 0;   //   用来计数，表示已加入的个数
		int num ;
		for(int j=0,len=sqls.length;j<len;j++){
			list = db.finds(sqls[j]);
			if(list.size() ==0 || list == null){
				continue;
			}
			if(j==0){
				maps.get("zname").add("first");  //给先加入歌名的做个标记
				maps.get("gname").add("first");  //为了使二者对应的小标一致
				count++;
			} else if(maps.get("zname").size()==0){
				maps.get("zname").add("last");  //给先加入歌名的做个标记
				maps.get("gname").add("last");  //为了使二者对应的小标一致
				count++;
			} 
			for(Map<String,Object> map : list){
				//  如果是 0 则  先输出歌手名   ，内容太少的话则继续添加     只有这一个把歌手名放到最前
				//  如果是 1 则 先输出各名，  内容太少则继续添加
				//  如果是 2 先输出  歌名，  再加入歌手名
				//  如果是 3 先输出 歌名，再加入歌手名
				if(count > 9){
					break;
				} else {   //  先判断元素是否已经加入
					if(dataSet.add((String) map.get("zname")+(String) map.get("gname"))){
						maps.get("zname").add((String) map.get("zname"));
						maps.get("gname").add((String) map.get("gname"));
						count++;
					} 
				}
				
			}
		}
		//  如果没找到则不改变框里的东西，
		if(count == 0){
			return;
		} else {
			num=count;
		}
		composite_3.dispose();
		composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setBounds(0, 0, 150, (count+3)*ll.getHeight());
		
		table_1.removeAll();
		String title = "搜\""+text.getText().trim()+"\"相关的结果>";             //   
		
		Label labelx ;
		labelx = new Label(composite_3,SWT.NONE);
		ll.setStyle(labelx, title, num-count--, true);
		
		TableItem tbitem_1;
		tbitem_1 = new TableItem(table_1,SWT.NONE);
		tbitem_1.setText(title);
		int len=maps.get("zname").size();
		if(maps.get("zname").get(0).equals("first")){
			labelx = new Label(composite_3,SWT.NONE);
			ll.setStyle(labelx, "歌手", num-count--, true);
			labelx = new Label(composite_3,SWT.NONE);
			ll.setStyle(labelx,maps.get("zname").get(1), num-count--, false);
			labelx = new Label(composite_3,SWT.NONE);
			ll.setStyle(labelx, "单曲", num-count--, true);					
			for(int i=1;i<len;i++){
				labelx = new Label(composite_3,SWT.NONE);
				ll.setStyle(labelx, maps.get("gname").get(i), num-count--, false);
				
			}
			
			tbitem_1 = new TableItem(table_1,SWT.NONE);
			tbitem_1.setText("歌手");
			
			for(int i=1;i<len;i++){
				if(dataSet.add(maps.get("zname").get(i)+"-歌手")){   //  为了不重复加入歌手
					tbitem_1 = new TableItem(table_1,SWT.NONE);
					tbitem_1.setText("  "+maps.get("zname").get(i));
				}
			}
			tbitem_1 = new TableItem(table_1,SWT.NONE);
			tbitem_1.setText("单曲");
			for(int i=1;i<len;i++){
				if(dataSet.add(maps.get("gname").get(i)+"-歌名")){   //  为了不重复加入歌手
					tbitem_1 = new TableItem(table_1,SWT.NONE);
					tbitem_1.setText("  "+maps.get("gname").get(i)+"-"+maps.get("zname").get(i));
				}
			}
			
			
		} else {
			labelx = new Label(composite_3,SWT.NONE);
			ll.setStyle(labelx, "歌手", num-count--, true);
			labelx = new Label(composite_3,SWT.NONE);
			ll.setStyle(labelx, "  "+maps.get("zname").get(1), num-count--, false);
			labelx = new Label(composite_3,SWT.NONE);
			ll.setStyle(labelx, "单曲", num-count--, true);
			
			tbitem_1 = new TableItem(table_1,SWT.NONE);
			tbitem_1.setText("单曲");
			for(int i=1;i<len;i++){
				if(dataSet.add(maps.get("gname").get(i)+"-歌名")){   //  为了不重复加入歌手
					labelx = new Label(composite_3,SWT.NONE);
					ll.setStyle(labelx,"  "+maps.get("gname").get(i)+"-"+maps.get("zname").get(i), num-count--, false);
					
					tbitem_1 = new TableItem(table_1,SWT.NONE);
					tbitem_1.setText("  "+maps.get("gname").get(i)+"-"+maps.get("zname").get(i));
				}
			}
			
			tbitem_1 = new TableItem(table_1,SWT.NONE);
			tbitem_1.setText("歌手");
			for(int i=1;i<len;i++){
				if(dataSet.add(maps.get("zname").get(i)+"-歌手")){   //  为了不重复加入歌手
					tbitem_1 = new TableItem(table_1,SWT.NONE);
					tbitem_1.setText("  "+maps.get("zname").get(i));
				}
			}
		}
	}
	public void HistoryQuery(){  //  历史查询
		
	}
}
