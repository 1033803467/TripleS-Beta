package edu.ts.dao;

import java.util.List;

import edu.ts.entity.OrderTableItemDetail;

public interface OrderTableItemDetailDao {
	/**
	 * 根据消费记录id获取消费明细
	 * @return
	 */
	public List<OrderTableItemDetail> getOrderTableItemDetailByoId(int oId);   
	
	/**
	 * 根据商品id获取消费明细
	 * @return
	 */
	public List<OrderTableItemDetail> getOrderTableItemDetailBygId(int gId);
}
