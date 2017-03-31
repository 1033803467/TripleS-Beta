package edu.ts.servlet;
/*/**
 * @ClassName:     ChartServlet
 * @Description:   图表异步加载servlet，用于index.jsp ajax请求，返回json数组
 *
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */
import edu.ts.service.StatisticService;
import edu.ts.service.impl.StatisticServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ChartServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChartServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		//获取ajax请求的图表类型
		int type = Integer.parseInt(request.getParameter("type"));
		//实例化统计服务类
		StatisticService ss= new StatisticServiceImpl();
		
		System.out.println("type"+type);
		//根据请求的图表类型做判断
		switch(type){
		//时间-历史注册的会员人数
		case 1:{
			System.out.println("in case 1");
			//获得月-会员人数映射
			Map<String,String> res = ss.CustomerNumByMonth();
			JSONArray jArray = new JSONArray();
			if(res!=null&&res.size()!=0){
				//构造json数组
				for (Map.Entry<? super String,? super String> entry : res.entrySet()) {
					JSONObject jObject = new JSONObject();
					jObject.put("time", entry.getKey());

					String time=(String)entry.getValue();
					int index = time.indexOf(":");
					String Num = time.substring(0,index);
					String Num_cur = time.substring(index+1);
					//System.out.println("time"+time+"  num:"+Num+"  num_cur:"+Num_cur);
					jObject.put("num",Num);
					jObject.put("num_cur",Num_cur);
					jArray.put(jObject);
				}
				out.print(jArray.toString());
			}
			else{
				JSONObject jObject = new JSONObject();
				jObject.put("time", "无数据");
				jObject.put("num","0");
				jObject.put("num_cur","0");

				jArray.put(jObject);
				out.print(jArray.toString());
			}

			break;
		}
		case 2:{
			//获得月-会员人数映射
			Map<String,Integer> res = ss.OrdersByMonth();
			JSONArray jArray = new JSONArray();
			if(res!=null&&res.size()!=0){
				//构造json数组
				for (Map.Entry<? super String,? super Integer> entry : res.entrySet()) {
					JSONObject jObject = new JSONObject();
					jObject.put("time", entry.getKey());
					jObject.put("o_num",entry.getValue());
					jArray.put(jObject);
				}
				out.print(jArray.toString());
			}
			else{
				JSONObject jObject = new JSONObject();
				jObject.put("time", "无数据");
				jObject.put("o_num",0);
				jArray.put(jObject);
				out.print(jArray.toString());
			}
			break;
			
		}
		case 3:{
			//获得月-会员人数映射
			Map<String,Integer> res = ss.MemberGenderPie();
			JSONArray jArray = new JSONArray();
			if(res!=null && res.size()!=0){
				//构造json数组
				for (Map.Entry<? super String,? super Integer> entry : res.entrySet()) {
					JSONObject jObject = new JSONObject();
					jObject.put("sex", entry.getKey());
					jObject.put("num",entry.getValue());
					jArray.put(jObject);
				}
				out.print(jArray.toString());
			}
			else{
				JSONObject jObject = new JSONObject();
				jObject.put("sex", "男");
				jObject.put("num",0);
				jObject.put("sex", "女");
				jObject.put("num",0);
				jArray.put(jObject);
				out.print(jArray.toString());

			}
			break;
			
		}
		case 4:{
			//获得月-会员人数映射
			Map<String,Integer> res = ss.MemberTypePie();
			JSONArray jArray = new JSONArray();
			if(res!=null && res.size()!=0){
				//构造json数组
				for (Map.Entry<? super String,? super Integer> entry : res.entrySet()) {
					JSONObject jObject = new JSONObject();
					jObject.put("memType", entry.getKey());// '非微信会员'
					jObject.put("num",entry.getValue());
					jArray.put(jObject);
				}
				out.print(jArray.toString());
			}
			else{
				JSONObject jObject = new JSONObject();
				jObject.put("memType", "非微信会员");// 非微信会员
				jObject.put("num",0);
				jObject.put("memType", "微信会员");// 非微信会员
				jObject.put("num",0);
				jArray.put(jObject);
				out.print(jArray.toString());

			}
			break;
			
		}
		case 5:{
			//获得月-会员人数映射
			Map<String,Integer> res = ss.SalesByMonth();
			JSONArray jArray = new JSONArray();
			if(res!=null && res.size()!=0){
				//构造json数组
				for (Map.Entry<? super String,? super Integer> entry : res.entrySet()) {
					JSONObject jObject = new JSONObject();
					jObject.put("month", entry.getKey());
					jObject.put("sales",entry.getValue());
					jArray.put(jObject);
				}
				out.print(jArray.toString());
			}
			else {
				JSONObject jObject = new JSONObject();
				jObject.put("month", "无数据");
				jObject.put("sales",0);
				jArray.put(jObject);
				out.print(jArray.toString());
			}
			break;
		}
		case 6:{
			int cid = Integer.parseInt(request.getParameter("cid"));
			//获得月-会员人数映射
			Map<String,Integer> res = ss.ConsumeByMonth(cid);
			JSONArray jArray = new JSONArray();
			if(res!=null  && res.size()!=0){
				//构造json数组
				for (Map.Entry<? super String,? super Integer> entry : res.entrySet()) {
					JSONObject jObject = new JSONObject();
					jObject.put("month", entry.getKey());
					jObject.put("consume",entry.getValue());
					jArray.put(jObject);
				}
				out.print(jArray.toString());
			}
			else{

				JSONObject jObject = new JSONObject();
				jObject.put("month", "无数据");
				jObject.put("consume",0);
				jArray.put(jObject);
				out.print(jArray.toString());
			}

			break;
		}
		case 7:{
			//获得月-会员人数映射
			Map<String,String> res = ss.FeedbackByMonth();
			JSONArray jArray = new JSONArray();
			if(res!=null&& res.size()!=0){
				//构造json数组
				for (Map.Entry<? super String,? super String> entry : res.entrySet()) {
					JSONObject jObject = new JSONObject();
					jObject.put("month", entry.getKey());
					String time=(String)entry.getValue();
					int index = time.indexOf(":");
					String count = time.substring(0,index);
					String countUnprocessed = time.substring(index+1);
					jObject.put("feedback",count);
					jObject.put("feedback_unprocessed",countUnprocessed);
					jArray.put(jObject);
				}
				out.print(jArray.toString());
			}
			else{
				JSONObject jObject = new JSONObject();
				jObject.put("month", "无数据" );

				jObject.put("feedback","0");
				jObject.put("feedback_unprocessed","0");
				jArray.put(jObject);
				out.print(jArray.toString());


			}
			break;
		}
		default:{}
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
