package edu.wx.message.resp;

/**
 * Created by jose on 2017/3/8.
 * 语音消息
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
