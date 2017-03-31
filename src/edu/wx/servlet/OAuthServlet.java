package edu.wx.servlet;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.wx.pojo.OAuthState;
import edu.wx.pojo.WxInfo;
import edu.wx.pojo.WeixinOauth2Token;
import edu.ts.util.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jose on 2017/3/11.
 */
public class OAuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("======进入OAuthServlet=======");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //用户同意授权后，获取code
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String appdid = WxInfo.getAppId();
        String appsecret = WxInfo.getAppsecret();
        WeixinOauth2Token weixinOauth2Token = WeixinUtil.getOauth2AccessToken(appdid, appsecret, code);
        String openid =  weixinOauth2Token.getOpenId();
        //查询是否是会员
        CustomerDao customerDao =  new CustomerDaoImpl();
        System.out.println("====="+openid+"======");
        List<Customer> list = customerDao.getCustomerByOpenid(openid);
        request.setAttribute("openid",openid);
        if(list.size()==0){
        	request.getRequestDispatcher("bind.jsp").forward(request, response);
        	//response.sendRedirect("../wxjsp/register.jsp");
          }
        else{
        	//查询是否被加入黑名单
        	if(list.get(0).getcState()==0) request.getRequestDispatcher("blacklist.jsp").forward(request, response);
        if(state.equals(OAuthState.FEEDBACK)) request.getRequestDispatcher("sendFeedback.jsp").forward(request,response);
        else if(state.equals(OAuthState.MODIFY)) request.getRequestDispatcher("modifyPassword.jsp").forward(request,response);
        else if(state.equals(OAuthState.UPLOAD))request.getRequestDispatcher("uploadFace.jsp").forward(request,response);
        else if(state.equals(OAuthState.RECOMMEND)){
            //request.setAttribute("customer",list.get(0)); 获取推荐信息
            request.getRequestDispatcher("uploadFace.jsp").forward(request,response);
        }else if(state.equals(OAuthState.INFORMATION)){
            request.setAttribute("customer",list.get(0));
            request.getRequestDispatcher("personinfo.jsp").forward(request,response);
        }else if(state.equals(OAuthState.CONSUME)){
        	request.getRequestDispatcher("orders.jsp").forward(request,response);
        }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    } 
}
