package edu.ts.entity;

/**
 * # 管理员表
  create table admins (
	a_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,	# 管理员id
	a_name int NOT NULL,							# 管理员姓名
	a_password varchar(20) NOT NULL,                # 管理员密码
	a_tel varchar(20) NOT NULL,                     # 管理员手机号
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * @author zmnerd
 *
 */
public class Admin {
	private int aId;

    private String aName;

    private String aPassword;

    private String aTel;

	public Admin(int aId, String aName, String aPassword, String aTel) {
		super();
		this.aId = aId;
		this.aName = aName;
		this.aPassword = aPassword;
		this.aTel = aTel;
	}
	public Admin(String aName,String aPassword,String aTel)
	{
		this.aName=aName;
		this.aPassword=aPassword;
		this.aTel=aTel;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

	public String getaTel() {
		return aTel;
	}

	public void setaTel(String aTel) {
		this.aTel = aTel;
	}

	
    
}
