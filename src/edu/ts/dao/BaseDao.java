package edu.ts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ts.util.DBUtil;
/**
 * 基本的Dao层操作，封装了update和query方法，分别有带参数和不带参数两种。
 * @author zmnerd
 *
 */
public class BaseDao {
	public Connection conn;
	public PreparedStatement ps;
	public ResultSet rs;
	/**
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int update(String sql,Object[] obj){
		int flag=0;
		try {
			ps=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			flag=ps.executeUpdate();
		} catch (SQLException e) {
			flag=-1;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return flag;
	}
	/**
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public ResultSet query(String sql,Object[] obj){
		try {
			ps=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int update(String sql){
		int flag=0;
		try {
			ps=conn.prepareStatement(sql);
			flag=ps.executeUpdate();
		} catch (SQLException e) {
			flag=-1;
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return flag;
	}
	
	public ResultSet query(String sql){
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
