package edu.ts.dao;

import java.util.List;

import edu.ts.entity.Order;
import edu.ts.entity.WxOrderItem;

public interface OrderDao {
	
	public boolean add(Order order);
	
	public List<Order> getAll();

	public List<Order> getByOrderId(int id);

	public List<Order> getByCustomerId(int id);
	
	public int getMaxOrderId();
	
	public List<WxOrderItem> getAllWxOrderItem(String openid);

}
