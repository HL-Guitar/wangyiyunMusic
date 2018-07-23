package com.me.dao;

import java.util.List;
import java.util.Map;

import com.me.utils.DataDic;

public class RecentListDao {
	public List<Map<String, String>>  findAll() {
		DBHelper helper = new DBHelper();
		String sql = "select mname,singer,frequency from recentList r join localMuList l on r.mno=l.mno ";
		List<Map<String,String>> list = helper.findStr(sql);
		return list;
	}

	public List<Map<String, String>>  findList() {
		DBHelper helper = new DBHelper();
		String sql = "select mname,singer,frequency from recentList r join localMuList l on r.mno=l.mno ";
		List<Map<String,String>> list = helper.findStr(sql);
		return list;
	}

	public List<Map<String, String>>  findOne(String mno) {
		DBHelper helper = new DBHelper();
		String sql = "select mno,frequency from recentList where mno=?";
		List<Map<String,String>> list = helper.findStr(sql,mno);
		return list;
	}
	
	public List<Map<String, String>>  findMno(String mname) {
		DBHelper helper = new DBHelper();
		String sql = "select r.mno from recentList r join localMuList l on r.mno = l.mno where l.mname =?";
		List<Map<String,String>> list = helper.findStr(sql,mname);
		return list;
	}
	 public List<Map<String, String>>  findDetail(String mno) {
			DBHelper helper = new DBHelper();
			String sql = "select mno,mname,singer,collection,mtime,filepath from localMuList where mno =?";
			List<Map<String,String>> list = helper.findStr(sql,mno);
			return list;
	  }
	public int insertTo(String mno) {
		DBHelper helper = new DBHelper();
		List<Map<String, String>> list = findOne(mno);
		String sql=null;
		if (list!=null && list.size()>0) {
			sql = "update recentList set frequency = "+list.get(0).get("frequency")+"+1 where mno=?";
		}else{
			  sql = "insert into recentList values(?,1)";
		}
		int result = helper.update(sql,mno);
		DataDic.recentList.openData();
		return result;
	}
}
