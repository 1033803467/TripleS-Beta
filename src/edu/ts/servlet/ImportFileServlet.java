package edu.ts.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.ts.service.GoodService;
import edu.ts.service.OrderService;
import edu.ts.service.impl.GoodServiceImpl;
import edu.ts.service.impl.OrderServiceImpl;
import edu.ts.util.FTPUtil;

public class ImportFileServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//获取请求类型
		int type = Integer.parseInt(request.getParameter("type"));
		switch(type){
			case 1:{
				String file = (String) request.getSession().getAttribute("orderCSV");
				OrderService os = new OrderServiceImpl();
				request.setAttribute("orderflag",os.importCSV(file));
				request.getRequestDispatcher("./jsp/inputOrders.jsp").forward(request, response);
				//response.sendRedirect("jsp/inputOrders.jsp");
		        break;
			}
			case 2:{
				String file = (String) request.getSession().getAttribute("goodCSV");
				GoodService gs = new GoodServiceImpl();
				request.setAttribute("goodflag",gs.importCSV(file));
				request.getRequestDispatcher("./jsp/inputGoods.jsp").forward(request, response);
				//response.sendRedirect("jsp/inputOrders.jsp");
		        break;
			}
			default:{}
		}
	}

}
