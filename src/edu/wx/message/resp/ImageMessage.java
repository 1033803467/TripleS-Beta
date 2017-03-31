package edu.wx.message.resp;

/**
 * Created by jose on 2017/3/8.
 * 图片消息
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
