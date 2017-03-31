package edu.ts.entity;

public class FeedbackItem {

public FeedbackItem(String cName,Feedback feedback) {
		super();
		this.cName = cName;
		this.feedback = feedback;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	private String cName;

	private Feedback feedback;


}
