package edu.ts.servlet;
/*/**
 * @ClassName:     CheckAddServlet
 * @Description:   添加会员异步验证  addCustomerjsp ajax异步请求，返回json
 *
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */
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

public class CheckAddServlet extends HttpServlet {

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
        String tel = request.getParameter("tel");
        PrintWriter out = response.getWriter();

        if(tel!=null){
            CustomerService cs = new CustomerServiceImpl();
            List<Customer> list = cs.getByTel(tel);
            if(list.size()==0){
                out.print("{\"valid\":true}");
            }
            else{
                out.print("{\"valid\":false}");

            }
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
