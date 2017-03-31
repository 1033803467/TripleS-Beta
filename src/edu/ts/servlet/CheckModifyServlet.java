package edu.ts.servlet;
/*/**
 * @ClassName:     CheckModifyServlet
 * @Description:   修改会员异步验证  modifyCustomerjsp ajax异步请求，返回json
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */
import edu.ts.service.CustomerService;
import edu.ts.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckModifyServlet extends HttpServlet {

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
        String tel = request.getParameter("tele");
        String c_id = request.getParameter("cid");

        boolean flag = false;

        CustomerService cs = new CustomerServiceImpl();
        //异步验证是否可以修改用户信息，可以修改返回true，否则返回false
        flag = cs.CheckCustomerModify(tel, c_id);
        if(flag==true){
            out.print("{\"valid\":true}");
        }
        else{
            out.print("{\"valid\":false}");
        }


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
