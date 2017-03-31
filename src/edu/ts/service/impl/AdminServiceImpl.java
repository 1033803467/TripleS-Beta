package edu.ts.service.impl;

import edu.ts.dao.AdminDao;
import edu.ts.dao.impl.AdminDaoImpl;
import edu.ts.entity.Admin;
import edu.ts.service.AdminService;

public class AdminServiceImpl implements AdminService {
	AdminDao ad = new AdminDaoImpl();
	public boolean login(String tel, String password)
	{
		return ad.findByTelAndPwd(tel, password);
	}
	
	public boolean register(Admin admin)
	{
		if(!ad.isExist(admin.getaTel())){
			return ad.add(admin);
		}else{
			return false;
		}
		
	}


	public boolean checkTel(String tel) {
		return ad.isExist(tel);
	}

	@Override
	public String getNameByTel(String tel) {
		return ad.getNameByTel(tel);
	}
}
