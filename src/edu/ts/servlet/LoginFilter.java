package edu.ts.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{



	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		// 获得在下面代码中要用的request,response,session对象
		  HttpServletRequest servletRequest = (HttpServletRequest) request;
		  HttpServletResponse servletResponse = (HttpServletResponse) response;
		  HttpSession session = servletRequest.getSession();
		// 获得用户请求的URI
		  String path = servletRequest.getRequestURI();
		  //如果他第一次点击login.jsp
		  String username = (String) session.getAttribute("mobile");
		  //System.out.println("go here");
		  //
		  if(path.lastIndexOf("/login.jsp") > -1) {
		   chain.doFilter(servletRequest, servletResponse);
		   //return;
		  }
		  // 判断如果没有取到管理员信息,就跳转到登陆页面
		  else if (username == null || "".equals(username)) {
			  //System.out.println("======go here1");
		   // 跳转到登陆页面
		   servletResponse.sendRedirect("../login.jsp");
		  } else {
		   // 已经登陆,继续此次请求
		   chain.doFilter(request, response);
		  }
		  
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
