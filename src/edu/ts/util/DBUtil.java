package edu.ts.util;

/**
 * Created by jose on 2017/3/2.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;




public class DBUtil {
	
	/**
	 * 通过mysql连接池获取某一个连接
	 * 
	 */
    public static Connection getConnection(){
    	
    	Connection conn=null;
		try {
			Context ct;
			ct = new InitialContext();
			DataSource dataSource=(DataSource)ct.lookup("java:comp/env/jdbc/db");//tomcat里配置context.xml;
			conn=dataSource.getConnection();
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
        
        return conn;
    }
    
    public static void close(Connection cn,Statement st,ResultSet rs){
        try {
            if(cn!=null){
                cn.close();
            }
            if(st!=null){
                st.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
    
    /**
     * 获取数据源DataSource，用于推荐系统模型
     *     
     */
    public static ConnectionPoolDataSource getDataSource(){
    	
    	ConnectionPoolDataSource dataSource=null;
    	try {
			Context ct;
			ct = new InitialContext();
			DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/db");//tomcat里配置context.xml;
			//封装DataSource 为 ConnectionPoolDataSource，用于模型
			dataSource = new ConnectionPoolDataSource(ds);
			}
		catch (NamingException e) {
			e.printStackTrace();
		}
    	
    	return dataSource;
    }

    
    
}
