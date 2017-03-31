package edu.ts.servlet;
/*/**
 * @ClassName:     CheckAdminServlet
 * @Description:   管理员登录注册异步验证  login.jsp ajax异步请求，返回json
 * * @author          tengyihao
 * @version         V1.0
 * @Date
 */

import edu.ts.dao.AdminDao;
import edu.ts.dao.impl.AdminDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckAdminServlet extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String adminTel=request.getParameter("adminTel");
		String type=request.getParameter("type");

		boolean flag = false;

		//登陆验证，号码注册过则返回"{"valid":true}",否则返回"{"valid":false}"
		if(type.equals("signInCheck")){
			AdminDao ad = new AdminDaoImpl();
			//注册过true 没注册过false
			flag = ad.isExist(adminTel);
			if(flag==true){
				System.out.println("true");
				out.print("{\"valid\":true}");
			}
			else{
				out.print("{\"valid\":false}");
			}
		}
		//注册验证，号码注册过则返回"{valide:false}",否则返回"{valide:true}"
		if(type.equals("signUpCheck")){
			AdminDao ad = new AdminDaoImpl();
			//注册过true 没注册过false
			flag = ad.isExist(adminTel);
			if(flag==true){
				out.print("{\"valid\":false}");
			}
			else{
				out.print("{\"valid\":true}");
			}
		}

		else{}

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

		doGet(request,response);
	}

}
