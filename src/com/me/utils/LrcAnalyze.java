package com.me.utils;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
public class LrcAnalyze {
 
	/**
	 * [ar:艺人名] [ti:曲名] [al:专辑名] [by:编者（指编辑LRC歌词的人）] [offset:时间补偿值]
	 * 其单位是毫秒，正值表示整体提前，负值相反。这是用于总体调整显示快慢的。
	 * */
	// parse taget artist
	private final String TagAr = "[ar:";
 
	// perse taget tittle
	private final String TagTi = "[ti:";
 
	// perse target album
	private final String TagAl = "[al:";
 
	// perse target author of the lrc
	private final String TagBy = "[by:";
 
	// perse taget offset
	private final String TagOff = "[offset:";
 
	// record the file
	private FileInputStream filein;
 
	// record the file
	private File file;
 
	// get lrc artist
	public static final int ARTIST_ZONE = 0;
 
	// get lrc tittle
	public static final int TITTLE_ZONE = 1;
 
	// get lrc album
	public static final int ALBUM_ZONE = 2;
 
	// get lrc author
	public static final int AOTHOR_ZONE = 3;
 
	// get lrc offset
	public static final int OFFSET_ZONE = 4;
 
	// get lrc
	public static final int LRC_ZONE = 5;
 
	// lrc data contract
	public class LrcData {
		public int type;
		public String Time; // time of string format
		public float TimeMs; // time of long format ms
		// public char TimeHour; // hour of time
		// public char TimeMinute; // minute of time
		// public char TimeSecond; // second of time
		// public char TimeMilliSecond; // millisecond of time
		public String LrcLine; // one line lrc
	}
 
	// record analyzed lrc
	private List<LrcData> LrcList;
	private LinkedHashMap<Float,String> LrcMap;
	String type="UTF-8";
	public LinkedHashMap<Float, String> getLrcMap() {
		return LrcMap;
	}

	public void LinkedHashMap(LinkedHashMap<Float, String> lrcMap) {
		LrcMap = lrcMap;
	}

	private float goTime=0;
 
