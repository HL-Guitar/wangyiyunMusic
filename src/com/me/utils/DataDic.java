package com.me.utils;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import mypoject.Bgimage;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Table;

import com.me.bean.SongBean;
import com.me.dao.SongSheetDao;
import com.me.ui.Download;
import com.me.ui.LocalMusic;
import com.me.ui.MainPage;
import com.me.ui.MyCollection;
import com.me.ui.Playing;
import com.me.ui.RecentList;

public class DataDic {
	public static SongBean sBean = null; 
	public static Playing playing =null ;
	public static StackLayout stackLayout = new StackLayout();
	public static LocalMusic localMusic=null;
	public static Download download=null;
	public static MyCollection myCollection=null;
	public static RecentList recentList=null;
	public static String[] localPath=null;
	public static MainPage mainPage=null;
	public static Bgimage bgImage=null;
	public static List<List<String>> mList = null;
	public static Table table = null;
	public static List<Player> playerList= null;
	public static double y=0;
	public static double j=0;
	public static int Red=255,Green=0,Blue=0;
//	//实现对按歌曲名的排许
//	public static class CollatorComparator implements Comparator {
//		Collator collator = Collator.getInstance();
//		public int compare(Object element1, Object element2) {
//			CollationKey key1 = collator.getCollationKey(element1.toString());
//			CollationKey key2 = collator.getCollationKey(element2.toString());
//			return key1.compareTo(key2);
//		}
//	}
}
