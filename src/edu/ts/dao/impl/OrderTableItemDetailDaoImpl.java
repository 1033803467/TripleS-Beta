package edu.ts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ts.dao.BaseDao;
import edu.ts.dao.OrderTableItemDetailDao;
import edu.ts.entity.OrderTableItemDetail;
import edu.ts.util.DBUtil;

public class OrderTableItemDetailDaoImpl extends BaseDao implements OrderTableItemDetailDao{

	@Override
	public List<OrderTableItemDetail> getOrderTableItemDetailByoId(int oId) {
		conn=DBUtil.getConnection();
		List<OrderTableItemDetail> list=new ArrayList<OrderTableItemDetail>();
		String sql="select od.od_quantity,g.g_name,g.g_catagory,g.g_brand,g.g_specification,g.g_price*od.od_quantity from goods as g,orderdetails as od where g.g_id=od.g_id and od.o_id=?";
		Object[] obj={oId};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int gquantity = rs.getInt(1);
				String gname = rs.getString(2);
				String gcatagory = rs.getString(3);
				String gbrand = rs.getString(4);
				String gspec = rs.getString(5);
				double total = rs.getDouble(6);
				OrderTableItemDetail otd = new OrderTableItemDetail(gquantity,gname,gcatagory,gbrand,gspec,total);
				list.add(otd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<OrderTableItemDetail> getOrderTableItemDetailBygId(int gId) {
		conn=DBUtil.getConnection();
		List<OrderTableItemDetail> list=new ArrayList<OrderTableItemDetail>();
		String sql="select od.od_quantity,g.g_name,g.g_catagory,g.g_brand,g.g_specification,g.g_price*od.od_quantity from goods as g,orderdetails as od where g.g_id=od.g_id and g.g_id=?";
		Object[] obj={gId};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int gquantity = rs.getInt(1);
				String gname = rs.getString(2);
				String gcatagory = rs.getString(3);
				String gbrand = rs.getString(4);
				String gspec = rs.getString(5);
				double total = rs.getDouble(6);
				OrderTableItemDetail otd = new OrderTableItemDetail(gquantity,gname,gcatagory,gbrand,gspec,total);
				list.add(otd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

}
