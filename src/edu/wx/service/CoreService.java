package edu.wx.service;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.util.MessageUtil;
import edu.wx.message.resp.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jose on 2017/3/9.
 * 核心业务 接口
 */
public class CoreService {

	public static String processRequest(HttpServletRequest request){
		// xml格式的消息数据
		String respXml = null;
		try {
			//调用parseXML方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			//发送方账号
			String formUserName =  requestMap.get("FromUserName");
			//开发者微信号
			String toUserName =  requestMap.get("ToUserName");
			//消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage =  new TextMessage();
			textMessage.setToUserName(formUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());

			CustomerDao customerDao = new CustomerDaoImpl();
			List<Customer> list = customerDao.getCustomerByOpenid(formUserName);
				textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
				//事件推送
				if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
					//事件类型
					String eventType = requestMap.get("Event");
					//订阅
					if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
						textMessage.setContent("您好，欢迎订阅");

					}else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
						//暂不做处理
					}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
						String eventKey = requestMap.get("EventKey");
						if(eventKey.equals("recommend")){
							if(list.size()==0){
								textMessage.setContent("你尚未注册，请<a href='http://zyj111.ittun.com/TripleS-Beta/wx/bind.jsp?openid="+formUserName+"'>注册</a>");
							}
							else if(list.get(0).getcIsrec()==0)//不接受主动识别
							{
								textMessage.setContent("你尚未开始主动推荐，请前往个人信息开启。");
							}
							else {
								textMessage.setContent("请上传你的头像：<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx97440886466a1b0f&redirect_uri=http%3a%2f%2fzyj111.ittun.com%2fTripleS-Beta%2fwx%2fOAuthServlet&response_type=code&scope=snsapi_base&state=recommend#wechat_redirect'>上传</a>");
							}
						}
					}
				
			}
			respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
