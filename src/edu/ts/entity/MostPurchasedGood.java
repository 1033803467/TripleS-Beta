package edu.ts.entity;

public class MostPurchasedGood {
	private int gId;
	
	private String gName;
	
	private String gPic;
	
	private String gBrand;
	
	private int purchaseCount;
	
	public MostPurchasedGood() {
		super();
	}

	public MostPurchasedGood(int gId, String gName, String gPic, String gBrand,
			int purchaseCount) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gPic = gPic;
		this.gBrand = gBrand;
		this.purchaseCount = purchaseCount;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
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

	public String getgBrand() {
		return gBrand;
	}

	public void setgBrand(String gBrand) {
		this.gBrand = gBrand;
	}

	public int getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	
	
	
}
