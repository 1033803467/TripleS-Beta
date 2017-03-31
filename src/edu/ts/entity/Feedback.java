package edu.ts.entity;

/**
 *# 反馈表
create table feedbacks (
	f_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,	     # 反馈项id
	c_id INT NOT NULL,								     # 会员id
	f_message text NOT NULL,							 # 反馈信息
	f_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, # 反馈时间
	f_state int(1)									     # 反馈状态 (待处理 / 已解决)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * @author zmnerd
 *
 */
public class Feedback {
	
	private int fId;
	
	private int cId;
	
	private String fMessage;
	
	private String fDate;
	
	private int fState;
	
	public Feedback(int fId, int cId, String fMessage, String fDate, int fState) {
		super();
		this.fId = fId;
		this.cId = cId;
		this.fMessage = fMessage;
		this.fDate = fDate;
		this.fState = fState;
	}
	
	
	public Feedback(int cId,String fMessage){
		this.cId = cId;
		this.fMessage = fMessage;
	}
	
	
	public Feedback() {
		super();
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getfMessage() {
		return fMessage;
	}

	public void setfMessage(String fMessage) {
		this.fMessage = fMessage;
	}

	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	public int getfState() {
		return fState;
	}

	public void setfState(int fState) {
		this.fState = fState;
	}
	
	
	

}
