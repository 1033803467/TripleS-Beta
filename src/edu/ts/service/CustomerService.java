package edu.ts.service;

import edu.ts.entity.DataTableRequest;
import edu.ts.entity.Customer;
import edu.ts.entity.CustomerTableItem;
import edu.ts.entity.Feedback;
import edu.ts.entity.Good;
import edu.ts.entity.MostPurchasedGood;
import edu.ts.entity.OrderTableItem;

import java.util.List;

/**
 * 会员管理service
 * 提供会员信息的增删改，查询全部，按手机号，id，personid，openid查询
 * @author zmnerd，jose
 */
public interface CustomerService {
	
    /**
     * 添加会员信息
     *
     * @param customer
     * @return 成功为true，否则为false
     */
    public boolean add(Customer customer);
    
    /**
     * 修改会员信息
     * 
     * @param customer
     * @return 成功为true，否则为false
     */
    public boolean modify(Customer customer);
    
    /**
     * 删除会员信息
     *
     * @param 会员id
     * @return 成功为true，否则为false
     */
    public boolean delete(int cId);
    
    /**
     * 按分页查询会员列表
     *
     * @param currentPage 当前页面，pageSize 设置单页容量
     * @return Object数组，第一个为按分页查询到的会员List，第二个为当前页面对应的Page对象
     */
    public Object[] queryByPage(String currentPage, int pageSize);
    
    /**
     * 获取所有会员信息，并按创建时间倒序排列
     * @return 会员列表
     */
    public List<Customer> getAll();
    
    /**
     * 通过用户手机号查询用户
     *
     * @param cTel 用户手机号
     * @return 会员的集合
     */
    public List<Customer> getByTel(String cTel);

    /**
     *通过id获取会员
     *
     * @param id
     * @return 会员的集合
     */
    public List<Customer> getById(int id);
    
    /**
     * 通过personId获取会员信息
     * 
     * @param personId
     * @return 会员的集合
     */
    public List<Customer> getByPersonid(String personId);
    /**
     * 通过openId获取会员信息
     * 
     * @param openId
     * @return 会员的集合
     */
    public List<Customer> getByOpenid(String openId);
    
    /**
     * 根据会员id获取全部反馈信息，并按时间戳倒序
     * @param cId
     * @return 反馈集合
     */
    public List<Feedback> getFeedbackByCustomerId(int cId);
    
    /**
     * 根据会员id获取推荐商品信息
     * @param cId
     * @return 商品的集合
     */
    public List<Good> getRecBycId(int cId);
    
    /**
     * 根据会员id获取最近购买
     * @param cId
     * @return 订单表项的集合
     */
    public List<OrderTableItem> getRecentOrderBycId(int cId);
    
    /**
     * 根据会员id获取用户经常购买的商品
     * @param cId
     * @return 商品的集合
     */
    public List<MostPurchasedGood> getCustomerLoved(int cId);

    /**
     * 绑定会员的微信
     *
     * @param customer  会员
     * @param openId 微信openid
     * @return
     */
    public boolean bindCustomer(Customer customer, String openId);

    /**
     * 通过微信注册会员
     * @param customer
     * @return
     */
    public boolean registerCustomerByWx(Customer customer);

    /**
     * 微信修改用户密码，通过openid
     * @param openid
     * @param password
     * @return
     */
    public boolean modifyPasswordByOpenid(String openid, String password);

    /**
     * 微信，开启人脸识别，通过openid添加personid
     *
     * @param openid
     * @param personid
     * @return
     */
    public boolean modifyPersonidByOpenid(String openid, String personid);

    /**
     * 
     * @Title: importCSV
     * @Description: 批量导入会员
     * @param fileName
     * @return
     */
    public boolean importCSV(String fileName);

    public List<CustomerTableItem> getCustomerPaging(DataTableRequest dtr);

    /**
     * 异步验证是否可以修改会员信息
     * @param tel
     * @param c_id
     * @return 可以修改返回true
     */
    public boolean CheckCustomerModify(String tel, String c_id);
    
    /**
     * 
     * @Title: UpdateRecBycId
     * @Description: TODO
     * @param id
     * @param rec
     * @return
     */
    public boolean UpdateRecBycId(int id, String rec);
}
