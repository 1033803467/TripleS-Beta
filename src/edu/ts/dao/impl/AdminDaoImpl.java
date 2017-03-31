package edu.ts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ts.dao.AdminDao;
import edu.ts.dao.BaseDao;
import edu.ts.entity.Admin;
import edu.ts.util.DBUtil;
/**
 * 
 * @ClassName: AdminDaoImpl
 * @Description: TODO
 * @author zmnerd
 * @date 2017-3-16 下午2:39:42
 *
 */
public class AdminDaoImpl extends BaseDao implements AdminDao{
	@Override
	public boolean add(Admin admin) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		String sql="insert into admins(a_name,a_password,a_tel) values(?,?,?)";
		Object[] obj={admin.getaName(),admin.getaPassword(),admin.getaTel()};
		if(conn!=null){
			if(update(sql, obj)>=1){
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public boolean del(int id) {
		return false;
	}

	@Override
	public boolean update(Admin admin) {
		return false;
	}

	@Override
	public Admin findById(int aId) {
		return null;
	}

	@Override
	public boolean findByTelAndPwd(String mobile, String password) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		String sql="select * from admins where a_tel=? and a_password=?";
		Object[] obj={mobile,password};
		if(conn!=null)
		{
			ResultSet rs=query(sql,obj);
			try {
				rs.last();
				int rowcount=rs.getRow();
				if(rowcount>=1)
				{
					flag=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, null, rs);
			}
			
		}
		return flag;
	}

	@Override
	public List<Admin> findAll() {
		//暂时不实现
		return null;
	}

	/**
	 * @Description: 异步检查管理员手机号是否存在
	 * @param: tel 管理员电话
	 * @return: 存在返回true，否则返回false
	 * @throws
	 * @author tengyihao
	 */
	@Override
	public boolean isExist(String tel) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		
		String sql="select * from admins where a_tel=?";
		Object[] obj={tel};
		if(conn!=null)
		{
			try{
				rs=query(sql,obj);
				//如果存在tel
				if(rs.next()){
					flag=true;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally{
				DBUtil.close(conn, null, rs);
			}
	
		}
		return flag;
	}

	@Override
	public String getNameByTel(String tel) {
		conn=DBUtil.getConnection();
		String name ="未知用户";
		
		String sql="select a_name from admins where a_tel=?";
		Object[] obj={tel};
		if(conn!=null)
		{
			try{
				rs=query(sql,obj);
				if(rs.next()){
					name=rs.getString("a_name");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally{
				DBUtil.close(conn, null, rs);
			}
		}
		return name;
	}

}
