package edu.wx.message.resp;

/**
 * Created by jose on 2017/3/8.
 * 视频消息
 */
public class VideoMessage extends BaseMessage {
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
