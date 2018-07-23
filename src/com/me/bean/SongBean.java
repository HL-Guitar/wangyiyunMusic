package com.me.bean;

public class SongBean {
  private String mname;
  private String singer;
  private String collection;
  private int mtime;
  private String msize;
  private String filepath;
  
  public SongBean(String mname, String singer, String collection, int mtime,
		String msize, String filepath) {
	super();
    
	this.mname = mname;
	this.singer = singer;
	this.collection = collection;
	this.mtime = mtime;
	this.msize = msize;
	this.filepath = filepath;
}
  
public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public int getMtime() {
		return mtime;
	}
	public void setMtime(int mtime) {
		this.mtime = mtime;
	}
	public String getMsize() {
		return msize;
	}
	public void setMsize(String msize) {
		this.msize = msize;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


}
