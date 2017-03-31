package edu.wx.servlet;

import edu.ts.service.FeedbackService;
import edu.ts.service.impl.FeedbackServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by jose on 2017/3/8.
 */

public class AddFeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        int flag = 0; //反馈失败
        String openid = (String)request.getParameter("openid");
        String fMessage = request.getParameter("feedback");
        FeedbackService feedbackService = new FeedbackServiceImpl();
        if(feedbackService.addFeedBack(openid,fMessage)){
        	flag = 1; //反馈成功
        }
        out.print(flag);
        out.close();
        out = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
