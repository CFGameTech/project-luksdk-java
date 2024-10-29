package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * NotifyChannelOrderResponseEntry 是对于 NotifyChannelOrderRequest 的响应条目，具体响应应为一个列表，每个条目对应一个 NotifyChannelOrderResponseEntry 中的条目。
 */
public class NotifyChannelOrderResponseEntry {
    /**
     * 用户 ID
     */
    @JsonProperty("c_uid")
    private String userId;

    /**
     * 渠道订单id
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 用户剩余货币
     */
    @JsonProperty("coins")
    private long coins;

    /**
     * 用户剩余积分
     */
    @JsonProperty("score")
    private long score;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
