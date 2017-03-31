package edu.ts.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import edu.wx.message.resp.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.core.util.QuickWriter;

public class MessageUtil {
    //请求消息时间：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    //请求消息时间：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    //请求消息类型: 语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    //请求消息类型: 视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    //请求消息类型:  地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    //请求消息类型: 链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    //请求消息事件: 事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    //事件类型: subscribe（订阅）
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    //事件类型: unsubscribe（取消订阅）
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    //事件类型: scan（关注用户扫描带参数二维码）
    public static final String EVENT_TYPE_SCAN = "scan";
    //事件类型: LOCATION（上报地理位置）
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    //事件类型: CLICK（自定义菜单）
    public static final String EVENT_TYPE_CLICK = "CLICK";

    //响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    //响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    //响应消息类型：语音
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    //响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    //响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    //响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    
    /**
     * 解析微信发来的请求(XML)
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
        //将解析结果存储在HashMap中
        Map<String,String> map = new HashMap<String, String>();

        //从request中取得输入流
        InputStream inputStream = request.getInputStream();
        //读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        //得到xml根元素
        Element root = document.getRootElement();
        //得到根元素的所有子节点
        List<Element> elementList = root.elements();

        //遍历所有子节点
        for (Element e: elementList) {
            map.put(e.getName(),e.getText());
        }
        //释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }
    /**
     * 扩展xstream 使其支持CDATA
     */
    private static XStream xStream = new XStream(new XppDriver(){
        public HierarchicalStreamWriter creatWriter(Writer out){
            return new PrettyPrintWriter(out){
                //对所有XML节点的转换都增加CDATA
                boolean cdata = true;

                public void stattNode(String name,Class clazz){
                    super.startNode(name,clazz);
                }

                protected void writeText(QuickWriter writer,String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        }
    });
    
    /**
     * 文本消息对象转换成xml
     * @param textMessage
     * @return
     */
    public static String messageToXml(TextMessage textMessage){
        xStream.alias("xml",textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    /**
     * 图片消息对象转换成xml
     * @param imageMessage
     * @return
     */
    public static String messageToXml(ImageMessage imageMessage){
        xStream.alias("xml",imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    /**
     * 语音消息对象转换成xml
     * @param voiceMessage
     * @return
     */
    public static String messageToXml(VoiceMessage voiceMessage){
        xStream.alias("xml",voiceMessage.getClass());
        return xStream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成xml
     * @param videoMessage
     * @return
     */
    public static String messageToXml(VideoMessage videoMessage){
        xStream.alias("xml",videoMessage.getClass());
        return xStream.toXML(videoMessage);
    }

    /**
     * 音乐消息对象转换成xml
     * @param musicMessage
     * @return
     */
    public static String messageToXml(MusicMessage musicMessage){
        xStream.alias("xml",musicMessage.getClass());
        return xStream.toXML(musicMessage);
    }


    /**
     * 图文消息对象转换成xml
     * @param newsMessage
     * @return
     */
    public static String messageToXml(NewsMessage newsMessage){
        xStream.alias("xml",newsMessage.getClass());
        return xStream.toXML(newsMessage);
    }

    
}
