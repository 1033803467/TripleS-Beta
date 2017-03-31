package edu.ts.service.impl;

import java.util.Map;

import edu.ts.dao.StatisticDao;
import edu.ts.dao.impl.StatisticDaoImpl;
import edu.ts.service.StatisticService;

public class StatisticServiceImpl implements StatisticService{

	public Map<String,String> CustomerNumByMonth() {
		Map<String,String> res = null;
		StatisticDaoImpl sdi = new StatisticDaoImpl();
		res = sdi.CustomerStatisticByTime();
		
		return res;
	}

	public Map<String,String> CustomerNumByQuarter() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> OrdersByMonth() {
		Map<String,Integer> res = null;
		StatisticDaoImpl sdi = new StatisticDaoImpl();
		res = sdi.OrdersByMonth();
		
		return res;
	
	}

	public Map<String, Integer> OrdersByQuarter() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> SalesByMonth() {
		Map<String,Integer> res = null;
		StatisticDaoImpl sdi = new StatisticDaoImpl();
		res = sdi.SalesByMonth();
		return res;
	}

	public Map<String, Integer> SalesByQuarter() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> MemberGenderPie() {
		Map<String,Integer> res = null;
		StatisticDaoImpl sdi = new StatisticDaoImpl();
		res = sdi.MemberGenderPie();
		
		return res;
	
	}
	
	
	public Map<String, Integer> MemberTypePie(){
		Map<String,Integer> res = null;
		StatisticDaoImpl sdi = new StatisticDaoImpl();
		res = sdi.MemberTypePie();
		
		return res;
	}

	@Override
	public Map<String, Integer> ConsumeByMonth(int cid) {
		Map<String,Integer> res = null;
		StatisticDao sdi = new StatisticDaoImpl();
		res = sdi.ConsumeByMonth(cid);
		return res;
	}

	@Override
	public Map<String, String> FeedbackByMonth() {
		Map<String,String> res = null;
		StatisticDaoImpl sdi = new StatisticDaoImpl();
		res = sdi.FeedbackByMonth();
		return res;
	}
	
	
	
}
