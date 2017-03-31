package edu.ts.dao;

import java.util.List;

import edu.ts.entity.DataTableRequest;
import edu.ts.entity.OrderTableItem;
import edu.ts.entity.Page;

public interface OrderTableItemDao {
	
	/**
	 * 四表查询获得消费记录列表中的多行信息
	 * @return
	 */
	public List<OrderTableItem> getAll();
	
	/**
	 * 根据会员id获得其消费记录
	 * @param cId
	 * @return
	 */
	public List<OrderTableItem> getByCustomerId(int cId);
	
	/**
	 * 根据会员id获得最近消费记录
	 * @param cId
	 * @return
	 */
	public List<OrderTableItem> getRecentBycId(int cId);
	
	/**
     *通过Page获取订单集合
     *
     * @param page
     * @return 会员的集合，参数为null时返回总记录数
     */
    public List<OrderTableItem> getOrderTableItemByPage(Page page);
    
    public List<OrderTableItem> getOrderPaging(DataTableRequest dtr);
}
