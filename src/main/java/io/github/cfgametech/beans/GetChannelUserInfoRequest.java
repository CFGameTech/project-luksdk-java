package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetChannelUserInfoRequest 类被用于解析来自 LUKSDK 获取接入方用户信息的请求参数。 
 */
public class GetChannelUserInfoRequest {
    /**
     * 用户所属渠道 ID
     */
    @JsonProperty("c_id")
    private int channelId;

    /**
     * 用户 ID
     */
    @JsonProperty("c_uid")
    private String userId;

    /**
     * 用户 Token
     */
    @JsonProperty("token")
    private String token;

    /**
     * 来自 LUKSDK 的请求参数签名
     */
    @JsonProperty("sign")
    private String sign;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
