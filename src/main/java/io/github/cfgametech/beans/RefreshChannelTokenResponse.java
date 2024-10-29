package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RefreshChannelTokenResponse 是对于 RefreshChannelTokenRequest 的响应数据
 */
public class RefreshChannelTokenResponse {
    /**
     * 刷新所得的用户 Token 令牌
     */
    @JsonProperty("token")
    private String token;

    /**
     * 刷新所得的用户 Token 令牌的剩余有效时间，单位为秒。
     * 剩余时间用于避免时区问题
     */
    @JsonProperty("left_time")
    private long expirationTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
}