package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RefreshChannelTokenRequest 类被用于解析来自 LUKSDK 刷新用户令牌的请求参数。 
 */
public class RefreshChannelTokenRequest {
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
     * SDK 平台令牌
     */
    @JsonProperty("token")
    private String token;

    /**
     * 发起请求的时间戳
     */
    @JsonProperty("timestamp")
    private long timestamp;

    /**
     * 来自 LUKSDK 的请求参数签名
     */
    @JsonProperty("sign")
    private String sign;

    /**
     * 当前用户 Token 令牌的剩余有效时间，单位为秒。
     * 剩余时间用于避免时区问题
     */
    @JsonProperty("left_time")
    private long leftTime;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(long leftTime) {
        this.leftTime = leftTime;
    }

    @Override
    public String toString() {
        return "RefreshChannelTokenRequest{" +
                "channelId=" + channelId +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                ", leftTime=" + leftTime +
                '}';
    }
}
