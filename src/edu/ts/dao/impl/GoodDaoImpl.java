package edu.ts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ts.dao.BaseDao;
import edu.ts.dao.GoodDao;
import edu.ts.entity.Good;
import edu.ts.entity.MostPurchasedGood;
import edu.ts.entity.Page;
import edu.ts.util.DBUtil;

public class GoodDaoImpl extends BaseDao implements GoodDao{

	@Override
	public boolean add(Good g) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		String sql="insert into goods(g_catagory,g_name,g_price,g_pic,g_desc,g_specification,g_brand) values(?,?,?,?,?,?,?)";
		Object[] obj={g.getgCatagory(),g.getgName(),g.getgPrice(),g.getgPic(),g.getgDesc(),g.getgSpecification(),g.getgBrand()};
		if(conn!=null){
			if(update(sql, obj)>=1){
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<Good> getAll() {
		conn=DBUtil.getConnection();
		List<Good> list = new ArrayList<Good>();
		String sql="select * from goods";
		Object[] obj={};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int gid = rs.getInt(1);
				String gcatagory = rs.getString(2);
				String gname = rs.getString(3);
				double gprice = rs.getDouble(4);
				String gpic = rs.getString(5);
				String gdesc = rs.getString(6);
				String gbrand = rs.getString(7);
				String gspec = rs.getString(8);
				Good g = new Good(gid,gcatagory,gname,gprice,gpic,gdesc,gspec,gbrand);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Good> getBygId(int id) {
		conn=DBUtil.getConnection();
		List<Good> list=new ArrayList<Good>();
		String sql="select * from goods where g_id=?";
		Object[] obj={id};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int gid = rs.getInt(1);
				String gcatagory = rs.getString(2);
				String gname = rs.getString(3);
				double gprice = rs.getDouble(4);
				String gpic = rs.getString(5);
				String gdesc = rs.getString(6);
				String gbrand = rs.getString(7);
				String gspec = rs.getString(8);
				Good g = new Good(gid,gcatagory,gname,gprice,gpic,gdesc,gspec,gbrand);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Good> getByName(String name) {
		conn=DBUtil.getConnection();
		List<Good> list=new ArrayList<Good>();
		String sql="select * from goods where g_name=?";
		Object[] obj={name};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int gid = rs.getInt(1);
				String gcatagory = rs.getString(2);
				String gname = rs.getString(3);
				double gprice = rs.getDouble(4);
				String gpic = rs.getString(5);
				String gdesc = rs.getString(6);
				String gbrand = rs.getString(7);
				String gspec = rs.getString(8);
				Good g = new Good(gid,gcatagory,gname,gprice,gpic,gdesc,gspec,gbrand);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Good> getGoodByPage(Page page) {
		conn=DBUtil.getConnection();
		List<Good> list=new ArrayList<Good>();
		String sql="select * from goods";
		Object[] obj={};
		if(page!=null){
			sql+=" limit "+((page.getCurrentPage()-1)*page.getpageSize())+","+page.getpageSize();
		}
		try {
			ResultSet rs=query(sql, obj);
			while(rs.next()){
				int gid = rs.getInt(1);
				String gcatagory = rs.getString(2);
				String gname = rs.getString(3);
				double gprice = rs.getDouble(4);
				String gpic = rs.getString(5);
				String gdesc = rs.getString(6);
				String gbrand = rs.getString(7);
				String gspec = rs.getString(8);
				Good g = new Good(gid,gcatagory,gname,gprice,gpic,gdesc,gspec,gbrand);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<MostPurchasedGood> getMostPurchasedBycId(int cId) {
		conn=DBUtil.getConnection();
		List<MostPurchasedGood> list=new ArrayList<MostPurchasedGood>();
		String sql="SELECT g.g_id as id,g.g_name as name,g.g_pic as pic,g.g_brand as brand,SUM(od_quantity) as count FROM orderdetails od LEFT JOIN goods g on od.g_id = g.g_id where od.o_id in (SELECT o_id from orders WHERE c_id=?) GROUP BY od.g_id ORDER BY SUM(od_quantity) desc limit 4";
		Object[] obj={cId};  
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int gid = rs.getInt("id");
				String gname = rs.getString("name");
				String gpic = rs.getString("pic");
				String gbrand = rs.getString("brand");
				int count = rs.getInt("count");
				MostPurchasedGood g = new MostPurchasedGood(gid,gname,gpic,gbrand,count);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Good> search(String content, Page page) {
		conn=DBUtil.getConnection();
		List<Good> list=new ArrayList<Good>();
		String sql="select * from goods where g_name like '%"+content+"%' ";
		if(page!=null){
			sql+=" limit "+((page.getCurrentPage()-1)*page.getpageSize())+","+page.getpageSize();
		}
		try {
			ResultSet rs=query(sql);
			while(rs.next()){
				int gid = rs.getInt(1);
				String gcatagory = rs.getString(2);
				String gname = rs.getString(3);
				double gprice = rs.getDouble(4);
				String gpic = rs.getString(5);
				String gdesc = rs.getString(6);
				String gbrand = rs.getString(7);
				String gspec = rs.getString(8);
				Good g = new Good(gid,gcatagory,gname,gprice,gpic,gdesc,gspec,gbrand);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
}
