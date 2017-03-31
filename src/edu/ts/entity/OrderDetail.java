package edu.ts.entity;

/**
 * # 消费明细表
create table orderdetails (
	od_id int NOT NULL AUTO_INCREMENT,	            # 消费明细id
	o_id int NOT NULL,							    # 消费记录id
	od_quantity int NOT NULL,						# 商品数量
	g_id int NOT NULL,                              # 商品id
	PRIMARY KEY(od_id,o_id)							# 通过消费记录id查询消费明细，统计消费明细
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * @author zmnerd
 *
 */
public class OrderDetail {
	
	private int odId;
	
	private int oId;
	
	private int odQuantity;
	
	private int gId;

	public OrderDetail(int odId, int oId, int odQuantity, int gId) {
		super();
		this.odId = odId;
		this.oId = oId;
		this.odQuantity = odQuantity;
		this.gId = gId;
	}

	public int getOdId() {
		return odId;
	}

	public void setOdId(int odId) {
		this.odId = odId;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public int getOdQuantity() {
		return odQuantity;
	}

	public void setOdQuantity(int odQuantity) {
		this.odQuantity = odQuantity;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

}
