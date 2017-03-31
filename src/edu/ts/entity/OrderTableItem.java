package edu.ts.entity;

/**
 * 用于消费记录列表的显示类，每行显示消费记录编号，客户名，消费时间，消费门店，消费金额
 * 消费记录编号读取o_id，格式化为定长字符
 * 客户名联表查询得到
 * 消费时间读取o_date时间戳，格式化字符串
 * 消费门店，数据库中没有数据，默认值
 * 消费金额，联表查询并统计
 * @author zmnerd
 *
 */
public class OrderTableItem {
	
	private int oId;
	
	private int cId;
	
	private String cName;
	
	private String oDate;
	
	private String address="独墅湖高教区分店";//数据库没有此信息，用于扩展
	
	private double amount;
	
	private int editId;

		
	public OrderTableItem(int oId, int cId, String cName, String oDate, double amount) {
		super();
		this.oId = oId;
		this.cId = cId;
		this.cName = cName;
		this.oDate = oDate;
		this.amount = amount;
	}

	public OrderTableItem(int oId, int cId, String cName, String oDate, double amount, int editId) {
		super();
		this.oId = oId;
		this.cId = cId;
		this.cName = cName;
		this.oDate = oDate;
		this.amount = amount;
		this.editId = editId;
	}

	public int getcId() {
		return cId;
	}



	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}



	public int getoId() {
		return oId;
	}


	public void setoId(int oId) {
		this.oId = oId;
	}


	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getoDate() {
		return oDate;
	}

	public void setoDate(String oDate) {
		this.oDate = oDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
