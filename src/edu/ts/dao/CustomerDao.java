package edu.ts.dao;

import edu.ts.entity.CustomerTableItem;
import edu.ts.entity.DataTableRequest;
import edu.ts.entity.Page;
import edu.ts.entity.Customer;

import java.util.List;

/**
 * 
 * @ClassName: CustomerDao
 * @Description: TODO
 * @author zmnerd
 * @date 2017-3-16 下午3:07:42
 *
 */
public interface CustomerDao {
    /**
     * 添加会员
     *
     * @param customer 会员
     * @return 会员id
     */
    public boolean addCustomer(Customer customer);

    /**
     * 微信端添加会员
     *
     * @param customer 会员
     * @return 会员id
     */
    public boolean addCustomerWeixin(Customer customer);
    
    /**
     * 通过会员Id删除会员
     *
     * @param customer
     * @return 成功true 失败false
     */
    public boolean deleteCustomer(int cId);

    /**
     * 修改会员
     *
     * @param customer
     * @return 成功true 失败false
     */
    public boolean modifyCustomer(Customer customer);

    /**
     * 微信端修改会员
     *
     * @param customer
     * @return 成功true 失败false
     */
    public boolean modifyCustomerWeixin(Customer customer);

    /**
     * 获取所有会员
     *
     * @return 会员的集合
     */
    public List<Customer> getCustomerList();

    /**
     * 通过用户手机号查询用户
     *
     * @param cTel 用户手机号
     * @return 会员的集合
     */
    public List<Customer> getCustomerByTel(String cTel);

    /**
     *通过id获取会员
     *
     * @param id
     * @return 会员的集合
     */
    public List<Customer> getCustomerById(int id);
    
    /**
     * 通过personId获取会员信息
     * 
     * @param personId
     * @return 会员的集合
     */
    public List<Customer> getCustomerByPersonid(String personId);
    /**
     * 通过openId获取会员信息
     * 
     * @param openId
     * @return 会员的集合
     */
    public List<Customer> getCustomerByOpenid(String openId);
    /**
     *通过Page获取会员集合
     *
     * @param page
     * @return 会员的集合，参数为null时返回总记录数
     */
    public List<CustomerTableItem> getCustomerByPage(Page page);
    
    public List<CustomerTableItem> getCustomerPaging(DataTableRequest dtr);
    
    /**
     *异步验证是否可以修改会员信息
     *
     * @param tel 会员电话
     * @param c_id  会员id
     * @return 可以修改返回true，否则返回false
     */
    public boolean CheckCustomerModify(String tel, String c_id);
    
    public boolean UpdateRec(int cid, String rec);
}
