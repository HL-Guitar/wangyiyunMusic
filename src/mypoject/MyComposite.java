package mypoject;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

public class MyComposite extends Composite{

	public MyComposite(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		this.setRegion();
	}
	public void setRegion(){
		Image image = SWTResourceManager.getImage("C:\\Users\\b.dddd\\Desktop\\新建文件夹\\7.png");
		ImageData imaged = image.getImageData();
		//区分image是α还是β像素
		boolean α = imaged.alphaData == null ? false : true;
		Region region = new Region();
		for (int i = 0; i < imaged.width; i++) {
		    for (int j = 0; j < imaged.height; j++) {
		        int v = α ? imaged.getAlpha(i, j) : imaged.getPixel(i, j);
		        if(α && v == 255){
		            region.add(i, j, 1, 1);
		        }
		        if(!α && v != 0){
		            region.add(i, j, 1, 1);
		        }
		    }
		}
		this.setBackgroundImage(image);
		this.setRegion(region);
		//以上就可以制定不规则窗体进行完成 , 真正的核心在于Region这个类
	}
	

}
