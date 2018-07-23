package com.me.utils;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class RegisterUtils {



	// 把相应的值储存到变量中去
	public static void writeValue(String key,String value) {
		// HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs下写入注册表值.
		Preferences pre = Preferences.userNodeForPackage(RegisterUtils.class);
		pre.put(key, value);
		try {
			pre.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	public static void writeIntValue(String key,int value) {
		// HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs下写入注册表值.
		Preferences pre = Preferences.userNodeForPackage(RegisterUtils.class);
		pre.putInt(key, value);
		try {
			pre.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		
	}


	
	/***
	 * 根据key获取value
	 * 
	 */
	public static String[] getValue() {
		Preferences pre = Preferences.userNodeForPackage(RegisterUtils.class);
		String[] keys=null;
		String valString[]=null;
		try {
			keys = pre.keys();
		} catch (BackingStoreException e) {
		}
		if (keys!=null) {
			valString = new String[keys.length];
			for (int i = 0; i < valString.length; i++) {
				valString[i]=pre.get(keys[i], "time");
			}
		}
		return valString;
	}
	public static int getIntValue(String color) {
		Preferences pre = Preferences.userNodeForPackage(RegisterUtils.class);
		String[] keys=null;
		int value = 255;
		try {
			keys = pre.keys();
			value = pre.getInt(color, 1);
		} catch (BackingStoreException e) {
		}
		
		return value;
	}

	/***
	 * 清除注册表
	 * 
	 * @throws BackingStoreException
	 */
	public static void clearValue() throws BackingStoreException {
		Preferences pre = Preferences.userNodeForPackage(RegisterUtils.class);
		pre.clear();
		try {
			pre.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

}
