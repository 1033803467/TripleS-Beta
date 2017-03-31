package edu.ts.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.service.CustomerService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.util.StringUtil;


public class ModifyCustomerServlet extends HttpServlet {

	String id=null;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			Date date=null;
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String id =request.getParameter("cid");
			String name =request.getParameter("name");
			String tel=request.getParameter("tel");
			String email=request.getParameter("email");
			String birth=request.getParameter("birth");
		    try {
				// date = sdf.parse(cBirth);
				 date=  new SimpleDateFormat("yyyy-MM-dd").parse(birth);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		    
		    //Customer customer =new Customer(Integer.parseInt(id),name,tel,email,date);
		    CustomerService custS=new CustomerServiceImpl();
		    Customer customer =  custS.getById(Integer.parseInt(id)).get(0);
		    customer.setcName(name);
		    customer.setcTel(tel);
		    customer.setcMail(email);
		    customer.setcBirth(date);
		    boolean flag=custS.modify(customer);
		    if(flag){
		    	System.out.println("modify success");
		    	response.sendRedirect("jsp/queryCustomer.jsp");
		    }else{
		    	System.out.println("fail to modify");
		    	response.sendRedirect("jsp/modifyCustomer.jsp");
		    }
	}

}
