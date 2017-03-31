package edu.wx.pojo;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

/**
 * Created by jose on 2017/3/7.
 */
public class WxInfo {
    private static final String appId = "wx97440886466a1b0f";

    private static final String appsecret = "4c96674c13077c25e403b9cda703f777";
    
    private static final String templateId = "F-5N18jmIF03gHzALNKEX2zCqZKiRa6bVhE6dbde4EA";
    
    public static final String URL = "http://zyj111.ittun.com/";
    
    public static final String ENCODEURL = "http%3a%2f%2f118.89.31.153%2f";

    public static String getAppId() {
        return appId;
    }

    public static String getAppsecret() {
        return appsecret;
    }

	public static String getTemplateid() {
		return templateId;
	}
    
}
