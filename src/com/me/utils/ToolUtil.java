package com.me.utils;

public class ToolUtil {
	/**
	 * 将整形的秒数转换为00:00的格式
	 * @param time
	 * @return
	 */
  public static String timeTo(String time) {
	  int tim =Integer.valueOf( time);
	  int min= tim/60;
	  int Seconds = tim % 60;
	  return String.valueOf(min)+":"+String.valueOf(Seconds);
  }
  /**
	 * 将00:00的格式转换为整形的秒数
	 * @param time
	 * @return
	 */
  public static int timeToInt(String time) {
	  String [] timeStrings = time.split(":");
	  int tt = Integer.valueOf(timeStrings[0])*60+Integer.valueOf(timeStrings[1]);
	  return tt;
  }
}
