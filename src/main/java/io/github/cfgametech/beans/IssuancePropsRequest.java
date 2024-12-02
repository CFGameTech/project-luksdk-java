package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class IssuancePropsRequest {
    /**
     * 渠道 ID
     */
    @JsonProperty("c_id")
    private int channelId;
    
    /**
     * 游戏 ID
     */
    @JsonProperty("g_id")
    private int gameId;

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

    @JsonProperty("data")
    private List<IssuancePropsRequestEntry> data;


    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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

    public List<IssuancePropsRequestEntry> getData() {
        return data;
    }

    public void setData(List<IssuancePropsRequestEntry> data) {
        this.data = data;
    }

    public static class Builder {
        private final IssuancePropsRequest request;
        
        public Builder() {
            request = new IssuancePropsRequest();
        }
        
        public IssuancePropsRequest build() {
            return request;
        }
        
        public Builder setChannelId(int channelId) {
            request.channelId = channelId;
            return this;
        }
        
        public Builder setGameId(int gameId) {
            request.gameId = gameId;
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
        
        public Builder setData(List<IssuancePropsRequestEntry> data) {
            request.data = data;
            return this;
        }
    }
    
    public static class DataBuilder {
        private final List<IssuancePropsRequestEntry> data;
        
        public DataBuilder() {
            this.data = new ArrayList<>();
        }
        
        public DataBuilder add(IssuancePropsRequestEntry entry) {
            data.add(entry);
            return this;
        }
        
        public List<IssuancePropsRequestEntry> build() {
            return data;
        }
        
    }
}
