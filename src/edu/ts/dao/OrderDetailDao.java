package edu.ts.dao;

import java.util.List;

import edu.ts.entity.OrderDetail;

public interface OrderDetailDao {
	
	public boolean add(OrderDetail od);
	
	public List<OrderDetail> getByOrderId(int id);

}
