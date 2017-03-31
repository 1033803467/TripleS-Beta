package edu.ts.dao;

import java.util.List;

import edu.ts.entity.Admin;

/**
 * 
 * @ClassName: AdminDao
 * @Description: 实现管理员表的增删改查
 * @author zmnerd
 * @date 2017-3-16 下午2:11:11
 *
 */
public interface AdminDao {
	
	/**
	 * 
	 * @Title: add
	 * @Description: 增加管理员
	 * @param admin
	 * @return true or false
	 */
	boolean add(Admin admin);

	/**
	 * 
	 * @Title: del
	 * @Description: 删除管理员
	 * @param admin
	 * @return true or false
	 */
	boolean del(int aId);
	
	/**
	 * 
	 * @Title: update
	 * @Description: 修改管理员
	 * @param admin
	 * @return true or false
	 */
	boolean update(Admin admin);
	
	/**
	 * 
	 * @Title: findById
	 * @Description: 根据id找到Admin对象
	 * @param aId
	 * @return Admin对象
	 */
    Admin findById(int aId);
    
    /**
     * 
     * @Title: findByTelAndPwd
     * @Description: 查询手机号和密码是否匹配管理员表项,用于管理员登录
     * @param tel
     * @param password
     * @return
     */
    boolean findByTelAndPwd(String tel, String password);

    /**
     * 
     * @Title: findAll
     * @Description: 获取所有管理员信息
     * @return Admin列表
     */
    List<Admin> findAll();
    
    /**
     * 
     * @Title: isExist
     * @Description: 用于注册时的验证
     * @return true or false
     */
    boolean isExist(String tel);
    
    /**
     * 
     * @Title: getNameByTel
     * @Description: 根据电话号码得到用户名
     * @param tel
     * @return 用户名
     */
    String getNameByTel(String tel);
}
