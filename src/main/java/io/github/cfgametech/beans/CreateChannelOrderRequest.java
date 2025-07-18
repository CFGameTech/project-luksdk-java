package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * CreateChannelOrderRequest 类被用于解析来自 LUKSDK 获取创建订单的请求参数。 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class CreateChannelOrderRequest {

    /**
     * 订单数据列表
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

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CreateChannelOrderRequest{" +
                "data=" + data +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", nonce='" + nonce + '\'' +
                '}';
    }

    /**
     * 订单数据条目
     */
    public static class Entry {
        /**
         * 创建订单的游戏 ID
         */
        @JsonProperty("g_id")
        private int gameId;

        /**
         * 创建订单的渠道 ID
         */
        @JsonProperty("c_id")
        private int channelId;

        /**
         * 创建订单的房间 ID
         */
        @JsonProperty("c_room_id")
        private String roomId;

        /**
         * 创建订单的用户 ID
         */
        @JsonProperty("c_uid")
        private String userId;

        /**
         * 订单应扣除的货币数量
         */
        @JsonProperty("coins_cost")
        private long coinsCost;

        /**
         * 订单消耗的积分数量
         */
        @JsonProperty("score_cost")
        private long scoreCost;

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
         * 时间戳
         */
        @JsonProperty("timestamp")
        private long timestamp;

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

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public long getCoinsCost() {
            return coinsCost;
        }

        public void setCoinsCost(long coinsCost) {
            this.coinsCost = coinsCost;
        }

        public long getScoreCost() {
            return scoreCost;
        }

        public void setScoreCost(long scoreCost) {
            this.scoreCost = scoreCost;
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

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "gameId=" + gameId +
                    ", channelId=" + channelId +
                    ", roomId='" + roomId + '\'' +
                    ", userId='" + userId + '\'' +
                    ", coinsCost=" + coinsCost +
                    ", scoreCost=" + scoreCost +
                    ", gameOrderId='" + gameOrderId + '\'' +
                    ", token='" + token + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

}

