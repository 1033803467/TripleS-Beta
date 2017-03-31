package edu.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.dao.PersonDao;
import edu.ts.dao.PersonGroupDao;
import edu.ts.dao.impl.PersonDaoImpl;
import edu.ts.dao.impl.PersonGroupDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.service.CustomerService;
import edu.ts.service.impl.CustomerServiceImpl;

public class TrianServlet extends HttpServlet {

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
		System.out.println("======TrainServlet=========");
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String openid = request.getParameter("openid");
        System.out.println(openid);
        CustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.getByOpenid(openid).get(0);
        
      String personId = customer.getcPersonid();
      PersonDao personDao = new PersonDaoImpl();
      System.out.println("正在添加");
      System.out.println(personId+"=="+customer.getcTel()+"==="+customer.getcPic());
      personDao.addFaceToPerson("1", personId,customer.getcTel(), customer.getcPic());
      PersonGroupDao personGroupDao = new PersonGroupDaoImpl();
      System.out.println("正在训练");
      personGroupDao.trainPersongroup("1");
	}

}
