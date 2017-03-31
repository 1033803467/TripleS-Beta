package edu.ts.servlet;


import edu.ts.service.AdminService;
import edu.ts.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//登录验证
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -3432724568017156828L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		String mobile=request.getParameter("adminTel");
		String password=request.getParameter("passwd");
		HttpSession session=request.getSession();
		session.setAttribute("mobile", mobile);
		AdminService as=new AdminServiceImpl();
		if(mobile!=null&&password!=null)
		{
			if(as.login(mobile, password))
			{
				session.setAttribute("username", as.getNameByTel(mobile));
				response.sendRedirect("./jsp/index.jsp");
			}
			else
			{
				response.sendRedirect("./");
			}
		}

	}

}