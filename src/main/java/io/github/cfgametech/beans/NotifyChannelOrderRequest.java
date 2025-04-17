package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * NotifyChannelOrderRequest 类被用于解析来自 LUKSDK 下注开奖通知结果的请求参数
 */
public class NotifyChannelOrderRequest {

    /**
     * 下注开奖通知结果的具体数据
     */
    @JsonProperty("data")
    private List<Entry> data;

    /**
     * 来自 LUKSDK 的请求参数签名
     */
    @JsonProperty("sign")
    private String sign;

    /**
     * 时间戳
     */
    @JsonProperty("timestamp")
    private long timestamp;

    /**
     * 随机值
     */
    @JsonProperty("nonce")
    private String nonce;

    public List<Entry> getData() {
        return data;
    }

    public void setData(List<Entry> data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return "NotifyChannelOrderRequest{" +
                "data=" + data +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", nonce='" + nonce + '\'' +
                '}';
    }

    /**
     * 通知结果的具体数据条目
     */
    public static class Entry {
        /**
         * 游戏 ID
         */
        @JsonProperty("g_id")
        private int gameId;

        /**
         * 渠道 ID
         */
        @JsonProperty("c_id")
        private int channelId;

        /**
         * 用户 ID
         */
        @JsonProperty("c_uid")
        private String userId;

        /**
         * 游戏方订单 ID
         */
        @JsonProperty("game_order_id")
        private String gameOrderId;

        /**
         * 用户 Token
         */
        @JsonProperty("token")
        private String token;

        /**
         * 下注时消耗的货币数量
         */
        @JsonProperty("coins_cost")
        private long coinsCost;

        /**
         * 下注开出的金币 可能为 0 或者负数,即没获胜的情况下
         */
        @JsonProperty("coins_award")
        private long coinsAward;

        /**
         * 下注时消耗的积分
         */
        @JsonProperty("score_cost")
        private long scoreCost;

        /**
         * 下注开出的积分 可能为0或者负数,即没获胜的情况下
         */
        @JsonProperty("score_award")
        private long scoreAward;

        /**
         * 时间戳
         */
        @JsonProperty("timestamp")
        private long timestamp;

        /**
         * 订单状态 1订单成功 2退款
         */
        @JsonProperty("status")
        private int status;

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

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

        public String getGameOrderId() {
            return gameOrderId;
        }

        public void setGameOrderId(String gameOrderId) {
            this.gameOrderId = gameOrderId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getCoinsCost() {
            return coinsCost;
        }

        public void setCoinsCost(long coinsCost) {
            this.coinsCost = coinsCost;
        }

        public long getCoinsAward() {
            return coinsAward;
        }

        public void setCoinsAward(long coinsAward) {
            this.coinsAward = coinsAward;
        }

        public long getScoreCost() {
            return scoreCost;
        }

        public void setScoreCost(long scoreCost) {
            this.scoreCost = scoreCost;
        }

        public long getScoreAward() {
            return scoreAward;
        }

        public void setScoreAward(long scoreAward) {
            this.scoreAward = scoreAward;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "gameId=" + gameId +
                    ", channelId=" + channelId +
                    ", userId='" + userId + '\'' +
                    ", gameOrderId='" + gameOrderId + '\'' +
                    ", token='" + token + '\'' +
                    ", coinsCost=" + coinsCost +
                    ", coinsAward=" + coinsAward +
                    ", scoreCost=" + scoreCost +
                    ", scoreAward=" + scoreAward +
                    ", timestamp=" + timestamp +
                    ", status=" + status +
                    '}';
        }
    }
}
