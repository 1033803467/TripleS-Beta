package edu.ts.servlet;

import edu.ts.entity.Admin;
import edu.ts.service.AdminService;
import edu.ts.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	//注册管理员
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		String username=request.getParameter("name");
		String password=request.getParameter("password");
		String telephone=request.getParameter("adminTel");
		HttpSession session=request.getSession();
		AdminService as=new AdminServiceImpl();
		if(username!=null&&password!=null&&telephone!=null)
		{
			Admin ad=new Admin(username,password,telephone);
			if(as.register(ad))
			{
				System.out.println("register true");
				session.setAttribute("username", username);
				response.sendRedirect("./jsp/index.jsp");
			}
			else
			{
				System.out.println("register false");
				response.sendRedirect("/TripleS-Beta");
			}
		}



	}




}
