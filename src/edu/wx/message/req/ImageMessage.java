package edu.wx.message.req;

/**
 * Created by jose on 2017/3/8.
 * 图片消息
 */
public class ImageMessage extends BaseMessage {
	//图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
