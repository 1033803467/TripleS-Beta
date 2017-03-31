package edu.ts.entity;

/**
 * # 用户偏好表
create table preferences (
	c_id int NOT NULL,								# 会员id
	g_id int NOT NULL,								# 商品id
	p_score double NOT NULL,						# 用户购买次数（用户提交评价时插入）(当评价记录已存在时，覆盖内容)
	PRIMARY KEY(c_id,g_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * @author zmnerd
 *
 */
public class Preference {
	private int cId;
	
	private int gId;
	
	private double pScore;

	public Preference(int cId, int gId, double pScore) {
		super();
		this.cId = cId;
		this.gId = gId;
		this.pScore = pScore;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public double getpScore() {
		return pScore;
	}

	public void setpScore(double pScore) {
		this.pScore = pScore;
	}
	
	
}
