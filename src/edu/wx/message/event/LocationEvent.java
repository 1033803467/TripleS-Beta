package edu.wx.message.event;

/**
 * Created by jose on 2017/3/13.
 *
 * 上报地理位置事件
 */
public class LocationEvent extends BaseEvent {
    //地理位置维度
    private String Latitude;
    //地理位置经度
    private String Longtitude;
    //地理位置精度
    private String Precision;

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
