package edu.ts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ts.dao.BaseDao;
import edu.ts.dao.OrderTableItemDao;
import edu.ts.entity.CustomerTableItem;
import edu.ts.entity.DataTableRequest;
import edu.ts.entity.OrderTableItem;
import edu.ts.entity.Page;
import edu.ts.util.DBUtil;
import edu.ts.util.StringUtil;

public class OrderTableItemDaoImpl extends BaseDao implements OrderTableItemDao{

	@Override
	public List<OrderTableItem> getAll() {
		conn=DBUtil.getConnection();
		List<OrderTableItem> list=new ArrayList<OrderTableItem>();
		String sql="SELECT a.a as oid,a.b as amount,b.b as cname,b.c as cid,b.d as odate from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_id as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id where c.c_state=1) as b on a.a=b.a ORDER BY b.d desc";
		Object[] obj={};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oId = rs.getInt(1);
				int cId=rs.getInt(4);
				String cName = rs.getString(3);
				String oDate = rs.getString(5);
				double amount=rs.getDouble(2);
				
				OrderTableItem ot = new OrderTableItem(oId,cId,cName,oDate,amount);
				list.add(ot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<OrderTableItem> getByCustomerId(int cId) {
		conn=DBUtil.getConnection();
		List<OrderTableItem> list=new ArrayList<OrderTableItem>();
		String sql="SELECT a.a as oid,a.b as amount,b.b as cname,b.c as cid,b.d as odate from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_id as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id where c.c_id=?) as b on a.a=b.a ORDER BY b.d desc";
		Object[] obj={cId};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oId = rs.getInt(1);
				String cName = rs.getString(3);
				String oDate = rs.getString(5);
				double amount=rs.getDouble(2);
				
				OrderTableItem ot = new OrderTableItem(oId,cId,cName,oDate,amount);
				list.add(ot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<OrderTableItem> getRecentBycId(int cId) {
		conn=DBUtil.getConnection();
		List<OrderTableItem> list=new ArrayList<OrderTableItem>();
		String sql="SELECT a.a as oid,a.b as amount,b.b as cname,b.c as cid,b.d as odate from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_id as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id where c.c_id=? ) as b on a.a=b.a ORDER BY b.d desc limit 4";
		Object[] obj={cId};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oId = rs.getInt(1);
				String cName = rs.getString(3);
				String oDate = rs.getString(5);
				double amount=rs.getDouble(2);
				OrderTableItem ot = new OrderTableItem(oId,cId,cName,oDate,amount);
				list.add(ot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<OrderTableItem> getOrderTableItemByPage(Page page) {
		conn=DBUtil.getConnection();
		List<OrderTableItem> list = new ArrayList<OrderTableItem>();
		String sql="SELECT a.a as oid,a.b as amount,b.b as cname,b.c as cid,b.d as odate from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_id as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id where c.c_state=1) as b on a.a=b.a ORDER BY b.d desc";
		Object[] obj={};
		if(page!=null){
			sql+=" limit "+((page.getCurrentPage()-1)*page.getpageSize())+","+page.getpageSize();
		}
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oId = rs.getInt(1);
				int cId=rs.getInt(4);
				String cName = rs.getString(3);
				String oDate = rs.getString(5);
				double amount=rs.getDouble(2);
				
				OrderTableItem ot = new OrderTableItem(oId,cId,cName,oDate,amount);
				list.add(ot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<OrderTableItem> getOrderPaging(DataTableRequest dtr) {
		conn=DBUtil.getConnection();
		List<OrderTableItem> list=new ArrayList<OrderTableItem>();
		String baseSql="SELECT a.a as oid,a.b as amount,b.b as cname,b.c as cid,b.d as odate from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_id as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id where c.c_state=1) as b on a.a=b.a";
		String whereSql="";
		String limitSql="";
		String orderSql="";
		if(dtr.getSearch()!=null && !dtr.getSearch().equals("")){
			whereSql="a.a LIKE '%"+dtr.getSearch().toString()+"%' or a.b LIKE '%"+dtr.getSearch().toString()+"%' or b.b LIKE '%"+dtr.getSearch().toString()+"%'";
			if(!StringUtil.isChinese(dtr.getSearch().toString())){
				whereSql+=" or b.d LIKE '%"+dtr.getSearch().toString()+"%'";
			}else{
				whereSql+=" ";
			}
			whereSql = " and("+whereSql+")";
		}
		if(dtr.getStart()!=null && !dtr.getLength().equals("-1")){
			limitSql=" limit "+dtr.getStart().toString()+","+dtr.getLength().toString();
		}
		if(dtr.getOrderDir()!=null && dtr.getOrderColumn()!=null){
			int i = Integer.parseInt(dtr.getOrderColumn().toString());
			switch(i){
				case 0: orderSql = " order by a.a "+dtr.getOrderDir();break;
				case 1: orderSql = " order by b.c "+dtr.getOrderDir();break;
				case 2: orderSql = " order by b.b "+dtr.getOrderDir();break;
				case 3: orderSql = " order by b.d "+dtr.getOrderDir();break;
				case 5: orderSql = " order by a.b "+dtr.getOrderDir();break;
				default: orderSql = "";
			}
		}
		try {
			System.out.println(baseSql+whereSql+orderSql+limitSql);
			ResultSet rs=query(baseSql+whereSql+orderSql+limitSql);
			while(rs.next()){
				int oId = rs.getInt(1);
				int cId=rs.getInt(4);
				String cName = rs.getString(3);
				String oDate = rs.getString(5);
				double amount=rs.getDouble(2);
				OrderTableItem ot = new OrderTableItem(oId,cId,cName,oDate,amount,oId);
				list.add(ot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
}
