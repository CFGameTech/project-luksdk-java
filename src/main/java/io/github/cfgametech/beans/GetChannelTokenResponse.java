package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetChannelTokenResponse 是对于 GetChannelTokenRequest 的响应数据
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class GetChannelTokenResponse {
    /**
     * 置换所得的用户 Token 令牌
     */
    @JsonProperty("token")
    private String token;

    /**
     * 置换所得的用户 Token 令牌的剩余有效时间，单位为秒。
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
        return "GetChannelTokenResponse{" +
                "token='" + token + '\'' +
                ", leftTime=" + leftTime +
                '}';
    }

    public static class Builder {
        private final GetChannelTokenResponse response;
        
        public Builder() {
            response = new GetChannelTokenResponse();
        }
        
        public Builder setToken(String token) {
            response.setToken(token);
            return this;
        }
        
        public Builder setLeftTime(long leftTime) {
            response.setLeftTime(leftTime);
            return this;
        }
        
        public GetChannelTokenResponse build() {
            return response;
        }
    }
}