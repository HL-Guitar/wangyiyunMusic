package com.me.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DBHelper {
	/**
	 * 加载驱动
	 */
	static{
		try {
			Class.forName(proties.getInstance().getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取连接
	 * @return
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(proties.getInstance().getProperty("url"),proties.getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 给预编译执行语句块的占位符赋值
	 * @param pstmt
	 * @param params
	 */
	public void setParams(PreparedStatement pstmt,Object... params ){
		if (params !=null  && params.length>0) {
			for (int i = 0,len = params.length; i < len; i++) {
				try {
					if ("".equals((String)params[i])) {
						params[i]="不详";
					}
					pstmt.setObject(i+1, params[i]);
				} catch (SQLException e) {
					System.out.println("第"+(i+1)+"个参数住值失败...");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 给预编译执行语句块的占位符赋值
	 * @param pstmt
	 * @param params
	 */
	public void setParams(PreparedStatement pstmt,List<List<Object>> params){
		if (params !=null  && params.size()>0) {
			for (int i = 0,len = params.size(); i < len; i++) {
				try {
					pstmt.setObject(i+1, params.get(i));
				} catch (SQLException e) {
					System.out.println("第"+(i+1)+"个参数住值失败...");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 更新操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql,List<Object> params) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = this.getConnection();
			psmt = con.prepareStatement(sql);
			this.setParams(psmt, params);
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con,psmt,null);
		}
		return 0;
	}

	/**
	 * 关闭方法
	 * @param con
	 * @param pstmt
	 * @param rs
	 */
	public void close(Connection con,PreparedStatement pstmt,ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 更新数据
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql,Object... params) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = this.getConnection();
			psmt = con.prepareStatement(sql);
			this.setParams(psmt, params);
			//	System.out.println(psmt.equals(obj));
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con,psmt,null);
		}
		return 0;
	}
	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> finds(String sql,Object... params) {
		Connection con = null; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译执行语句

			this.setParams(pstmt, params);  //赋值
			rs=pstmt.executeQuery(); //执行语句
			ResultSetMetaData rsm = rs.getMetaData(); 

			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];
			for (int i = 1; i <=colCount; i++) {
				colNames[i-1] = rsm.getColumnName(i).toLowerCase();
			}
			Map<String, Object> map=null; //每一行的数据存到一个LISt中
			while (rs.next()) {
				map=new HashMap<String, Object>();
				for(String colName:colNames) { //取每一列的值
					map.put(colName,rs.getObject(colName));
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con,pstmt,null);
		}
		return list;
	}

	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,String>> findStr(String sql,String... params) {
		Connection con = null; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译执行语句

			this.setParams(pstmt, params);  //赋值
			rs=pstmt.executeQuery(); //执行语句
			ResultSetMetaData rsm = rs.getMetaData(); 

			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];

			for (int i = 1; i <=colCount; i++) {
				colNames[i-1] = rsm.getColumnName(i).toLowerCase();
			}
			Map<String, String> map=null; //每一行的数据存到一个LISt中
			while (rs.next()) {
				map=new HashMap<String, String>();
				for(String colName:colNames) { //取每一列的值
					map.put(colName,rs.getString(colName));
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con,pstmt,null);
		}
		return list;
	}

	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<String> findOneStr(String sql,String... params) {
		Connection con = null; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();

		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译执行语句

			this.setParams(pstmt, params);  //赋值
			rs=pstmt.executeQuery(); //执行语句
			ResultSetMetaData rsm = rs.getMetaData(); 

			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];

			for (int i = 1; i <=colCount; i++) {
				colNames[i-1] = rsm.getColumnName(i).toLowerCase();
			}
			//Map<String, String> map=null; //每一行的数据存到一个LISt中
			while (rs.next()) {
				//	map=new HashMap<String, String>();
				for(String colName:colNames) { //取每一列的值
					list.add(rs.getString(colName));				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con,pstmt,null);
		}
		return list;
	}
	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> finds(String sql,List<Object> params) {
		Connection con = null; 
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		try {
			con = this.getConnection(); //获取连接
			pstmt = con.prepareStatement(sql); //预编译执行语句

			this.setParams(pstmt, params);  //赋值
			rs=pstmt.executeQuery(); //执行语句
			ResultSetMetaData rsm = rs.getMetaData(); 

			int colCount = rsm.getColumnCount();
			String[] colNames = new String[colCount];

			for (int i = 1; i <=colCount; i++) {
				colNames[i-1] = rsm.getColumnName(i).toLowerCase();
			}
			Map<String, Object> map=null; //每一行的数据存到一个LISt中
			while (rs.next()) {
				map=new HashMap<String, Object>();
				for(String colName:colNames) { //取每一列的值
					map.put(colName,rs.getObject(colName));
					//System.out.println(rs.getObject(colName));
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con,pstmt,null);
		}
		return list;
	}
	/**
	 * 获取总记录数的方法 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int  getTotal(String sql,Object...params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con= this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con, pstmt, rs);
		}
		return 0;
	}

	public int getTotal(String sql,List<Object> params){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con= this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close(con, pstmt, rs);
		}
		return 0;
	}

}
