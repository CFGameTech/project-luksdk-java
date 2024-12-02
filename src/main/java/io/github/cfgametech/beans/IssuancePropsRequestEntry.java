package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssuancePropsRequestEntry {
    /**
     * 用户 id
     */
    @JsonProperty("c_uid")
    private String userId;

    /**
     * 道具 id
     */
    @JsonProperty("prop_id")
    private String propId;

    /**
     * 过期时间（秒）
     */
    @JsonProperty("expire")
    private long expire;
    
    /**
     * 数量
     */
    @JsonProperty("num")
    private int num;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static class Builder {
        private final IssuancePropsRequestEntry entry;
        
        public Builder() {
            this.entry = new IssuancePropsRequestEntry();
        }
        
        public Builder setUserId(String userId) {
            this.entry.setUserId(userId);
            return this;
        }
        
        public Builder setPropId(String propId) {
            this.entry.setPropId(propId);
            return this;
        }
        
        public Builder setExpire(long expire) {
            this.entry.setExpire(expire);
            return this;
        }
        
        public Builder setNum(int num) {
            this.entry.setNum(num);
            return this;
        }
        
        public IssuancePropsRequestEntry build() {
            return this.entry;
        }
    }
}
