package edu.ts.entity;
/**
 * 暂时保留用于扩展使用
 * @author zmnerd
 *
 */
public class RecommInfo {
	
	private long userID;
	private long movieID;
	private int size;
	private String type;
	
	
	//接受推荐信息
	public RecommInfo(long userID, int size, String type) {
		super();
		this.userID = userID;
		this.size = size;
		this.type = type;
	}
	//返回推荐信息
	public RecommInfo(long userID, long movieID, int size, String type) {
		super();
		this.userID = userID;
		this.movieID = movieID;
		this.size = size;
		this.type = type;
	}
	
	public long getUserID() {
		return userID;
	}
	public long getMovieID() {
		return movieID;
	}
	public int getSize() {
		return size;
	}
	public String getType() {
		return type;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public void setMovieID(long movieID) {
		this.movieID = movieID;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
