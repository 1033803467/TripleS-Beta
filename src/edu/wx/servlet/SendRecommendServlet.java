package edu.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.ts.dao.GoodDao;
import edu.ts.dao.impl.GoodDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.Good;
import edu.ts.service.CustomerService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.util.AdvancedUtil;
import edu.ts.util.CommonUtil;
import edu.wx.message.resp.Article;
import edu.wx.pojo.WxInfo;

public class SendRecommendServlet extends HttpServlet {

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
		doPost(request, response);
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();		
		int cid = Integer.parseInt(request.getParameter("cid"));
		CustomerService customerService = new CustomerServiceImpl();
		Customer customer = customerService.getById(cid).get(0);
		GoodDao goodDao = new GoodDaoImpl();
		if(customer.getcRec()==null) out.print("false");
		else{
			String recommendGoodId = customer.getcRec();
			String[] rGoodIds = recommendGoodId.split(",");
			List<Good> goodList = new ArrayList<Good>();
			for(int i=0;i<rGoodIds.length;i++){
				//添加商品进list
				goodList.add(goodDao.getBygId(Integer.parseInt(rGoodIds[i])).get(0));
			}
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.append("touser", customer.getcOpenid());
//			jsonObject.append("msgtype", "news");
//			
//			JSONObject jsonTitle = new JSONObject();
//			jsonTitle.append("title","推荐商品");
//			jsonTitle.append("picurl","http://118.89.31.153:8080/recommendhead.jpg");
//			JSONArray jsonArray = new JSONArray();
//			jsonArray.put(jsonTitle);
//			for(int i=0;i<goodList.size();i++){
//				JSONObject goodJsonObject = new JSONObject();
//				goodJsonObject.append("title", goodList.get(i).getgName()+"  ￥"+goodList.get(i).getgPrice());
//				goodJsonObject.append("picurl",goodList.get(i).getgPic());
//				jsonArray.put(goodJsonObject);
//			}
//			jsonObject.append("articles", jsonArray);
			
			List<Article> list = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle("推荐商品");
			article.setUrl("http://118.89.31.153:8080/recommendhead.jpg");
			article.setPicUrl("http://118.89.31.153:8080/recommendhead.jpg");
			list.add(article);
			for(int i=0;i<goodList.size();i++){
				Article article2 = new Article();
				article2.setTitle(goodList.get(i).getgName()+"  ￥"+goodList.get(i).getgPrice());
				article2.setUrl(goodList.get(i).getgPic());
				article2.setPicUrl(goodList.get(i).getgPic());
				list.add(article2);
			}
			String accessToken = CommonUtil.getToken(WxInfo.getAppId(), WxInfo.getAppsecret()).getAccessToken();
			String jsonTextMsg = AdvancedUtil.makeNewsCustomMessage(customer.getcOpenid(), list);
			AdvancedUtil.sendCustomMessage(accessToken, jsonTextMsg);
			out.print("true");
		}
	}
}
