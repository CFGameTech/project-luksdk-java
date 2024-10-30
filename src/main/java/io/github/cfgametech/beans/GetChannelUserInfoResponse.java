package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetChannelUserInfoResponse 是对于 GetChannelUserInfoRequest 的响应数据
 */
public class GetChannelUserInfoResponse {
    /**
     * 用户 ID
     */
    @JsonProperty("c_uid")
    private String userId;

    /**
     * 用户昵称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 用户头像 URL
     */
    @JsonProperty("avatar")
    private String avatar;

    /**
     * 用户货币数量（金币）
     */
    @JsonProperty("coins")
    private long coins;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "GetChannelUserInfoResponse{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", coins=" + coins +
                '}';
    }

    public static class Builder {
        private GetChannelUserInfoResponse response;
        
        public Builder() {
            this.response = new GetChannelUserInfoResponse();
        }
        
        public Builder setUserId(String userId) {
            this.response.setUserId(userId);
            return this;
        }
        
        public Builder setName(String name) {
            this.response.setName(name);
            return this;
        }
        
        public Builder setAvatar(String avatar) {
            this.response.setAvatar(avatar);
            return this;
        }
        
        public Builder setCoins(long coins) {
            this.response.setCoins(coins);
            return this;
        }
        
        public GetChannelUserInfoResponse build() {
            return this.response;
        }
    }
}
