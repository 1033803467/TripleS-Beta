package edu.ts.entity;

import java.util.List;

public class WxOrderItem {
	
	private int oId;
	
	private String oDate;
	
	private double amount;
	
	private List<WxOrderItemDetail> detailList;

	public WxOrderItem(int oId, String oDate, double amount,
			List<WxOrderItemDetail> detailList) {
		super();
		this.oId = oId;
		this.oDate = oDate;
		this.amount = amount;
		this.detailList = detailList;
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public String getoDate() {
		return oDate;
	}

	public void setoDate(String oDate) {
		this.oDate = oDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<WxOrderItemDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<WxOrderItemDetail> detailList) {
		this.detailList = detailList;
	}
	
	

}
