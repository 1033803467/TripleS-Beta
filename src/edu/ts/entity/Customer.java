package edu.ts.entity;

import java.util.Date;

/**
* # 用户表
CREATE TABLE customers (
  c_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, 	# 会员id,不可修改
  c_name varchar(20) NOT NULL, 			# 会员名字,不可修改
  c_password varchar(20) DEFAULT '123456',# 会员密码，可以在微信端修改密码界面修改
  c_birth date DEFAULT NULL,				# 会员出生日期,不可修改
  c_gender int(1) NOT NULL,				# 会员性别,不可修改 0表示男性，1表示女性
  c_email varchar(30) NOT NULL,			# 会员邮箱，可以修改
  c_tel varchar(20) NOT NULL,		    # 会员电话，可以修改
  c_score int(4) NOT NULL DEFAULT '0',	# 会员积分,不可修改
  c_personid varchar(255) DEFAULT NULL,   # 会员personid，可以修改
  c_openid varchar(255) DEFAULT NULL,     # 会员微信号openid，可以修改
  c_pic varchar(255) DEFAULT NULL,        # 会员头像地址，不可修改
  c_rec varchar(255) DEFAULT NULL,		    # 推荐内容为id1,id2,id3,id4，可以修改 ，没有写在Customer实体类中，通过CustomerService的getRecBycId()可以获得
  c_isrec int(1) DEFAULT 0,               #是否接受推荐，0表示不接受
  c_state int(1) DEFAULT 1,               #会员状态，1表示激活，0表示已删除
  c_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP #会员创建时间
) ENGINE=InnoDB DEFAULT CHARSET=utf8;  
 * Created by jose on 2017/3/2.
 */
public class Customer {
    private int cId;

    private String cName;

    private int cGender;

    private Date cBirth;

    private String cTel;

    private String cMail;

    private String cPic;

    private int cScore;

    private String cPassword;
    
    private String cOpenid;
    
    private String cPersonid;
    
    private String cRec;
    
    private int cIsrec;
    
    private int cState;
    
    private String cDate;
    

    public Customer() {
		super();
	}

    
    
    public Customer(int cId, String cName, int cGender, Date cBirth,
			String cTel, String cMail, String cPic, int cScore,
			String cPassword, String cOpenid, String cPersonid, String cRec,
			int cIsrec, int cState, String cDate) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cGender = cGender;
		this.cBirth = cBirth;
		this.cTel = cTel;
		this.cMail = cMail;
		this.cPic = cPic;
		this.cScore = cScore;
		this.cPassword = cPassword;
		this.cOpenid = cOpenid;
		this.cPersonid = cPersonid;
		this.cRec = cRec;
		this.cIsrec = cIsrec;
		this.cState = cState;
		this.cDate = cDate;
	}



	public Customer(String cName, int cGender, String cTel) {
		super();
		this.cName = cName;
		this.cGender = cGender;
		this.cTel = cTel;
	}
    /**
     * 增加用户时调用
     * 
     * @param cName
     * @param cgender
     * @param cTel
     * @param cMail
     * @param birth
     */
    public Customer(String cName, int cgender, String cTel, String cMail,Date birth) {
    	this.cName=cName;
    	this.cGender=cgender;
    	this.cTel=cTel;
    	this.cMail=cMail;
    	this.cBirth=birth;
    }

	public Customer(int cId, String cName, int cGender, String cTel) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cGender = cGender;
		this.cTel = cTel;
	}

	/**
	 * 修改用户时调用
	 * 
	 * @param parseInt
	 * @param name
	 * @param tel
	 * @param email
	 * @param date
	 */
	public Customer(int parseInt, String name, String tel, String email,Date date) {
		this.cId=parseInt;
		this.cName=name;
		this.cTel=tel;
		this.cMail=email;
		this.cBirth=date;
	}

	public Customer(int cId, String cName, int cGender, Date cBirth,String cTel, String cMail, String cPic, int cScore,String cPassword) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cGender = cGender;
		this.cBirth = cBirth;
		this.cTel = cTel;
		this.cMail = cMail;
		this.cPic = cPic;
		this.cScore = cScore;
		this.cPassword = cPassword;
	}

	//web注册
    public Customer(String cName, int cGender, Date cBirth, String cTel, String cMail,int cScore, String cPassword) {
        this.cName = cName;
        this.cGender = cGender;
        this.cBirth = cBirth;
        this.cTel = cTel;
        this.cMail = cMail;
        this.cScore = cScore;
        this.cPassword = cPassword;
    }

    //微信注册
    public Customer(String cName, int cGender, Date cBirth, String cTel, String cMail, String cPassword , String cOpenid) {
        this.cName = cName;
        this.cGender = cGender;
        this.cBirth = cBirth;
        this.cTel = cTel;
        this.cMail = cMail;
        this.cPassword = cPassword;
        this.cOpenid = cOpenid;
    }

    public Customer(String cName, int cGender, Date cBirth, String cTel,String cMail, int cScore) {
    	this.cName = cName;
        this.cGender = cGender;
        this.cBirth = cBirth;
        this.cTel = cTel;
        this.cMail = cMail;
        this.cScore = cScore;
	}

	public Customer(int cId, String cName, int cGender, Date cBirth,
			String cTel, String cMail, String cPic, int cScore,
			String cPassword, String cOpenid, String cPersonid, int cIsrec,
			int cState, String cDate) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cGender = cGender;
		this.cBirth = cBirth;
		this.cTel = cTel;
		this.cMail = cMail;
		this.cPic = cPic;
		this.cScore = cScore;
		this.cPassword = cPassword;
		this.cOpenid = cOpenid;
		this.cPersonid = cPersonid;
		this.cIsrec = cIsrec;
		this.cState = cState;
		this.cDate = cDate;
	}

	

	public String getcRec() {
		return cRec;
	}



	public void setcRec(String cRec) {
		this.cRec = cRec;
	}



	public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcGender() {
        return cGender;
    }

    public void setcGender(int cGender) {
        this.cGender = cGender;
    }

    public Date getcBirth() {
        return cBirth;
    }

    public void setcBirth(Date cBirth) {
        this.cBirth = cBirth;
    }

    public String getcTel() {
        return cTel;
    }

    public void setcTel(String cTel) {
        this.cTel = cTel;
    }

    public String getcMail() {
        return cMail;
    }

    public String getcPic() {
		return cPic;
	}

	public void setcPic(String cPic) {
		this.cPic = cPic;
	}

	public void setcMail(String cMail) {
        this.cMail = cMail;
    }

    public int getcScore() {
        return cScore;
    }

    public void setcScore(int cScore) {
        this.cScore = cScore;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }



	public String getcOpenid() {
		return cOpenid;
	}



	public void setcOpenid(String cOpenid) {
		this.cOpenid = cOpenid;
	}



	public String getcPersonid() {
		return cPersonid;
	}



	public void setcPersonid(String cPersonid) {
		this.cPersonid = cPersonid;
	}


	public int getcIsrec() {
		return cIsrec;
	}


	public void setcIsrec(int cIsrec) {
		this.cIsrec = cIsrec;
	}


	public int getcState() {
		return cState;
	}


	public void setcState(int cState) {
		this.cState = cState;
	}


	public String getcDate() {
		return cDate;
	}


	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
    
}
