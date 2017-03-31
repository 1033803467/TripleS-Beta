package edu.ts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ts.entity.Customer;
import edu.ts.entity.Feedback;
import edu.ts.entity.FeedbackItem;
import edu.ts.service.CustomerService;
import edu.ts.service.FeedbackService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.FeedbackServiceImpl;

public class FeedbackListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			//获取用户反馈
			FeedbackService fs = new FeedbackServiceImpl();
			List<Feedback> fbList =fs.getAllUnprocessd();
			request.setAttribute("fbList", fbList);
			List<FeedbackItem> feedItem = new ArrayList<FeedbackItem>();
			//获取用户信息
			for(int i=0;i<fbList.size();i++){
			Feedback fb =fbList.get(i);
			CustomerService custS =new CustomerServiceImpl();
			List<Customer> listCust=custS.getById(fb.getcId());
			if(listCust.size()!=0){
				Customer c1 =listCust.get(0);
				FeedbackItem fitem =new FeedbackItem(c1.getcName(),fb);
				feedItem.add(fitem);
				}
			}
			if(feedItem.size()!=0)
			request.setAttribute("feedItem", feedItem);
	}

}
