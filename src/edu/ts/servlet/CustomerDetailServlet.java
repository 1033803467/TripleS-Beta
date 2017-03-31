package edu.ts.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.entity.Customer;
import edu.ts.entity.Feedback;
import edu.ts.entity.Good;
import edu.ts.entity.MostPurchasedGood;
import edu.ts.entity.OrderTableItem;
import edu.ts.service.CustomerService;
import edu.ts.service.RecommendService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.RecommendServiceImpl;
import edu.ts.util.StringUtil;

public class CustomerDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			int cid;
			CustomerService cs = new CustomerServiceImpl();
			RecommendService rs = new RecommendServiceImpl();
			
			if(request.getAttribute("cid")==null){
				cid = Integer.parseInt(request.getParameter("cid"));
				//flag = 1;
			}else{
				cid= (Integer)request.getAttribute("cid");
				
			}
			//用户信息获取
			List<Customer> listC=cs.getById(cid);
			try{
				request.setAttribute("customer", listC.get(0));
			}catch(IndexOutOfBoundsException e){
				request.setAttribute("customer", null);
			}
			//用户反馈信息的获取
			List<Feedback> listfs=cs.getFeedbackByCustomerId(cid);
			request.setAttribute("feeedbackList", listfs);
			
			//用户喜爱商品获取
			List<MostPurchasedGood> listMG=cs.getCustomerLoved(cid);
			request.setAttribute("mostGoodList", listMG);
			
			//用户最近消费获取
			List<OrderTableItem> listOrder=cs.getRecentOrderBycId(cid);
			request.setAttribute("OrderList", listOrder);
			
			//推荐商品分析
			//List<Good> listGood = rs.recommendBycId(cid);
			//request.setAttribute("RecommendList", listGood);
			
			//转发到customerDetail.jsp页面
			
			request.getRequestDispatcher("/jsp/customerDetail.jsp").forward(request, response);
			
			
	}

}
