package edu.ts.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
public class AddCustomerServlet extends HttpServlet {
	private Customer customer;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Date date=null;
		String cName=request.getParameter("name");
		String cGender = request.getParameter("sex");
		String cTel = request.getParameter("tel");
		String cMail = request.getParameter("email");
		String cBirth = request.getParameter("birth");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
		    try {
				// date = sdf.parse(cBirth);
				 date=  new SimpleDateFormat("yyyy-MM-dd").parse(cBirth);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		    
		Customer customer=new Customer(cName, Integer.parseInt(cGender), cTel, cMail, date);
		CustomerService custs=new CustomerServiceImpl();
		boolean flag=custs.add(customer);
		if(flag){
			
			System.out.println("success add customers");
			response.sendRedirect("jsp/addCustomer.jsp?flag="+flag);
			
		}else{
			System.out.println("fail to add");
			response.sendRedirect("jsp/addCustomer.jsp?flag="+flag);
		}

	}
}
