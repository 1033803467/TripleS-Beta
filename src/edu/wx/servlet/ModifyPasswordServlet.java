package edu.wx.servlet;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.service.CustomerService;
import edu.ts.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ListModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jose on 2017/3/8.
 */

public class ModifyPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        int flag = 0;  //修改失败
        String oldPassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("newpassword");
        String openId = request.getParameter("openid");
        CustomerDao  customerDao = new CustomerDaoImpl();
        Customer customer = customerDao.getCustomerByOpenid(openId).get(0);
        if(customer.getcPassword().equals(oldPassword)){
        	customer.setcPassword(newPassword);
        	customerDao.modifyCustomerWeixin(customer);
        	flag = 1;//修改成功
        }else{
        	flag = 2;//原密码错误
        }
        out.print(flag);
        out.close();
        out = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
