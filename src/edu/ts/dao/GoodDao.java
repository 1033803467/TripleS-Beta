package edu.ts.dao;

import java.util.List;

import edu.ts.entity.Good;
import edu.ts.entity.MostPurchasedGood;
import edu.ts.entity.Page;
/**
 * 
 * @author zmnerd
 *
 */
public interface GoodDao {
	
	/**
	 * 
	 * @param g
	 * @return
	 */
    public boolean add(Good g);
    
    /**
     * 
     * @return
     */
    public List<Good> getAll();
    
    /**
     * 
     * @param id
     * @return
     */
    public List<Good> getBygId(int id);
    
    /**
     * 通过商品名获取商品集合
     * @param name
     * @return 商品集合
     */
    public List<Good> getByName(String name);
    
    /**
     *通过Page获取商品集合
     *
     * @param page
     * @return 商品集合，参数为null时返回总记录数
     */
    public List<Good> getGoodByPage(Page page);
    /**
     * 根据会员id获取其经常购买的商品
     * @param cId
     * @return 经常购买的商品集合
     */
    public List<MostPurchasedGood> getMostPurchasedBycId(int cId);
    
    public List<Good> search(String content, Page page);
}
