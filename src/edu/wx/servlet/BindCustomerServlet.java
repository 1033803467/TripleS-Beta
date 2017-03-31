package edu.wx.servlet;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.service.CustomerService;
import edu.ts.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jose on 2017/3/6.
 */
public class BindCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
    	response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String openid = request.getParameter("openid");
        int flag = 0;//绑定失败
        String tel = request.getParameter("tel");
        String password = request.getParameter("password_bind");
        CustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> customers = customerDao.getCustomerByTel(tel);
        if(customers.size()==0) flag =2;//你不是老会员
        
        if (customers.size()==1&&password.equals(customers.get(0).getcPassword())&&customers.get(0).getcOpenid()==null){
            CustomerService customerService = new CustomerServiceImpl();
            customerService.bindCustomer(customers.get(0),openid);
            flag = 1; //绑定成功
       }else if(customers.size()==1&&!password.equals(customers.get(0).getcPassword())){
    	   flag=3;//提示密码错误
       }
        out.print(flag);
        out.close();
        out = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
