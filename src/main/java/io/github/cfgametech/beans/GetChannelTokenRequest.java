package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetChannelTokenRequest 类被用于解析来自 LUKSDK 通过接入方提供的用户 code 获取用户令牌的请求参数。 
 */
public class GetChannelTokenRequest {
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
     * 置换 Token 的用户 code
     */
    @JsonProperty("code")
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "GetChannelTokenRequest{" +
                "channelId=" + channelId +
                ", userId='" + userId + '\'' +
                ", code='" + code + '\'' +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                '}';
    }
}