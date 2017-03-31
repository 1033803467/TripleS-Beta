package edu.ts.servlet;
/*
 * @ClassName:     RecommenderServlet
 * @Description:   异步计算推荐商品  customerDetail.jsp ajax异步请求，返回json数组
 *
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */

import edu.ts.entity.Good;
import edu.ts.service.CustomerService;
import edu.ts.service.RecommendService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.RecommendServiceImpl;
import edu.ts.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RecommenderServlet extends HttpServlet {

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
		int cid = Integer.parseInt(request.getParameter("cid"));
		PrintWriter out = response.getWriter();
		RecommendService rs = new RecommendServiceImpl();
		CustomerService cs = new CustomerServiceImpl();
		//推荐商品分析
		List<Good> listGood = rs.recommendBycId(cid);
		JSONArray jArray = new JSONArray();
		String rec = "";
		//推荐4个商品成功
		if (listGood != null && listGood.size() == 4) {
			for (Good good : listGood) {
				JSONObject jObject = new JSONObject();
				jObject.put("gpic", good.getgPic());
				jObject.put("ghref", "GoodDetailServlet?gid=" + good.getgId());
				jObject.put("gname", good.getgName());
				jObject.put("gbrand", good.getgBrand());
				jArray.put(jObject);
				rec=rec+good.getgId()+",";
			}
			//写入数据库推荐字段
			cs.UpdateRecBycId(cid, rec.substring(0, rec.length()-1));
			out.print(jArray.toString());
		} else {
			for (int i = 0; i < 4; i++) {
				JSONObject jObject = new JSONObject();
				jObject.put("gpic","jsp/images/loading.gif");
				jObject.put("ghref", "#");
				jObject.put("gname", "计算失败！");
				jObject.put("gbrand", "计算失败！");
				jArray.put(jObject);
			}
			out.print(jArray.toString());
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

}
