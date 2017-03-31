package edu.wx.servlet;

import com.google.gson.Gson;
import edu.ts.service.CustomerService;
import edu.ts.service.FeedbackService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.FeedbackServiceImpl;
import edu.ts.util.AdvancedUtil;
import edu.ts.util.CommonUtil;
import edu.wx.message.template.*;
import edu.wx.pojo.WxInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class HandleFeedbackServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		boolean flag = false;
		if(!request.getParameter("fid").equals("")){
		int fId = Integer.parseInt(request.getParameter("fid"));// 取fid
		int cid = Integer.parseInt(request.getParameter("cid"));// 取cid
		String  feedbackMsg = request.getParameter("feedbackMsg"); //取反馈消息
		CustomerService customerService = new CustomerServiceImpl();
		FeedbackService feedbackService = new FeedbackServiceImpl();
		String openid = customerService.getById(cid).get(0).getcOpenid();
		String accessToken = CommonUtil.getToken(WxInfo.getAppId(), WxInfo.getAppsecret()).getAccessToken();
		
		
		FeedbackTemplate feedbackTemplate = new FeedbackTemplate();
		Data data = new Data();
		Data_First first = new Data_First();
		Data_HandleMsg handleMsg = new Data_HandleMsg();
		Data_FbTime fbTime = new Data_FbTime();
		Data_HandleTime handleTime = new Data_HandleTime();
		Data_Remark remark = new Data_Remark();
		
		first.setValue("您的反馈已处理");
		first.setColor("#173177");
		handleMsg.setValue(feedbackMsg);
		handleMsg.setColor("#173177");
		fbTime.setValue(feedbackService.getByFid(fId).getfDate());
		fbTime.setColor("#173177");
		handleTime.setValue(new Date().toString());
		handleTime.setColor("#173177");
		remark.setValue("感谢您的反馈");
		remark.setColor("#173177");
		data.setFbtime(fbTime);
		data.setFirst(first);
		data.setHandleMsg(handleMsg);
		data.setHandleTime(handleTime);
		data.setRemark(remark);
		feedbackTemplate.setData(data);
		feedbackTemplate.setTemplateid(WxInfo.getTemplateid());
		feedbackTemplate.setTopcolor("#173177");
		feedbackTemplate.setTouser(openid);
		
		Gson gson = new Gson();
		String jsonMsg =gson.toJson(feedbackTemplate);
		//String jsonMsg = new JSONObject(feedbackTemplate).toString();
		if(AdvancedUtil.sendTemplateMessage(accessToken, jsonMsg))
			if(feedbackService.process(fId))
				flag = true;}
		else{
			flag = false;
		}
		out.print(flag);
        out.close();
        out = null;
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
