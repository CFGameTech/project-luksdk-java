package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetGameServiceListRequest {
    /**
     * 渠道 ID
     */
    @JsonProperty("c_id")
    private int channelId;

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
        return "GetGameServiceListRequest{" +
                "channelId=" + channelId +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                '}';
    }
    
    
    public static class Builder {
        private final GetGameServiceListRequest request;

        public Builder() {
            request = new GetGameServiceListRequest();
        }

        public Builder setChannelId(int channelId) {
            request.channelId = channelId;
            return this;
        }
        
        public Builder setTimestamp(long timestamp) {
            request.timestamp = timestamp;
            return this;
        }
        
        public Builder setSign(String sign) {
            request.sign = sign;
            return this;
        }
        
        public GetGameServiceListRequest build() {
            return request;
        }
    }
}
