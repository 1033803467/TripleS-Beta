package edu.ts.service;

import edu.ts.entity.Admin;
/**
 * 提供管理员的登录验证和注册服务
 * @author zmnerd
 *
 */
public interface AdminService {
	
	/**
	 * 用于管理员的登录验证功能
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean login(String tel, String password);
	
	/**
	 * 用于管理员的注册功能
	 * @param admin
	 * @return
	 */
	public boolean register(Admin admin);

	public String getNameByTel(String tel);
}
