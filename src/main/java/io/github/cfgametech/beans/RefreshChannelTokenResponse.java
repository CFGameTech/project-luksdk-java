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
    private long leftTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(long leftTime) {
        this.leftTime = leftTime;
    }

    @Override
    public String toString() {
        return "RefreshChannelTokenResponse{" +
                "token='" + token + '\'' +
                ", leftTime=" + leftTime +
                '}';
    }

    public static class Builder {
       private final RefreshChannelTokenResponse response;
       
       public Builder() {
           response = new RefreshChannelTokenResponse();
       }
       
       public Builder setToken(String token) {
           response.setToken(token);
           return this;
       }
       
       public Builder setLeftTime(long leftTime) {
           response.setLeftTime(leftTime);
           return this;
       }
       
       public RefreshChannelTokenResponse build() {
           return response;
       }
    }
}