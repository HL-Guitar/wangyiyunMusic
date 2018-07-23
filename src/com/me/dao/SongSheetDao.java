package com.me.dao;

import java.util.List;
import java.util.Map;

public class SongSheetDao {
   public int insertTo(String sname,String mname,String singer,String collection,String mtime,String filepath) {
	 String sql="insert into songSheet values(?,?,?,?,?,?)";
	 DBHelper dbH = new DBHelper();
	 int result= dbH.update(sql, sname,mname,singer,collection,mtime,filepath);
	 return result;
   }
   
   public List<Map<String, String>>  findAll(String name) {
		DBHelper helper = new DBHelper();
		String sql = "select mname,singer,collection,mtime from songSheet where sname =?";
		List<Map<String,String>> list = helper.findStr(sql,name);
		return list;
   }
   
   public List<Map<String, String>>  findOne(String path) {
		DBHelper helper = new DBHelper();
		String sql = "select mname,singer,collection,mtime,filepath from songSheet where filepath =?";
		List<Map<String,String>> list = helper.findStr(sql,path);
		return list;
  }
   
   public int deleteSong(String sname,String mname) {
 		DBHelper helper = new DBHelper();
 		String sql = "delete from songSheet where sname=? and mname=?";
 		int result = helper.update(sql, sname,mname);
 		return result;
   }
   
   public List<Map<String, String>> seachAll(String name) {
 		DBHelper helper = new DBHelper();

 		String sql = "select mname,singer,collection,mtime from songsheet where mname like '"+name+"%' or mname like '%"+
 		name+"' or mname like '%"+name+"%' and sname =?";
 		List<Map<String,String>> list = helper.findStr(sql,name);
 		return list;
    }
   
   public int AddSheet(String sname) {
		DBHelper helper = new DBHelper();
		String sql = "insert into songSheet values(?,0,0,0,0,0)";
		int result = helper.update(sql, sname);
		return result;
  }
   
   public int deleteSheet(String sname) {
 		DBHelper helper = new DBHelper();
 		String sql = "delete from songsheet where sname =?";
 		int result = helper.update(sql, sname);
 		return result;
   }
   
   public List<Map<String, String>> showList() {
	String sql ="select distinct sname from songSheet";
	DBHelper db = new DBHelper();
	List<Map<String, String>> list = db.findStr(sql);
	return list;
   }
}
