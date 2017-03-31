package edu.wx.message.event;

/**
 * Created by jose on 2017/3/13.
 *
 * 自定义菜单事件
 */
public class MenuEvent extends BaseEvent{
    //时间KEY值，与自定义菜单接口中KEY值对应
    private  String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
