package com.me.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class proties extends Properties {
   
	private static proties pro = new proties();
	public static proties getInstance() {
		return pro;
	}
    
    
	private proties(){
		InputStream is = null;
		try {
			is = this.getClass().getClassLoader().getResourceAsStream("db.properties");
			this.load(is);
			
// 			Set<String> keys = this.stringPropertyNames();
//			for (String key:keys) {
//				System.out.println(key+": "+this.getProperty(key));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		proties pro = proties.getInstance();
		
	}
	
	
}
