package edu.ts.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ts.dao.BaseDao;
import edu.ts.dao.CustomerDao;
import edu.ts.dao.OrderDao;
import edu.ts.entity.Customer;
import edu.ts.entity.Order;
import edu.ts.entity.OrderTableItemDetail;
import edu.ts.entity.WxOrderItem;
import edu.ts.entity.WxOrderItemDetail;
import edu.ts.util.DBUtil;

public class OrderDaoImpl extends BaseDao implements OrderDao{

	@Override
	public boolean add(Order order) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		String sql="insert into orders(c_id,o_date) values(?,?)";
		Object[] obj={order.getoId(),order.getoDate()};
		if(conn!=null){
			if(update(sql, obj)>=1){
				flag = true;
			}
		}
		return flag;
	}

	
	@Override
	public List<Order> getAll() {
		conn=DBUtil.getConnection();
		List<Order> list = new ArrayList<Order>();
		String sql="select * from orders";
		Object[] obj={};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oid = rs.getInt(1);
				int cid = rs.getInt(2);
				String odate = rs.getString(3);
				Order order = new Order(oid,cid,odate);
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Order> getByOrderId(int id) {
		conn=DBUtil.getConnection();
		List<Order> list=new ArrayList<Order>();
		String sql="select * from orders where o_id=?";
		Object[] obj={id};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oid = rs.getInt(1);
				int cid = rs.getInt(2);
				String odate = rs.getString(3);
				Order order = new Order(oid,cid,odate);
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Order> getByCustomerId(int id) {
		conn=DBUtil.getConnection();
		List<Order> list=new ArrayList<Order>();
		String sql="select * from orders where c_id=?";
		Object[] obj={id};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oid = rs.getInt(1);
				int cid = rs.getInt(2);
				String odate = rs.getString(3);
				Order order = new Order(oid,cid,odate);
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public int getMaxOrderId() {
		conn=DBUtil.getConnection();
		String sql="select max(o_id) from orders";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0;
	}


	@Override
	public List<WxOrderItem> getAllWxOrderItem(String openid) {
		List<WxOrderItem> list=new ArrayList<WxOrderItem>();
		conn=DBUtil.getConnection();
		String sql="SELECT a.a as oid,a.b as amount,b.d as odate from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_openid as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id ORDER BY o.o_date) as b on a.a=b.a WHERE b.c=?";
		Object[] obj={openid};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int oid = rs.getInt("oid");
				String odate = rs.getString("odate");
				double amount = rs.getDouble("amount");
				//odate = odate.substring(0,odate.length()-2);
				List<WxOrderItemDetail> listDetail = new ArrayList<WxOrderItemDetail>();
				String sqlDetail="SELECT g.g_name as gname,g.g_pic as gpic,g.g_price as gprice,g.g_specification as gspec,od.od_quantity as gquan,g.g_price*od.od_quantity as money from orderdetails od,goods g WHERE od.g_id=g.g_id AND od.o_id=?";
				Object[] objDetail={oid};
				ResultSet rsDetail = query(sqlDetail, objDetail);
				while(rsDetail.next()){
					String gname = rsDetail.getString("gname");
					String gpic = rsDetail.getString("gpic");
					double gprice = rsDetail.getDouble("gprice");
					String gspec = rsDetail.getString("gspec");
					int gquan = rsDetail.getInt("gquan");
					double money = rsDetail.getDouble("money");
					WxOrderItemDetail woid = new WxOrderItemDetail(gname, gpic, gspec, gprice, gquan, money);
					listDetail.add(woid);
				}
				WxOrderItem woi = new WxOrderItem(oid, odate, amount, listDetail);
				list.add(woi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
}
