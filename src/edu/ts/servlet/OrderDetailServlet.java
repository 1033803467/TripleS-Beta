package edu.ts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.OrderTableItem;
import edu.ts.entity.OrderTableItemDetail;
import edu.ts.service.CustomerService;
import edu.ts.service.OrderService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.OrderServiceImpl;

public class OrderDetailServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			//从orderList view按钮获取cid便于页面查询
			OrderService os = new OrderServiceImpl();
			String oid=request.getParameter("oid");
			int cid= os.getOrderByoId(Integer.parseInt(oid.trim())).get(0).getcId();
			String odate=os.getOrderByoId(Integer.parseInt(oid.trim())).get(0).getoDate();
			request.setAttribute("date",odate);
			//客户信息
			CustomerService customerS = new CustomerServiceImpl();
			List<Customer> listCust=customerS.getById(cid);
			request.setAttribute("customer", listCust.get(0));
			//订单详情
			List<OrderTableItemDetail> listOrderTID=os.getOrderTableItemDetailByoId(Integer.parseInt(oid.trim()));
			request.setAttribute("listOrderTID",listOrderTID);
			//转发到orderDetail.jsp
			request.getRequestDispatcher("/jsp/orderDetail.jsp").forward(request, response);
			
			

	}

}