	/**
	 * constract
	 * */
	public LrcAnalyze(File file) {
		try {
			filein = new FileInputStream(file);
 
			this.file = file;
 
			LrcList = new ArrayList<LrcData>();
			
			LrcAnalyzeStart();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * constract
	 * */
	public LrcAnalyze(String path,float goTime) {
		try {
			filein = new FileInputStream(path);
 
			file = new File(path);
			try {
				String str=getFileCharset(file.getAbsolutePath());
				type=str;
				//System.out.println(type);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			LrcList = new ArrayList<LrcData>();
			LrcMap = new LinkedHashMap<Float, String>();
            this.goTime=goTime;
			LrcAnalyzeStart();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	private float LrcAnalyzeTimeStringToValue(String time) {
//		 System.out.print(time.substring(0, time.lastIndexOf(":"))+"  ");
//		 System.out.print(time.substring(time.indexOf(":") + 1,
//		 time.lastIndexOf("."))+"  ");
//		 System.out.println(time.substring(time.indexOf(".") + 1));
// 
		long minute = Integer
				.parseInt(time.substring(0, time.lastIndexOf(":")));
 
		long second = Integer.parseInt(time.substring(time.indexOf(":") + 1,
				time.lastIndexOf(".")));
 
		long millisecond = Integer
				.parseInt(time.substring(time.indexOf(".") + 1,time.length()-1));
       // System.out.println("："+minute+" "+second+" "+millisecond+" ");
       // System.out.println((float) (minute * 60 + second+millisecond*0.01));
		return (float) (minute * 60 + second+(millisecond)*0.1+goTime);
	}
 
	private void LrcAnalyzeLine(String ContentLine) {
      
		try {
			if (ContentLine.indexOf(TagAr) != -1) {// whether artist or not
				LrcData lrcdata = new LrcData();
				lrcdata.type = ARTIST_ZONE;
				lrcdata.LrcLine = ContentLine.substring(
						ContentLine.indexOf(':') + 1, ContentLine.lastIndexOf(']'));
				// System.out.println(lrcline.LrcLine);
				LrcList.add(lrcdata);
			} else if (ContentLine.indexOf(TagAl) != -1) {// whether album or not
				LrcData lrcdata = new LrcData();
				lrcdata.type = ALBUM_ZONE;
				lrcdata.LrcLine = ContentLine.substring(
						ContentLine.indexOf(':') + 1, ContentLine.lastIndexOf(']'));
				// System.out.println(lrcline.LrcLine);
				LrcList.add(lrcdata);
			} else if (ContentLine.indexOf(TagTi) != -1) {// whether tittle or not
				LrcData lrcdata = new LrcData();
				lrcdata.type = TITTLE_ZONE;
				lrcdata.LrcLine = ContentLine.substring(
						ContentLine.indexOf(':') + 1, ContentLine.lastIndexOf(']'));
				// System.out.println(lrcline.LrcLine);
				LrcList.add(lrcdata);
			} else if (ContentLine.indexOf(TagBy) != -1) {// whether author or not
				LrcData lrcdata = new LrcData();
				lrcdata.type = AOTHOR_ZONE;
				lrcdata.LrcLine = ContentLine.substring(
						ContentLine.indexOf(':') + 1, ContentLine.lastIndexOf(']'));
				// System.out.println(lrcline.LrcLine);
				LrcList.add(lrcdata);
			} else if (ContentLine.indexOf(TagOff) != -1) {// whether offset or not
				LrcData lrcdata = new LrcData();
				lrcdata.type = OFFSET_ZONE;
				lrcdata.LrcLine = ContentLine.substring(
						ContentLine.indexOf(':') + 1, ContentLine.lastIndexOf(']'));
				// System.out.println(lrcline.LrcLine);
				LrcList.add(lrcdata);
			} else {// lrc content
				String[] cut = ContentLine.split("]");
				
				if (cut.length >= 2) {
					
					for (int i = 0; i < cut.length - 1; i++) {
						LrcData lrcdata = new LrcData();
						lrcdata.type = LRC_ZONE;
						lrcdata.Time = cut[i]
								.substring(ContentLine.indexOf('[') + 1);
						lrcdata.TimeMs = LrcAnalyzeTimeStringToValue(lrcdata.Time);
						lrcdata.LrcLine = cut[cut.length - 1];
						// System.out.println("------" + i + "-----"
						// + ">>>>>>>" + lrcdata.Time
						// + ">>>>>>>" + lrcdata.LrcLine
						// + ">>>>>>>" + lrcdata.TimeMs);
						LrcMap.put(lrcdata.TimeMs, lrcdata.LrcLine);
					//	System.out.println(lrcdata.TimeMs+" "+lrcdata.LrcLine);
						LrcList.add(lrcdata);
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	private void LrcAnalyzeStart() {
		try {
			// new memory for file content
			byte[] ContentForByte = new byte[(int) file.length()];
 
			// read file content
			filein.read(ContentForByte);
 
			// cover byte to string
			String ContentForString = new String(ContentForByte,type);
 
			String[] ContentLine = ContentForString.split("\n");
 
			if (!ContentLine[0].equals(new String(ContentLine[0].getBytes(type ), type ))) {
				type="GBK";
				ContentForString = new String(ContentForByte,type);
				ContentLine = ContentForString.split("\n");
			}
			
			for (int i = 0; i < ContentLine.length; i++) {
				// System.out.println(ContentLine[i]);
				LrcAnalyzeLine(ContentLine[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * <div>
	 * 利用第三方开源包cpdetector获取文件编码格式.<br/>
	 * --1、cpDetector内置了一些常用的探测实现类,这些探测实现类的实例可以通过add方法加进来,
	 *   如:ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector. <br/>
	 * --2、detector按照“谁最先返回非空的探测结果,就以该结果为准”的原则. <br/>
	 * --3、cpDetector是基于统计学原理的,不保证完全正确.<br/>
	 * </div>
	 * @param filePath
	 * @return 返回文件编码类型：GBK、UTF-8、UTF-16BE、ISO_8859_1
	 * @throws Exception 
	 */
	public static String getFileCharset(String filePath) throws Exception {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/*ParsingDetector可用于检查HTML、XML等文件或字符流的编码,
		 * 构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。
	    */
		detector.add(new ParsingDetector(false));
		/*JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码测定。
		 * 所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，
		 * 比如下面的ASCIIDetector、UnicodeDetector等。
        */
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		File file = new File(filePath);
		try {
			//charset = detector.detectCodepage(file.toURI().toURL());
			InputStream is = new BufferedInputStream(new FileInputStream(filePath));
			charset = detector.detectCodepage(is, 8);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
 
		String charsetName = "GBK";
		if (charset != null) {
			if (charset.name().equals("US-ASCII")) {
				charsetName = "ISO_8859_1";
			} else if (charset.name().startsWith("UTF")) {
				charsetName = charset.name();// 例如:UTF-8,UTF-16BE.
			}
		}
		return charsetName;
	}
	public List<LrcData> LrcGetList() {
		return LrcList;
	}
}
