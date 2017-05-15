package com.example.yuer.myoschina.bean;

/**
 * Created by Yuer on 2017/4/27.
 */

public class TokenResponse {

    /**
     * access_token : 055b476b-21ef-47b7-b2c2-d439ca726465
     * refresh_token : bec48803-41f2-4295-943c-8014400f76d9
     * uid : 3453639
     * token_type : bearer
     * expires_in : 604362
     */

    private String access_token;
    private String refresh_token;
    private int uid;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
