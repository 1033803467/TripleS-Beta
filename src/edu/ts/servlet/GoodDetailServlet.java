package edu.ts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.entity.Good;
import edu.ts.service.GoodService;
import edu.ts.service.impl.GoodServiceImpl;

public class GoodDetailServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doPost(request,response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String gid=request.getParameter("gid");
			Good good = new Good();
			List<Good> listg= new ArrayList();
			GoodService gService = new GoodServiceImpl();
            
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

			listg=gService.getById(Integer.parseInt(gid));
			System.out.println(listg.get(0));
			request.setAttribute("good", listg.get(0));
			request.getRequestDispatcher("/jsp/goodDetail.jsp").forward(request, response);
			

	}

}
