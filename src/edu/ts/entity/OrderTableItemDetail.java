package edu.ts.entity;
/**
 * 用于消费明细列表的显示类，每行显示商品数量，商品名，商品类别，商品品牌，商品单价，小计
 * 通过消费记录编号读取orderDetail表，通过商品id获取商品信息：商品名，商品类别，商品品牌，商品单价
 * 读取orderDetail表的相关记录，得到商品数量
 * 根据单价和数量统计每条明细的金额小计
 * @author zmnerd
 *
 */
public class OrderTableItemDetail {
	
	private int quantity;
	
	private String gName;
	
	private String gCatagory;
	
	private String gBrand;
	
	private String gSpecification;
	
	private double itemsAmount;
	
	public OrderTableItemDetail(int quantity, String gName, String gCatagory,
			String gBrand, String gSpecification, double itemsAmount) {
		super();
		this.quantity = quantity;
		this.gName = gName;
		this.gCatagory = gCatagory;
		this.gBrand = gBrand;
		this.gSpecification = gSpecification;
		this.itemsAmount = itemsAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgCatagory() {
		return gCatagory;
	}

	public void setgCatagory(String gCatagory) {
		this.gCatagory = gCatagory;
	}

	public String getgBrand() {
		return gBrand;
	}

	public void setgBrand(String gBrand) {
		this.gBrand = gBrand;
	}

	public String getgSpecification() {
		return gSpecification;
	}

	public void setgSpecification(String gSpecification) {
		this.gSpecification = gSpecification;
	}

	public double getItemsAmount() {
		return itemsAmount;
	}

	public void setItemsAmount(double itemsAmount) {
		this.itemsAmount = itemsAmount;
	}
	
	
}
