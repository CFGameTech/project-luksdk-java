package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CreateChannelOrderResponseEntry 是对于 CreateChannelOrderRequest 的响应条目，具体响应应为一个列表，每个条目对应一个 CreateChannelOrderRequest 中的条目。
 */
public class CreateChannelOrderResponseEntry {
    /**
     * 创建订单的用户 ID
     */
    @JsonProperty("c_uid")
    private String userId;

    /**
     * 接入方生成的订单 ID
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 用户余额
     */
    @JsonProperty("coins")
    private long coins;

    /**
     * 订单状态，订单创建成功（1），订单创建失败（0）
     */
    @JsonProperty("status")
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

