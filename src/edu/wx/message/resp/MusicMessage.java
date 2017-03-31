package edu.wx.message.resp;

/**
 * Created by jose on 2017/3/8.
 * 音乐消息
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
