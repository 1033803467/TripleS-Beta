package edu.ts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.entity.Good;
import edu.ts.entity.Page;
import edu.ts.service.GoodService;
import edu.ts.service.impl.GoodServiceImpl;

public class SearchGoodServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			Page page2;
			String content=request.getParameter("content");
			request.setCharacterEncoding("GBK");
			content=new String(content.getBytes("ISO-8859-1"),"utf-8");
//			request.setCharacterEncoding("utf-8");
//			response.setCharacterEncoding("UTF-8");
			List<Good> listg=new ArrayList<Good>();
			int pageSize=9;
			String currentPage=request.getParameter("currentPage");
			GoodService gService=new GoodServiceImpl();
			
			Object[] obj=gService.searchGood(content, currentPage, pageSize);
			if(obj.length>=2){
			page2=(Page) obj[1];
			listg=(List<Good>) obj[0];
			request.setAttribute("page", page2);
			
			request.setAttribute("listg", listg);
			}
			request.getRequestDispatcher("./jsp/goodsList.jsp").forward(request, response);

	}

}
