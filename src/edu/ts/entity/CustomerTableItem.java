package edu.ts.entity;

import java.util.Date;

public class CustomerTableItem {
	private int cId;

    private String cName;

    private String cGender;

    private Date cBirth;

    private String cTel;

    private String cMail;

    private String cPic;

    private int cScore;
    
    private int editId;

	public CustomerTableItem() {
		super();
	}

	public CustomerTableItem(int cId, String cName, int cGender,
			Date cBirth, String cTel, String cMail, String cPic, int cScore,
			int editId) {
		super();
		this.cId = cId;
		this.cName = cName;
		if(cGender==0){
			this.cGender = "男";
		}else{
			this.cGender = "女";
		}
		this.cBirth = cBirth;
		this.cTel = cTel;
		this.cMail = cMail;
		if(cPic==null){
			this.cPic = "./images/user.png";
		}else{
			this.cPic = cPic;
		}
		this.cScore = cScore;
		this.editId = editId;
	}
	
	public int getEditId() {
		return editId;
	}
	
	public void setEditId(int editId) {
		this.editId = editId;
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

	public String getcGender() {
		return cGender;
	}

	public void setcGender(int cGender) {
		if(cGender==0){
			this.cGender = "男";
		}else{
			this.cGender = "女";
		}
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

	public void setcMail(String cMail) {
		this.cMail = cMail;
	}

	public String getcPic() {
		return cPic;
	}

	public void setcPic(String cPic) {
		if(cPic==null){
			this.cPic = "./images/user.png";
		}else{
			this.cPic = cPic;
		}
	}

	public int getcScore() {
		return cScore;
	}

	public void setcScore(int cScore) {
		this.cScore = cScore;
	}

    
}
