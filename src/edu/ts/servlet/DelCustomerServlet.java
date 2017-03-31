package edu.ts.servlet;

import java.io.IOException;
/*/**
 * @ClassName:     DelCustomerServlet
 * @Description:   删除会员，返回前台 删除成功/失败信息
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.util.StringUtil;

public class DelCustomerServlet extends HttpServlet {

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

		doPost(request,response);
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
		String id =request.getParameter("cid");
		CustomerServiceImpl cs=new CustomerServiceImpl();
		boolean flag = cs.delete(Integer.parseInt(id));//调用删除会员服务
		if(flag == true){
			request.setAttribute("deleteRes", "succ");
			response.sendRedirect("jsp/queryCustomer.jsp");
		}
		else{
			request.setAttribute("deleteRes", "fail");
			response.sendRedirect("jsp/queryCustomer.jsp");
		}
	}

}
