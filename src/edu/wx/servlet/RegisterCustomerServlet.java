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

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jose on 2017/3/8.
 */
public class RegisterCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("==========进入registercustomerservlet============");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        boolean success = false;
        Date date = null;
        String cOpenid = request.getParameter("openid");
        String cName = request.getParameter("name");
        int cGender = Integer.parseInt(request.getParameter("gender"));
        String cBirth = request.getParameter("bday");
        String cTel = request.getParameter("tel");
        String cMail = request.getParameter("mail");
        String cPassword = request.getParameter("password");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        try {
            // date = sdf.parse(cBirth);
            date = new SimpleDateFormat("yyyy-MM-dd").parse(cBirth);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Customer customer = new Customer(cName, cGender, date, cTel, cMail, cPassword, cOpenid);
        CustomerService customerService = new CustomerServiceImpl();
        if(customerService.getByOpenid(cOpenid).size()==0){     
        System.out.println(request.getContextPath());
        success = customerService.registerCustomerByWx(customer);}
        out.print(success);
        out.close();
        out = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
