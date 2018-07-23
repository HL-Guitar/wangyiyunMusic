package com.me.dao;

import java.util.List;
import java.util.Map;

import com.me.utils.DataDic;

public class LocalMusicDao {
	public int insertTo(String mname,String singer,String collection,String mtime,String msize,String filepath) {
		String sql="insert into localMuList values(seq_LocalMuList_mno.nextval,?,?,?,?,?,?)";
		DBHelper dbH = new DBHelper();
		int result= dbH.update(sql, mname,singer,collection,mtime,msize,filepath);
		return result;
	}
	public List<Map<String, String>> findAll(String[] filepath) {
		String sql="select mname,singer,collection,mtime,msize from localMuList where filepath like '"+filepath[0]+"%' ";
		for (int i = 1,len=filepath.length; i < len; i++) {
			sql+=" or filepath like '"+filepath[i]+"%' ";
		}
		sql+=" order by mname";
		//System.out.println(sql);
		DBHelper dbH = new DBHelper();
		List<Map<String, String>> list = dbH.findStr(sql);
		return list;
	}
	public int insertLoPath(String filepath) {
		String sql="insert into fileDirectory values(?)";
		DBHelper dbH = new DBHelper();
		int result= dbH.update(sql, filepath);
		return result;
	}
	public List<Map<String, String>> findPath() {
		String sql="select filepath from fileDirectory";
		DBHelper dbH = new DBHelper();
		List<Map<String, String>> list = dbH.findStr(sql);
		return list;
	}
	/**
	 * 获取播放文件路径
	 * @param name 歌曲名
	 * @param time 歌曲时长，转换为秒传入，例：356
	 * @param filepath 固定传入 DataDic.localPath
	 * @return
	 */
	public List<String> findOne(String name,String time,String[] filepath) {
		String sql="select mno,filepath from localMuList where mname=? and mtime=? and (filepath like '"+filepath[0]+"%'";
		for (int i = 1,len=filepath.length; i < len; i++) {
			sql+=" or filepath like '"+filepath[i]+"%' ";
		}
		sql+=" )";
		//System.out.println(sql);
		DBHelper dbH = new DBHelper();
		List<String> list = dbH.findOneStr(sql,name,time);
		return list;
	}
	//搜索本地音乐
	public List<Map<String, String>> seachAll(String name,String[] filepath) {
		DBHelper helper = new DBHelper();
		List<Map<String,String>> list=null;
		if (!"".equals(name)) {
			String sql = "select mname,singer,collection,mtime,msize from localMuList where mname like '"+name+"%' or mname like '%"+
					name+"' or mname like '%"+name+"%' and (filepath like '"+filepath[0]+"%' ";
			
			for (int i = 1,len=filepath.length; i < len; i++) {
				sql+=" or filepath like '"+filepath[i]+"%' ";
			}
			sql+=" )";
			System.out.println(sql);
			 list = helper.findStr(sql);
		}

		return list;
	}
	//删除
	public int delete(String fstr) {
		DBHelper helper = new DBHelper();
		String sql = "delete from localMuList where filepath=?";
		String sql2 = "select mno from localMuList where filepath=?";
		List<Map<String,String>> list = helper.findStr(sql2,fstr);
		if (list!=null && list.size()>0) {
			sql2 = "delete from recentList where mno=?";
			helper.update(sql2,list.get(0).get("mno"));
		}
		int result =helper.update(sql,fstr);
		return result;
	}
	//提取部分信息
	public List<String> findSeg(String[] filepath) {
		String sql="select filepath from localMuList where filepath like '"+filepath[0]+"%'";
		for (int i = 1,len=filepath.length; i < len; i++) {
			sql+=" or filepath like '"+filepath[i]+"%' ";
		}
		DBHelper dbH = new DBHelper();
		List<String> list = dbH.findOneStr(sql);
		return list;
	}
}
