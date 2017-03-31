package edu.ts.service;

import java.util.List;

import edu.ts.entity.DataTableRequest;
import edu.ts.entity.Order;
import edu.ts.entity.OrderTableItem;
import edu.ts.entity.OrderTableItemDetail;
import edu.ts.entity.WxOrderItem;

/**
 * 订单管理service
 * 提供获取所有订单信息的方法
 * 提供按会员id获取相关订单信息的方法
 * 提供按消费记录id获取相关消费明细的方法
 * 提供按商品id获取相关消费明细的方法,用来统计商品销量
 * @author zmnerd
 *
 */
public interface OrderService {
	
	/**
	 * 为消费记录列表提供所有列表项
	 * @return
	 */
	public List<OrderTableItem> getAllOrderTableItem();
	
	/**
	 * 为用户详情页面提供相关消费记录
	 * @return
	 */
	public List<OrderTableItem> getOrderTableItemByCustomerId(int cId);
	
	
	public List<Order> getOrderByoId(int oId);
	/**
	 * 根据消费记录id获取消费明细
	 * @return
	 */
	public List<OrderTableItemDetail> getOrderTableItemDetailByoId(int oId);
	
	/**
	 * 根据商品id获取消费明细
	 * 可能用于统计分析
	 * @return
	 */
	public List<OrderTableItemDetail> getOrderTableItemDetailBygId(int gId);
	
    /**
     * 
     * @Title: importCSV
     * @Description: TODO
     * @param fileName
     * @return
     */
	public boolean importCSV(String fileName);
	
	/**
     * 按分页查询订单列表
     *
     * @param currentPage 当前页面，pageSize 设置单页容量
     * @return Object数组，第一个为按分页查询到的订单List，第二个为当前页面对应的Page对象
     */
    public Object[] queryByPage(String currentPage, int pageSize);
    
    public List<OrderTableItem> getOrderPaging(DataTableRequest dtr);

	public List<WxOrderItem> getAllWxOrderItem(String openid);
}
