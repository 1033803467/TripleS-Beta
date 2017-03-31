package edu.ts.util;


import org.apache.http.client.HttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wx.pojo.WeixinOauth2Token;

import javax.net.ssl.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;


/**
 * Created by jose on 2017/3/6.
 * 公共平台通用接口工具类
 */
public class WeixinUtil {
    //private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr){
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            TrustManager[] tm =  {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm,new java.security.SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url =new URL(requestUrl);
            HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
            httpsUrlConn.setSSLSocketFactory(ssf);
            httpsUrlConn.setDoOutput(true);
            httpsUrlConn.setDoInput(true);
            httpsUrlConn.setUseCaches(false);
            //设置请求方式(GET/POST)
            httpsUrlConn.setRequestMethod(requestMethod);

            if("GET".equalsIgnoreCase(requestMethod))
                httpsUrlConn.connect();

            //当有数据提交时
            if(null !=outputStr){
                OutputStream outputStream = httpsUrlConn.getOutputStream();
                //注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            //将返回的输入流转换成字符串
            InputStream inputStream = httpsUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while((str = bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream =null;
            httpsUrlConn.disconnect();
            jsonObject = new JSONObject(buffer.toString());

        } catch (ConnectException ce){
            //log.error("WEixin server connect timed out.");
        }catch (Exception e) {
            //log.error("https request error()",e);
        }
        return jsonObject;
    }

    /**
     * 刷新网页授权凭证，scope  base 可以在这一步获取openid
     *
     * @param appId
     * @param appSecret
     * @param code
     * @return 
     * @return
     */
    public static  WeixinOauth2Token getOauth2AccessToken(String appId,String appSecret,String code){
        WeixinOauth2Token  wat= null;
        //拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID",appId);
        requestUrl = requestUrl.replace("SECRET",appSecret);
        requestUrl = requestUrl.replace("CODE",code);
        //获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl,"GET",null);
        if(jsonObject != null){
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            }catch (Exception e){
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                //log.error("刷新网页收取凭证失败 errcode:{} errmsh:{}",errorCode,errorMsg);
            }
        }
        return wat;
    }
}
