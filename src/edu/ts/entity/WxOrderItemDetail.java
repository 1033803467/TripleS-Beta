package edu.ts.entity;

public class WxOrderItemDetail {
	
	private String gName;
	
	private String gPic;
	
	private String gSpecification;
	
	private double gPrice;
	
	private int quantity;
	
	private double itemsAmount;

	public WxOrderItemDetail(String gName, String gPic, String gSpecification,
			double gPrice, int quantity, double itemsAmount) {
		super();
		this.gName = gName;
		this.gPic = gPic;
		this.gSpecification = gSpecification;
		this.gPrice = gPrice;
		this.quantity = quantity;
		this.itemsAmount = itemsAmount;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgPic() {
		return gPic;
	}

	public void setgPic(String gPic) {
		this.gPic = gPic;
	}

	public String getgSpecification() {
		return gSpecification;
	}

	public void setgSpecification(String gSpecification) {
		this.gSpecification = gSpecification;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getItemsAmount() {
		return itemsAmount;
	}

	public void setItemsAmount(double itemsAmount) {
		this.itemsAmount = itemsAmount;
	}

}
