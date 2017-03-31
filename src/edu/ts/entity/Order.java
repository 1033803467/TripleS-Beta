package edu.ts.entity;

/**
 * # 消费记录表  
create table orders (
	o_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,	# 消费记录id
	c_id int NOT NULL, 								# 会员id
	o_date timestamp NOT NULL                       # 消费时间
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * @author zmnerd
 *
 */
public class Order {
	private int oId;
	
	private int cId;
	
	private String oDate;

	public Order(int oId, int cId, String oDate) {
		super();
		this.oId = oId;
		this.cId = cId;
		this.oDate = oDate;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getoDate() {
		return oDate;
	}

	public void setoDate(String oDate) {
		this.oDate = oDate;
	}
	
}
