package edu.wx.pojo;

/**
 * Created by jose on 2017/3/6.
 * 微信通用接口凭证
 */
public class AccessToken {
    private String token;

    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
