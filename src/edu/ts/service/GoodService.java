package edu.ts.service;

import java.util.List;

import edu.ts.entity.Good;

/**
 * 商品管理service
 * 提供商品的文件导入，分页查询，按id查询，查询所有、
 * 暂时不提供商品信息的删除与修改
 * @author zmnerd
 *
 */
public interface GoodService {
	
	/**
	 * 从CSV文件中批量导入商品信息
	 * @return
	 */
	public boolean importCSV(String fileName);
	
	/**
	 * 按分页查询商品列表
	 * @param currentPage 当前页面
	 * @param pageSize 设置单页容量
	 * @return Object数组，第一个为按分页查询到的会员List，第二个为当前页面对应的Page对象
	 */
	public Object[] queryByPage(String currentPage, int pageSize);
	
    /**
     * 查询所有商品信息
     * @return 商品集合
     */
    public List<Good> getAll();
    
    /**
     * 按商品id查询商品信息
     * @return 商品集合
     */
    public List<Good> getById(int id);
    
    public Object[] searchGood(String content, String currentPage, int pageSize);
    
}
