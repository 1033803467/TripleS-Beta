package edu.ts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ts.dao.BaseDao;
import edu.ts.dao.OrderDetailDao;
import edu.ts.entity.OrderDetail;
import edu.ts.util.DBUtil;

public class OrderDetailDaoImpl extends BaseDao implements OrderDetailDao{

	@Override
	public boolean add(OrderDetail od) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		String sql="insert into orderdetails(o_id,od_quantity,g_id) values(?,?,?)";
		Object[] obj={od.getoId(),od.getOdQuantity(),od.getgId()};
		if(conn!=null){
			if(update(sql, obj)>=1){
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<OrderDetail> getByOrderId(int id) {
		conn=DBUtil.getConnection();
		List<OrderDetail> list=new ArrayList<OrderDetail>();
		String sql="select * from orderdetails where o_id=?";
		Object[] obj={id};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int odid = rs.getInt(1);
				int oid = rs.getInt(2);
				int odquantity = rs.getInt(3);
				int gid = rs.getInt(4);
				OrderDetail od = new OrderDetail(odid,oid,odquantity,gid);
				list.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
