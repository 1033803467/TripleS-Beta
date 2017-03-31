package edu.wx.message.template;


public class FeedbackTemplate {
	//接收userid
	private String touser;
	//模板id
	private String template_id;
	//url
	private String url;
	//标题颜色
	private String topcolor;
	//详细内容
	private Data data;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplateid() {
		return template_id;
	}
	public void setTemplateid(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	

}
