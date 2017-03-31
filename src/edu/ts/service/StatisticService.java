package edu.ts.service;

import java.util.Map;

public interface StatisticService {
	/**
	 * 用户人数统计  按月/季度
	 * */
	public Map<String,String> CustomerNumByMonth();
	
	public Map<String,String> CustomerNumByQuarter();
	
	public Map<String,Integer> OrdersByMonth();
	
	public Map<String,Integer> OrdersByQuarter();
	
	public Map<String,Integer> SalesByMonth();
	
	public Map<String,Integer> SalesByQuarter();
	
	public Map<String,Integer> MemberGenderPie();
	
	public Map<String, Integer> MemberTypePie();
	
	public Map<String, Integer> ConsumeByMonth(int cid);
	
	public Map<String, String> FeedbackByMonth();
	
}
