package edu.ts.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.CustomerTableItem;
import edu.ts.entity.DataTableJSONResponse;
import edu.ts.entity.DataTableRequest;
import edu.ts.entity.Good;
import edu.ts.entity.OrderTableItem;
import edu.ts.entity.Page;
import edu.ts.entity.WxOrderItem;
import edu.ts.service.CustomerService;
import edu.ts.service.GoodService;
import edu.ts.service.OrderService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.GoodServiceImpl;
import edu.ts.service.impl.OrderServiceImpl;



public class PageServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				// 编码设置
				 request.setCharacterEncoding("UTF-8");
				 response.setCharacterEncoding("utf-8");
				 response.setContentType("text/html; charset=UTF-8");
				 //获取请求类型
				 int type = Integer.parseInt(request.getParameter("type"));
				 Gson gson = new Gson();
				 switch(type){
				 case 1:{
					 Object draw = request.getParameter("draw");
					 Object orderColumn = request.getParameter("order[0][column]");
					 Object orderDir = request.getParameter("order[0][dir]");
					 Object start = request.getParameter("start");
					 Object length = request.getParameter("length");
					 Object search = request.getParameter("search[value]");
					//封装请求
					 DataTableRequest dtr = new DataTableRequest(orderColumn,orderDir,start,length,search);
					 DataTableRequest searchDtr = new DataTableRequest(search);
					 // 从数据库获取数据
					 CustomerService cs = new CustomerServiceImpl();
					 List<CustomerTableItem> infos = cs.getCustomerPaging(dtr);
					 Object recordsTotal = cs.getAll().size();
					 Object recordsFiltered = cs.getCustomerPaging(searchDtr).size();
					 // 数据封装并返回给客户端
					 DataTableJSONResponse dtjs = new DataTableJSONResponse(draw,recordsTotal,recordsFiltered,infos);
					 String jsonObject = gson.toJson(dtjs);
					 response.getWriter().print(jsonObject.toString());
					 break;
				 }
				 case 2:{
					 Object draw = request.getParameter("draw");
					 Object orderColumn = request.getParameter("order[0][column]");
					 Object orderDir = request.getParameter("order[0][dir]");
					 Object start = request.getParameter("start");
					 Object length = request.getParameter("length");
					 Object search = request.getParameter("search[value]");
					//封装请求
					 DataTableRequest dtr = new DataTableRequest(orderColumn,orderDir,start,length,search);
					 DataTableRequest searchDtr = new DataTableRequest(search);
					 // 从数据库获取数据
					 OrderService os = new OrderServiceImpl();
					 List<OrderTableItem> infos = os.getOrderPaging(dtr);
					 Object recordsTotal = os.getAllOrderTableItem().size();
					 Object recordsFiltered = os.getOrderPaging(searchDtr).size();
					 // 数据封装并返回给客户端
					 DataTableJSONResponse dtjs = new DataTableJSONResponse(draw,recordsTotal,recordsFiltered,infos);
					 String jsonObject = gson.toJson(dtjs);
					 response.getWriter().print(jsonObject.toString());
					 break;
				 }
				 case 3:{
					 	List<Good> listg = new ArrayList<Good>();
					 	int pageSize=10;
						String currentPage=request.getParameter("currentPage");
						GoodService gService=new GoodServiceImpl();
						Object[] obj=gService.queryByPage(currentPage, pageSize);
						Page page2=(Page) obj[1];
						listg=(List<Good>) obj[0];
						request.setAttribute("page", page2);
						request.setAttribute("listg", listg);
						request.getRequestDispatcher("./jsp/goodsList.jsp").forward(request, response);
						break;
				 }case 4:{
					 Object openid = request.getParameter("openid");
					 // 从数据库获取数据
					 OrderService os = new OrderServiceImpl();
					 List<WxOrderItem> data = os.getAllWxOrderItem(openid.toString());
					 // 数据封装并返回给客户端
					 String jsonObject = gson.toJson(data);
					 response.getWriter().print(jsonObject.toString());
					 break;
				 }
				 default:{}
				 }
	}

}
