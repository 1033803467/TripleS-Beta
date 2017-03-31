package edu.wx.pojo;

/**
 * Created by jose on 2017/3/13.
 */
public class Token {
    //接口访问凭证
    private String accessToken;
    //凭证有效期
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
