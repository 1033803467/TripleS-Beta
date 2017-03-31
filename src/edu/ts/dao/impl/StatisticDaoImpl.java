package edu.ts.dao.impl;
/**
 * @ClassName:     StatisticDaoImpl
 * @Description:   图表统计
 *
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;
import edu.ts.util.DBUtil;
import edu.ts.dao.BaseDao;


public class StatisticDaoImpl extends BaseDao implements edu.ts.dao.StatisticDao {
	
		/**
		 * 按月统计会员人数
		 *
		 * @return Map<String,String> 月份/当月会员人数映射。当月会员人数格式------"当月历史注册人数:当月有效会员人数"
		 * */
		/*/**
		 * @Description: ${todo}(这里用一句话描述这个方法的作用)
		 * @param: ${tags}
		 * @return: ${return_type}
		 * @throws
		 *
		 */
		@Override
		public  Map<String,String> CustomerStatisticByTime(){
			Connection conn = DBUtil.getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			if(conn!=null){
				try {
					//stmt用于查询最大最小记录和时间间隔结果集
					stmt=conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//返回数据，月份-数量映射
			Map<String,String> results = new TreeMap<String,String>();
			int maxMonth,minMonth,maxYear,minYear;

			String sqlMax="select MAX(c_date) AS MAX from customers";
			String sqlMin="select MIN(c_date) AS MIN  from customers";
					
			if(conn!=null && stmt!=null){
			try {
			
				//查询c_date最大记录
				ResultSet max = stmt.executeQuery(sqlMax);
				Timestamp maxTime=null;
				while(max.next()){
					//maxTime数据库中最大的时间戳
					maxTime = max.getTimestamp("MAX");
				}
				if(max!=null)max.close();
				//如果数据库没有记录，释放资源，直接返回null
				if(maxTime==null){
					DBUtil.close(conn,stmt,max);
					return null;
				}
				
				//查询c_date最小记录
				ResultSet min = stmt.executeQuery(sqlMin);
				Timestamp minTime=null;
				while(min.next()){
					//maxTime数据库中最小的时间戳
					 minTime = min.getTimestamp("MIN");
				}
				if(min!=null)min.close();

		        maxMonth = Integer.parseInt(maxTime.toString().substring(5,7));
		        minMonth = Integer.parseInt(minTime.toString().substring(5,7));
		        maxYear = Integer.parseInt(maxTime.toString().substring(0,4));
		        minYear = Integer.parseInt(minTime.toString().substring(0,4));

		        int monthInterval = 12*(maxYear-minYear)+(maxMonth-minMonth)+1;
				
				//按月查询的时间间隔起始 年，月。并初始化
				int yearLow=minYear;
				int monthLow=minMonth;
				int yearHigh=yearLow;
				int monthHigh=monthLow+1;

				//for循环查询每个月的会员人数信息
		        for(int i=0;i<monthInterval;i++){
		        	//如果月份超过12
		        	if(monthLow>12){
		        		//月份减为1
						monthLow-=12;
						//年份增加1
						yearLow++;
					}
		        	if(monthHigh>12){
						monthHigh-=12;
		        		yearHigh++;
		        	}
		        	//时间间隔起始点
		        	String timeLow=yearLow+"-"+monthLow+"-01 00:00:00";
					String timeHigh=yearHigh+"-"+monthHigh+"-01 00:00:00";
					//查询历史注册会员
					String sql="SELECT COUNT(c_date) AS NUM from customers where c_date>='"+timeLow+"'and c_date<'"+timeHigh+"'";
					//查询当前有效会员
					String sql_cur="SELECT COUNT(c_date) AS NUM from customers where c_date>='"+timeLow+"'and c_date<'"+timeHigh+"' and c_state=1";
					
					//统计历史会员人数
					rs=stmt.executeQuery(sql);
					while(rs.next()){
						String num=String.valueOf(rs.getInt("NUM"));
						results.put(yearLow+"-"+monthLow,num);
					}
					//统计当前会员人数
					rs=stmt.executeQuery(sql_cur);
					while(rs.next()){
						String num=String.valueOf(rs.getInt("NUM"));
						String temp = results.get(yearLow+"-"+monthLow);
						results.put(yearLow+"-"+monthLow,temp+":"+num);
					}


					monthLow++;
					monthHigh++;
		        }
		        
		        
			}
		    catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			catch(NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			finally{
				//最后释放资源
				DBUtil.close(conn,stmt,rs);
			}
			}
		        
			return results;
		}
		
		/**
		 * 按月统计订单
		 * 
		 * @return Map<String,Integer> 月份/当月订单数映射
		 * */
		@Override
		public Map<String,Integer> OrdersByMonth(){
			Connection conn = DBUtil.getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			if(conn!=null){
				try {
					//stmt用于查询最大最小记录和时间间隔结果集
					stmt=conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//返回数据，月份-数量映射
			Map<String,Integer> results = new TreeMap<String,Integer>();
			int maxMonth,minMonth,maxYear,minYear;

			String sqlMax="select MAX(o_date) AS MAX from orders";
			String sqlMin="select MIN(o_date) AS MIN  from orders";
					
			if(conn!=null && stmt!=null){
			try {
			
				//查询o_date最大记录
				ResultSet max = stmt.executeQuery(sqlMax);
				Timestamp maxTime=null;
				while(max.next()){
					//maxTime数据库中最大的时间戳
					maxTime = max.getTimestamp("MAX");
				}
				if(max!=null)max.close();
				//如果数据库没有记录，释放资源，直接返回null
				if(maxTime==null){
					DBUtil.close(conn,stmt,max);
					return null;
				}
				
				//查询o_date最小记录
				ResultSet min = stmt.executeQuery(sqlMin);
				Timestamp minTime=null;
				while(min.next()){
					//maxTime数据库中最小的时间戳
					 minTime = min.getTimestamp("MIN");
				}
				if(min!=null)min.close();

		        maxMonth = Integer.parseInt(maxTime.toString().substring(5,7));
		        minMonth = Integer.parseInt(minTime.toString().substring(5,7));
		        maxYear = Integer.parseInt(maxTime.toString().substring(0,4));
		        minYear = Integer.parseInt(minTime.toString().substring(0,4));
		
		        int monthInterval = 12*(maxYear-minYear)+(maxMonth-minMonth)+1;
				
				//按月查询的时间间隔起始 年，月。并初始化
				int yearLow=minYear;
				int monthLow=minMonth;
				int yearHigh=yearLow;
				int monthHigh=monthLow+1;

				//for循环查询每个月的订单数量
		        for(int i=0;i<monthInterval;i++){
		        	//如果月份超过12
		        	if(monthLow>12){
		        		//月份减为1
						monthLow-=12;
						//年份增加1
						yearLow++;
					}
		        	if(monthHigh>12){
						monthHigh-=12;
		        		yearHigh++;
		        	}
		        	//时间间隔起始点
		        	String timeLow=yearLow+"-"+monthLow+"-01 00:00:00";
					String timeHigh=yearHigh+"-"+monthHigh+"-01 00:00:00";
					//查询订单
					String sql="SELECT COUNT(o_date) AS NUM from orders where o_date>='"+timeLow+"'and o_date<'"+timeHigh+"'";
					
					//统计
					rs=stmt.executeQuery(sql);
					while(rs.next()){
						Integer num=rs.getInt("NUM");
						results.put(yearLow+"-"+monthLow,num);
					}
					monthLow++;
					monthHigh++;
		        }
		               
			}
		    catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			catch(NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			finally{
				//最后释放资源
				DBUtil.close(conn,stmt,rs);
			}
			}
		        
			return results;
		}
		
		
		/**
		 * 按月统计订单
		 * 
		 * @return Map<String,Integer> 月份/当月订单数映射
		 * */
		@Override
		public Map<String, Integer> MemberGenderPie(){
			Connection conn = DBUtil.getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			if(conn!=null){
				try {
					//stmt用于查询最大最小记录和时间间隔结果集
					stmt=conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//返回数据，性别-数量映射
			Map<String,Integer> results = new TreeMap<String,Integer>();
			String sqlMale="select count(c_gender) AS MALENUM from customers where c_gender=0";
			String sqlFemale="select count(c_gender) AS FEMALENUM  from customers where c_gender=1";
					
			if(conn!=null && stmt!=null){
			try {
			
				//查询o_date最大记录
				ResultSet maleRes = stmt.executeQuery(sqlMale);
				int maleNum=0;
				while(maleRes.next()){
					//maxTime数据库中最大的时间戳
					maleNum = maleRes.getInt("MALENUM");
				}
				if(maleRes!=null)maleRes.close();
				//如果数据库没有记录，释放资源，直接返回null
				
				
				//查询o_date最小记录
				ResultSet femaleRes = stmt.executeQuery(sqlFemale);
				int femaleNum=0;
				while(femaleRes.next()){
					//maxTime数据库中最小的时间戳
					femaleNum = femaleRes.getInt("FEMALENUM");
				}
				if(femaleRes!=null)femaleRes.close();
					results.put("男士",maleNum);
					results.put("女士",femaleNum);
		               
			}
		    catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			catch(NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			finally{
				//最后释放资源
				DBUtil.close(conn,stmt,rs);
			}
			}
		        
			return results;
			
		}

		
		/**
		 * 按微信/非微信会员统计人数
		 * 
		 * @return Map<String,Integer> 会员类型/人数映射
		 * */
		@Override
		public Map<String, Integer> MemberTypePie() {
			Connection conn = DBUtil.getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			if(conn!=null){
				try {
					//stmt用于查询最大最小记录和时间间隔结果集
					stmt=conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//返回数据，会员类型-数量映射
			Map<String,Integer> results = new TreeMap<String,Integer>();
			String sqlGeneral="select count(*) AS GENERAL_NUM from customers where c_openid IS NULL ";
			String sqlWeChat="select count(c_openid) AS WECHAT_NUM  from customers where c_openid IS NOT NULL ";
					
			if(conn!=null && stmt!=null){
			try {
			
				//查询o_date最大记录
				ResultSet gRes = stmt.executeQuery(sqlGeneral);
				int gNum=0;
				while(gRes.next()){
					//maxTime数据库中最大的时间戳
					gNum = gRes.getInt("GENERAL_NUM");
					//
					System.out.println("GENERAL_NUM"+gNum);
				}
				if(gRes!=null)gRes.close();
				//如果数据库没有记录，释放资源，直接返回null
				
				
				//查询o_date最小记录
				ResultSet weRes = stmt.executeQuery(sqlWeChat);
				int weNum=0;
				while(weRes.next()){
					//maxTime数据库中最小的时间戳
					weNum = weRes.getInt("WECHAT_NUM");
					System.out.println("WECHAT_NUM"+weNum);
				}
				if(weRes!=null)weRes.close();
					results.put("微信会员",weNum);
					results.put("非微信会员",gNum);
		               
			}
		    catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			catch(NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			finally{
				//最后释放资源
				DBUtil.close(conn,stmt,rs);
			}
			}
		        
			return results;
		}

		@Override
		public Map<String, Integer> SalesByMonth() {
			Connection conn = DBUtil.getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			if(conn!=null){
				try {
					//stmt用于查询最大最小记录和时间间隔结果集
					stmt=conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//返回数据，月份-数量映射
			Map<String,Integer> results = new TreeMap<String,Integer>();
			int maxMonth,minMonth,maxYear,minYear;

			String sqlMax="select MAX(o_date) AS MAX from orders";
			String sqlMin="select MIN(o_date) AS MIN  from orders";
					
			if(conn!=null && stmt!=null){
			try {
			
				//查询o_date最大记录
				ResultSet max = stmt.executeQuery(sqlMax);
				Timestamp maxTime=null;
				while(max.next()){
					//maxTime数据库中最大的时间戳
					maxTime = max.getTimestamp("MAX");
				}
				if(max!=null)max.close();
				//如果数据库没有记录，释放资源，直接返回null
				if(maxTime==null){
					DBUtil.close(conn,stmt,max);
					return null;
				}
				
				//查询c_date最小记录
				ResultSet min = stmt.executeQuery(sqlMin);
				Timestamp minTime=null;
				while(min.next()){
					//maxTime数据库中最小的时间戳
					 minTime = min.getTimestamp("MIN");
				}
				if(min!=null)min.close();

		        maxMonth = Integer.parseInt(maxTime.toString().substring(5,7));
		        minMonth = Integer.parseInt(minTime.toString().substring(5,7));
		        maxYear = Integer.parseInt(maxTime.toString().substring(0,4));
		        minYear = Integer.parseInt(minTime.toString().substring(0,4));

		        int monthInterval = 12*(maxYear-minYear)+(maxMonth-minMonth)+1;
				
				//按月查询的时间间隔起始 年，月。并初始化
				int yearLow=minYear;
				int monthLow=minMonth;
				int yearHigh=yearLow;
				int monthHigh=monthLow+1;

				//for循环查询每个月的会员人数信息
		        for(int i=0;i<monthInterval;i++){
		        	//如果月份超过12
		        	if(monthLow>12){
		        		//月份减为1
						monthLow-=12;
						//年份增加1
						yearLow++;
					}
		        	if(monthHigh>12){
						monthHigh-=12;
		        		yearHigh++;
		        	}
		        	//时间间隔起始点
		        	String timeLow=yearLow+"-"+monthLow+"-01 00:00:00";
					String timeHigh=yearHigh+"-"+monthHigh+"-01 00:00:00";
					//查询
					String sql="SELECT sum(a.b) as amount from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_id as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id ORDER BY o.o_date) as b on a.a=b.a WHERE b.d>='"+timeLow+"'and b.d<'"+timeHigh+"'";
					rs=stmt.executeQuery(sql);
					while(rs.next()){
						Integer num=(int) rs.getDouble("amount");
						System.out.println(num);
						results.put(yearLow+"-"+monthLow,num);
					}
					monthLow++;
					monthHigh++;
		        }
			}
		    catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			catch(NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			finally{
				//最后释放资源
				DBUtil.close(conn,stmt,rs);
			}
			}
		        
			return results;
		}

		@Override
		public Map<String, Integer> ConsumeByMonth(int cid) {
			Connection conn = DBUtil.getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			if(conn!=null){
				try {
					//stmt用于查询最大最小记录和时间间隔结果集
					stmt=conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//返回数据，月份-数量映射
			Map<String,Integer> results = new TreeMap<String,Integer>();
			int maxMonth,minMonth,maxYear,minYear;

			String sqlMax="select MAX(o_date) AS MAX from orders where c_id="+cid;
			String sqlMin="select MIN(o_date) AS MIN  from orders where c_id="+cid;
					
			if(conn!=null && stmt!=null){
			try {
			
				//查询o_date最大记录
				ResultSet max = stmt.executeQuery(sqlMax);
				Timestamp maxTime=null;
				while(max.next()){
					//maxTime数据库中最大的时间戳
					maxTime = max.getTimestamp("MAX");
				}
				if(max!=null)max.close();
				//如果数据库没有记录，释放资源，直接返回null
				if(maxTime==null){
					DBUtil.close(conn,stmt,max);
					return null;
				}
				
				//查询c_date最小记录
				ResultSet min = stmt.executeQuery(sqlMin);
				Timestamp minTime=null;
				while(min.next()){
					//maxTime数据库中最小的时间戳
					 minTime = min.getTimestamp("MIN");
				}
				if(min!=null)min.close();

		        maxMonth = Integer.parseInt(maxTime.toString().substring(5,7));
		        minMonth = Integer.parseInt(minTime.toString().substring(5,7));
		        maxYear = Integer.parseInt(maxTime.toString().substring(0,4));
		        minYear = Integer.parseInt(minTime.toString().substring(0,4));

		        int monthInterval = 12*(maxYear-minYear)+(maxMonth-minMonth)+1;
				
				//按月查询的时间间隔起始 年，月。并初始化
				int yearLow=minYear;
				int monthLow=minMonth;
				int yearHigh=yearLow;
				int monthHigh=monthLow+1;

				//for循环查询每个月的会员人数信息
		        for(int i=0;i<monthInterval;i++){
		        	//如果月份超过12
		        	if(monthLow>12){
		        		//月份减为1
						monthLow-=12;
						//年份增加1
						yearLow++;
					}
		        	if(monthHigh>12){
						monthHigh-=12;
		        		yearHigh++;
		        	}
		        	//时间间隔起始点
		        	String timeLow=yearLow+"-"+monthLow+"-01 00:00:00";
					String timeHigh=yearHigh+"-"+monthHigh+"-01 00:00:00";
					//查询
					String sql="SELECT sum(a.b) as amount from (select od.o_id as a,sum(od.od_quantity*g.g_price) as b from orderdetails od JOIN goods g on od.g_id = g.g_id GROUP BY od.o_id) as a JOIN (SELECT o.o_id as a,c.c_name as b,c.c_id as c,o.o_date as d from customers c JOIN orders o on c.c_id = o.c_id ORDER BY o.o_date) as b on a.a=b.a WHERE b.d>='"+timeLow+"'and b.d<'"+timeHigh+"' and b.c="+cid;
					rs=stmt.executeQuery(sql);
					while(rs.next()){
						Integer num=(int) rs.getDouble("amount");
						System.out.println(num);
						results.put(yearLow+"-"+monthLow,num);
					}
					monthLow++;
					monthHigh++;
		        }
			}
		    catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			catch(NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			finally{
				//最后释放资源
				DBUtil.close(conn,stmt,rs);
			}
			}
		        
			return results;
		}

		@Override
		public Map<String, String> FeedbackByMonth() {
			Connection conn = DBUtil.getConnection();
			Statement stmt = null;
			ResultSet rs = null;
			if(conn!=null){
				try {
					//stmt用于查询最大最小记录和时间间隔结果集
					stmt=conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//返回数据，月份-数量映射
			Map<String,String> results = new TreeMap<String,String>();
			int maxMonth,minMonth,maxYear,minYear;

			String sqlMax="select MAX(f_date) AS MAX from feedbacks";
			String sqlMin="select MIN(f_date) AS MIN  from feedbacks";
					
			if(conn!=null && stmt!=null){
			try {
			
				//查询o_date最大记录
				ResultSet max = stmt.executeQuery(sqlMax);
				Timestamp maxTime=null;
				while(max.next()){
					//maxTime数据库中最大的时间戳
					maxTime = max.getTimestamp("MAX");
				}
				if(max!=null)max.close();
				//如果数据库没有记录，释放资源，直接返回null
				if(maxTime==null){
					DBUtil.close(conn,stmt,max);
					return null;
				}
				
				//查询c_date最小记录
				ResultSet min = stmt.executeQuery(sqlMin);
				Timestamp minTime=null;
				while(min.next()){
					//maxTime数据库中最小的时间戳
					 minTime = min.getTimestamp("MIN");
				}
				if(min!=null)min.close();

		        maxMonth = Integer.parseInt(maxTime.toString().substring(5,7));
		        minMonth = Integer.parseInt(minTime.toString().substring(5,7));
		        maxYear = Integer.parseInt(maxTime.toString().substring(0,4));
		        minYear = Integer.parseInt(minTime.toString().substring(0,4));

		        int monthInterval = 12*(maxYear-minYear)+(maxMonth-minMonth)+1;
				
				//按月查询的时间间隔起始 年，月。并初始化
				int yearLow=minYear;
				int monthLow=minMonth;
				int yearHigh=yearLow;
				int monthHigh=monthLow+1;

				//for循环查询每个月的会员人数信息
		        for(int i=0;i<monthInterval;i++){
		        	//如果月份超过12
		        	if(monthLow>12){
		        		//月份减为1
						monthLow-=12;
						//年份增加1
						yearLow++;
					}
		        	if(monthHigh>12){
						monthHigh-=12;
		        		yearHigh++;
		        	}
		        	//时间间隔起始点
		        	String timeLow=yearLow+"-"+monthLow+"-01 00:00:00";
					String timeHigh=yearHigh+"-"+monthHigh+"-01 00:00:00";
					//查询
					String sql="SELECT count(*) as count from feedbacks WHERE f_date>='"+timeLow+"' and f_date<'"+timeHigh+"'";
					String sqlUnprocessed="SELECT count(*) as count from feedbacks WHERE f_date>='"+timeLow+"' and f_date<'"+timeHigh+"' and f_state=0";
					rs=stmt.executeQuery(sql);
					while(rs.next()){
						String num=String.valueOf(rs.getInt("count"));
						System.out.println(num);
						results.put(yearLow+"-"+monthLow,num);
					}
					rs=stmt.executeQuery(sqlUnprocessed);
					while(rs.next()){
						String num=String.valueOf(rs.getInt("count"));
						System.out.println(num);
						String temp = results.get(yearLow+"-"+monthLow);
						results.put(yearLow+"-"+monthLow,temp+":"+num);
					}
					monthLow++;
					monthHigh++;
		        }
			}
		    catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			catch(NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				results=null;
			}
			finally{
				//最后释放资源
				DBUtil.close(conn,stmt,rs);
			}
		}
		        
			return results;
	}
}

