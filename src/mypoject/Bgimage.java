package mypoject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;


import com.me.utils.DataDic;
import com.me.utils.RegisterUtils;
import com.me.utils.ThreadMannager;

public class Bgimage {
	
	public static void main(String[] args){
		Bgimage a = new Bgimage();
		a.createimg();
	}
	public void createimg(){
		File out;
		BufferedImage bi = new BufferedImage(300, 7, 1);
		
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int rgb1 = getRGB(i,width);
				bi.setRGB(i, j, rgb1);
			}
		}
		try {
			out = new File("C:\\Users\\db\\Desktop\\30.png");
			if (!out.exists())
				out.createNewFile();
			OutputStream output = new FileOutputStream(out);
			ImageIO.write(bi, "png", output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void setMoreColor(int r,int g,int b){
		DataDic.Red=r;
		DataDic.Green=g;
		DataDic.Blue=b;
		RegisterUtils.writeIntValue("Red", r);
		RegisterUtils.writeIntValue("Green", g);
		RegisterUtils.writeIntValue("Blue", b);
		/*
		if(ThreadMannager.imgThread1!=null && !ThreadMannager.imgThread1.isAlive()){
			ThreadMannager.imgThread1.stop();
		}
		ThreadMannager.imgThread1 =new Thread (new Runnable() {
			public void run() {
				createimgMore();
			}
		});
		ThreadMannager.imgThread1.start(); //开启线程
		if(ThreadMannager.imgThread2!=null && !ThreadMannager.imgThread2.isAlive()){
			ThreadMannager.imgThread2.stop();
		}
		ThreadMannager.imgThread2 =new Thread (new Runnable() {
			public void run() {
				createimgMore1();
			}
		});
		ThreadMannager.imgThread2.start(); //开启线程
		if(ThreadMannager.imgThread3!=null && !ThreadMannager.imgThread3.isAlive()){
			ThreadMannager.imgThread3.stop();
		}
		ThreadMannager.imgThread3 =new Thread (new Runnable() {
			public void run() {
				createimgMore2();
			}
		});
		ThreadMannager.imgThread3.start(); //开启线程
		*/
	
		createimg36();
		createimg37();
		createimg38(DataDic.y);
		createimg1(DataDic.j);
		createimgMore();
		createimgMore1();
		createimgMore2();
		
	}
	public boolean createimg1(double x){  //   音乐播放条
		DataDic.j=x;
		File out;
		BufferedImage bi = new BufferedImage(540, 6, 1);
		
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		//System.out.println("width=" + width + ",height=" + height + ".");
		//System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int rgb1;
				if(i<width*x){
					rgb1=DataDic.Red <<16 | DataDic.Green << 8 | DataDic.Blue;
				} else {
					rgb1=200 <<16 | 200 << 8 | 200;
				}
				if((i<1 || i==width-1) && (j!=2 && j!=3)){
					rgb1=246 <<16 | 246 << 8 | 248;
				}
				if(i<255){
					rgb1=rgb1 | i<<24;
				}
				bi.setRGB(i, j, rgb1);
			}
		}
		try {
			out = new File("src/image/35.png");//"src/image/35.png"
			if (!out.exists())
				out.createNewFile();
			OutputStream output = new FileOutputStream(out);
			ImageIO.write(bi, "png", output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public void createimg36(){
		File out;
		BufferedImage bi = new BufferedImage(19, 19, 1);
		
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		int d;
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				d = (i-9)*(i-9)+(j-9)*(j-9);
				int rgb1;
				if(d<=83){
					if(d<=4){
						rgb1=DataDic.Red<<16|DataDic.Green<<8|DataDic.Blue;
					} else if(d<26) {
						rgb1=246 <<16 | 246 << 8 | 248;
					} else {
						rgb1=(165+d) <<16 | (165+d) << 8 | (167+d);
					}
				} else {
					rgb1=246 <<16 | 246 << 8 | 248;
				}
				bi.setRGB(i, j, rgb1);
			}
		}
		try {
			out = new File("src/image/36.png");
			if (!out.exists())
				out.createNewFile();
			OutputStream output = new FileOutputStream(out);
			ImageIO.write(bi, "png", output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createimg37(){   //  鼠标放上后的图片
		File out;
		BufferedImage bi = new BufferedImage(19, 19, 1);
		
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		int d;
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				d = (i-9)*(i-9)+(j-9)*(j-9);
				int rgb1;
				if(d<=83){
					if(d<=4){
						rgb1=DataDic.Red<<16|DataDic.Green<<8|DataDic.Blue;
					} else if(d<26) {
						rgb1=246 <<16 | 246 << 8 | 248;
					} else {
						rgb1=(246+2*(83-d)) <<16 | (246+2*(83-d)) << 8 | (248+2*(83-d));
					}
				} else {
					rgb1=246 <<16 | 246 << 8 | 248;
				}
				bi.setRGB(i, j, rgb1);
			}
		}
		try {
			out = new File("src/image/37.png");
			if (!out.exists())
				out.createNewFile();
			OutputStream output = new FileOutputStream(out);
			ImageIO.write(bi, "png", output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean createimg38(double x){   //  音乐条的
		DataDic.y = x;
		File out;
		BufferedImage bi = new BufferedImage(130, 5, 1);
		
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		//int d;
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int rgb1;
				if(i<width*x){
					rgb1=DataDic.Red<<16|DataDic.Green<<8|DataDic.Blue;
				} else {
					rgb1=200 <<16 | 200 << 8 | 200;
				}
				if((i<1 || i==width-1) && (j!=2 && j!=3)){
					rgb1=246 <<16 | 246 << 8 | 248;
				}
				if(i<255){
					rgb1=rgb1 | i<<24;
				}
				bi.setRGB(i, j, rgb1);
			}
		}
		try {
			out = new File("src/image/38.png");
			if (!out.exists())
				out.createNewFile();
			OutputStream output = new FileOutputStream(out);
			ImageIO.write(bi, "png", output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean createimgMore(){   
		File out;
		String[] strs = {"src/images2/the_last.png","src/images2/play.png","src/images2/suspend.png","src/images2/the_next.png"};
		for(String str : strs){
			BufferedImage bi=null;
			try {
				bi = ImageIO.read(new File(str.replace('2', '3')));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int width = bi.getWidth();
			int height = bi.getHeight();
			int minx = bi.getMinX();
			int miny = bi.getMinY();
			//int d;  //  226  149  149      
			BufferedImage bufferedImage = new BufferedImage(width, height,1); //BufferedImage.TYPE_4BYTE_ABGR
			for (int i = minx; i < width; i++) {
				for (int j = miny; j < height; j++) {
					int rgb1;
					int rgb2=bi.getRGB(i, j);
					rgb1=rgb2;
					int r=((rgb2 & 0xff0000) >> 16),g=(rgb2 & 0xff00) >> 8,b=rgb2 & 0xff;
					//System.out.println(((rgb2 & 0xff0000) >> 16)+"  "+((rgb2 & 0xff00) >> 8)+"   "+(rgb2 & 0xff));
					if(r==255 && g==255 && b==255){
						rgb1=255<<16|255<<8|255;
					} else if(g==b && g>86){
						rgb1=235<<16|235<<8|235;
					} else if(r+g+b>200 && r+g+b<739){
						rgb1=(DataDic.Red<<16)|(DataDic.Green<<8)|(DataDic.Blue);
						//System.out.println("&&&"+DataDic.Red+"   "+DataDic.Blue+"   "+DataDic.Green);
					} else {
						rgb1=0<<24 |(rgb1 & 0x00ffffff);
					}
					
					bufferedImage.setRGB(i, j, rgb1);
					rgb2=bi.getRGB(i, j);
					//System.out.println(((rgb2 & 0xff0000) >> 16)+"  "+((rgb2 & 0xff00) >> 8)+"   "+(rgb2 & 0xff));
					
				}
			}
			try {
				out = new File(str);
				if (!out.exists())
					out.createNewFile();
				OutputStream output = new FileOutputStream(out);
				ImageIO.write(bufferedImage, "png", output);
				System.out.println(str);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	public boolean createimgMore2(){   
		File out;
		String[] strs = {"src/images2/theme.png", "src/images2/theme_1.png", "src/images2/bgcolor.png", "src/images2/logo.png"};
		for(String str : strs){
			BufferedImage bi=null;
			try {
				bi = ImageIO.read(new File(str.replace('2', '3')));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int width = bi.getWidth();
			int height = bi.getHeight();
			int minx = bi.getMinX();
			int miny = bi.getMinY();
			//int d;  //  226  149  149      
			BufferedImage bufferedImage = new BufferedImage(width, height,1); //BufferedImage.TYPE_4BYTE_ABGR
			for (int i = minx; i < width; i++) {
				for (int j = miny; j < height; j++) {
					int rgb1;
					int rgb2=bi.getRGB(i, j);
					rgb1=rgb2;
					int r=((rgb2 & 0xff0000) >> 16),g=(rgb2 & 0xff00) >> 8,b=rgb2 & 0xff;
					//System.out.println(((rgb2 & 0xff0000) >> 16)+"  "+((rgb2 & 0xff00) >> 8)+"   "+(rgb2 & 0xff));
					if(r==255 && g==255 && b==255){
						rgb1=255<<16|255<<8|255;
					} else if(g==b && g>86){
						rgb1=235<<16|235<<8|235;
					} else if(r+g+b>200 && r+g+b<739){
						rgb1=(DataDic.Red<<16)|(DataDic.Green<<8)|(DataDic.Blue);
						//System.out.println("&&&"+DataDic.Red+"   "+DataDic.Blue+"   "+DataDic.Green);
					} else {
						rgb1=0<<24 |(rgb1 & 0x00ffffff);
					}
					
					bufferedImage.setRGB(i, j, rgb1);
					rgb2=bi.getRGB(i, j);
					//System.out.println(((rgb2 & 0xff0000) >> 16)+"  "+((rgb2 & 0xff00) >> 8)+"   "+(rgb2 & 0xff));
					
				}
			}
			try {
				out = new File(str);
				if (!out.exists())
					out.createNewFile();
				OutputStream output = new FileOutputStream(out);
				ImageIO.write(bufferedImage, "png", output);
				System.out.println(str);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	public boolean createimgMore1(){   //  音乐条的
		File out;
		String[] strs = {"src/images2/Min.png","src/images2/Min_1.png","src/images2/close.png","src/images2/close_1.png"};
		for(String str : strs){
			BufferedImage bi=null;
			try {
				bi = ImageIO.read(new File(str.replace('2', '3')));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int width = bi.getWidth();
			int height = bi.getHeight();
			int minx = bi.getMinX();
			int miny = bi.getMinY();
			//int d;  //  226  149  149      
			BufferedImage bufferedImage = new BufferedImage(width, height,1); //BufferedImage.TYPE_4BYTE_ABGR
			for (int i = minx; i < width; i++) {
				for (int j = miny; j < height; j++) {
					int rgb1;
					int rgb2=bi.getRGB(i, j);
					rgb1=rgb2;
					int r=((rgb2 & 0xff0000) >> 16),g=(rgb2 & 0xff00) >> 8,b=rgb2 & 0xff;
					//System.out.println(((rgb2 & 0xff0000) >> 16)+"  "+((rgb2 & 0xff00) >> 8)+"   "+(rgb2 & 0xff));
					if(r==255 && g==255 && b==255){
						rgb1=255<<16|255<<8|255;
					} else if(g==b && g>86){
						rgb1=235<<16|235<<8|235;
					} else if(r+g+b>200 && r+g+b<739){
						rgb1=(DataDic.Red<<16)|(DataDic.Green<<8)|(DataDic.Blue);
						//System.out.println("&&&"+DataDic.Red+"   "+DataDic.Blue+"   "+DataDic.Green);
					} else {
						rgb1=0<<24 |(rgb1 & 0x00ffffff);
					}
					
					bufferedImage.setRGB(i, j, rgb1);
					rgb2=bi.getRGB(i, j);
					//System.out.println(((rgb2 & 0xff0000) >> 16)+"  "+((rgb2 & 0xff00) >> 8)+"   "+(rgb2 & 0xff));
					
				}
			}
			try {
				out = new File(str);
				if (!out.exists())
					out.createNewFile();
				OutputStream output = new FileOutputStream(out);
				ImageIO.write(bufferedImage, "png", output);
				System.out.println(str);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	public int getRGB(int x,int X){
		int sex = X/6;
		int R=0;
		int G=0;
		int B=0;
		int RGB=0;
		if(x<=sex){
			R=255;
			G=x*255/sex;
			B=0;
		} else if(x<=2*sex) {
			R=255-(x-sex)*255/sex;
			G=255;
			B=0;
		} else if(x<=3*sex) {
			R=0;
			G=255;
			B=(x-2*sex)*255/sex;
		} else if(x<=4*sex) {
			R=0;
			G=255-(x-3*sex)*255/sex;
			B=255;
		} else if(x<=5*sex) {
			R=(x-4*sex)*255/sex;
			G=0;
			B=255;
		} else{
			R=255;
			G=0;
			B=255-(x-5*sex)*255/sex;
		}
		RGB=R <<16 | G <<8 | B;
		//System.out.println(R+" "+G+" "+B+"  "+RGB +":"+  (R<<16));
		return RGB;
	}
	public void seRGB() {
		File out;
		BufferedImage bi = new BufferedImage(300, 10, 1);
		
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		//System.out.println("width=" + width + ",height=" + height + ".");
		//System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int rgb1 = 255 <<16 | 100 <<8 | 0;
				bi.setRGB(i, j, rgb1);
			}
		}
		try {
			out = new File("C:\\Users\\db\\Desktop\\MusicPlayer\\MusicPlayer\\bin\\image\\30.png");
			if (!out.exists())
				out.createNewFile();
			OutputStream output = new FileOutputStream(out);
			ImageIO.write(bi, "png", output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void analyseRGB(String filePath, String newFilePath, int type) {
		OutputStream output = null;
		try {
			BufferedImage img = ImageIO.read(new File(filePath));
			int imageType = img.getType();// 获取图片类型
			int width = img.getWidth();// 获取图片宽度
			int height = img.getHeight();// 获取图片高度
			int startX = 0;// 开始的横坐标
			int startY = 0;// 开始的纵坐标
			int offset = 0;// 偏移量
			int scansize = width;// 扫描间距
			int dd = width - startX;// 被遍历的宽度间距
			int hh = height - startY;// 被遍历的高度间距
			int x0 = width / 2;// 横向中间点
			int y0 = height / 2;// 纵向中间点
			System.out.println("dd:" + dd + " hh:" + hh);
			System.out.println("width:" + width + " height:" + height);
			System.out.println("imageType:" + imageType);
			System.out.println("size:"+(offset + hh * scansize + dd));
			// rgb的数组，保存像素，用一维数组表示二位图像像素数组
			int[] rgbArray = new int[offset + hh * scansize + dd];// 偏移量+纵向开始坐标*扫描间距+横向开始坐标
			
			int[] newArray = new int[offset + hh * scansize + dd];// 偏移量+纵向开始坐标*扫描间距+横向开始坐标
			
			img.getRGB(startX, startY, width, height, rgbArray, offset,
					scansize); // 把像素存到固定的数组里面去
			int count=0;
			for(int i : rgbArray){
				System.out.println(i);
				if(i!=0){
					count=count+1;
				}
			}
			System.out.println(count);
			int rgb = rgbArray[offset + (y0 - startY) * scansize
					+ (x0 - startX)]; // 位于图片正中央的rgb像素点
			Color c = new Color(rgb);
			System.out.println("中间像素点的rgb:"+c);
			for (int i = 0; i < height - startY; i++) {//遍历每一行
				for (int j = 0; j < width - startX; j++) {//遍历每一列
					c = new Color(rgbArray[offset+i * scansize + j]);
					switch (type) {
					case 1://红色灰度图片
						newArray[i*dd + j] = new Color(c.getRed(), 0, 0).getRGB();
						break;
					case 2://绿色灰度图片
						 newArray[i*dd + j] = new Color(0, c.getGreen(), 0).getRGB(); 
						break;
					case 3://蓝色灰度图片
						newArray[i * dd + j] = new Color(0, 0, c.getBlue()).getRGB();
						break;
 
					default:
						break;
					}
 
					
					
				}
			}
			// 新建一个图像
			File out = new File(newFilePath);
			if (!out.exists())
				out.createNewFile();
			output = new FileOutputStream(out);
			BufferedImage imgOut = new BufferedImage(width, height,
					BufferedImage.TYPE_3BYTE_BGR);
			imgOut.setRGB(startX, startY, width, height, newArray, offset,
					scansize);
			ImageIO.write(imgOut, "jpg", output);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}


}
