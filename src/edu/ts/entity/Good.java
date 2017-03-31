package edu.ts.entity;

/**
 * # 商品表
create table goods (
	g_id int NOT NULL AUTO_INCREMENT primary key,	# 商品id
	g_catagory varchar(100) NOT NULL,				# 商品种类
	g_name varchar(50) NOT NULL,					# 商品名
	g_price double(10, 2) NOT NULL,					# 商品单价
	g_pic varchar(255) NOT NULL,					# 商品图片地址
	g_desc varchar(255) NOT NULL,				    # 商品简介
	g_specification varchar(100) NOT NULL,          # 商品规格
	g_brand varchar(100) NOT NULL 					# 品牌
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * @author zmnerd
 *
 */
public class Good {
	private int gId;
	
	private String gCatagory;
	
	private String gName;
	
	private double gPrice;
	
	private String gPic;
	
	private String gDesc;
	
	private String gSpecification;
	
	private String gBrand;


	public Good(int gId, String gCatagory, String gName, double gPrice,
			String gPic, String gDesc, String gSpecification, String gBrand) {
		super();
		this.gId = gId;
		this.gCatagory = gCatagory;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gPic = gPic;
		this.gDesc = gDesc;
		this.gSpecification = gSpecification;
		this.gBrand = gBrand;
	}

	public Good() {
		super();
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public String getgCatagory() {
		return gCatagory;
	}

	public void setgCatagory(String gCatagory) {
		this.gCatagory = gCatagory;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public String getgPic() {
		return gPic;
	}

	public void setgPic(String gPic) {
		this.gPic = gPic;
	}

	public String getgDesc() {
		return gDesc;
	}

	public void setgDesc(String gDesc) {
		this.gDesc = gDesc;
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
	
	
	
}
