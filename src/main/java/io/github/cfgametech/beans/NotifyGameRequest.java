package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * NotifyGameRequest 是用于接收来自 LUKSDK 通知游戏状态变更请求参数的数据结构
 */
public class NotifyGameRequest {

    /**
     * 游戏开始前状态
     */
    public static final int NotifyTypeStartBefore = 1;
    /**
     * 游戏开始中状态
     */
    public static final int NotifyTypeGaming = 2;
    /**
     * 游戏结束状态
     */
    public static final int NotifyTypeEnd = 3;
    /**
     * 加入游戏操作
     */
    public static final int ActionJoinGame = 1;
    /**
     * 退出游戏操作
     */
    public static final int ActionExitGame = 2;
    /**
     * 设置游戏操作
     */
    public static final int ActionSettingGame = 3;
    /**
     * 踢人操作
     */
    public static final int ActionKickOut = 4;
    /**
     * 开始游戏操作
     */
    public static final int ActionStartGame = 5;
    /**
     * 准备操作
     */
    public static final int ActionPrepare = 6;
    /**
     * 取消准备操作
     */
    public static final int ActionCancelPrepare = 7;
    /**
     * 游戏结束操作
     */
    public static final int ActionGameEnd = 8;

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
     * 通知类型
     */
    @JsonProperty("notify_type")
    private int notifyType;

    /**
     * 渠道方透传数据或游戏房间拉起时传入游戏客户端，后续房间的通知会透传该数据，可选项，如果不接 SDK 接口，默认为空
     */
    @JsonProperty("ext")
    private String ext;

    /**
     * 游戏数据，根据 notify_type 的类型做相对应解析
     */
    @JsonProperty("data")
    private String data;

    /**
     * 时间戳
     */
    @JsonProperty("timestamp")
    private long timestamp;

    /**
     * 来自 LUKSDK 的请求参数签名
     */
    @JsonProperty("sign")
    private String sign;
    
    /**
     * @return 游戏开始前的数据
     * @throws JsonProcessingException 解析异常
     */
    public StartBefore getStartBefore() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.data, StartBefore.class);
    }

    /**
     * @return 游戏进行中的数据
     * @throws JsonProcessingException 解析异常
     */
    public Gaming getGaming() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.data, Gaming.class);
    }

    /**
     * @return 游戏结束时的数据
     * @throws JsonProcessingException 解析异常
     */
    public End getEnd() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.data, End.class);
    }

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

    public int getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    /**
     * 游戏开始前的数据
     */
    public static class StartBefore {
        /**
         * 房间 ID
         */
        @JsonProperty("room_id")
        private int roomId;

        /**
         * 游戏轮次 ID
         */
        @JsonProperty("round_id")
        private int roundId;

        /**
         * 玩家准备状态，key 为玩家 uid，value 为是否准备
         */
        @JsonProperty("player_ready_status")
        private Map<String, Boolean> playerReadyStatus;

        /**
         * 通知动作
         */
        @JsonProperty("notify_action")
        private int notifyAction;

        /**
         * 游戏设置
         */
        @JsonProperty("game_setting")
        private String gameSetting;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getRoundId() {
            return roundId;
        }

        public void setRoundId(int roundId) {
            this.roundId = roundId;
        }

        public Map<String, Boolean> getPlayerReadyStatus() {
            return playerReadyStatus;
        }

        public void setPlayerReadyStatus(Map<String, Boolean> playerReadyStatus) {
            this.playerReadyStatus = playerReadyStatus;
        }

        public int getNotifyAction() {
            return notifyAction;
        }

        public void setNotifyAction(int notifyAction) {
            this.notifyAction = notifyAction;
        }

        public String getGameSetting() {
            return gameSetting;
        }

        public void setGameSetting(String gameSetting) {
            this.gameSetting = gameSetting;
        }
    }

    /**
     * 游戏进行中的数据
     */
    public static class Gaming {
        /**
         * 房间 ID
         */
        @JsonProperty("room_id")
        private int roomId;

        /**
         * 游戏轮次 ID
         */
        @JsonProperty("round_id")
        private int roundId;

        /**
         * 玩家数量
         */
        @JsonProperty("player_num")
        private int playerNum;

        /**
         * 玩家 uid 列表
         */
        @JsonProperty("player_uids")
        private List<String> playerIds;

        /**
         * 通知动作
         */
        @JsonProperty("notify_action")
        private int notifyAction;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getRoundId() {
            return roundId;
        }

        public void setRoundId(int roundId) {
            this.roundId = roundId;
        }

        public int getPlayerNum() {
            return playerNum;
        }

        public void setPlayerNum(int playerNum) {
            this.playerNum = playerNum;
        }

        public List<String> getPlayerIds() {
            return playerIds;
        }

        public void setPlayerIds(List<String> playerIds) {
            this.playerIds = playerIds;
        }

        public int getNotifyAction() {
            return notifyAction;
        }

        public void setNotifyAction(int notifyAction) {
            this.notifyAction = notifyAction;
        }
    }

    /**
     * 游戏结束时的数据
     */
    public static class End {
        /**
         * 房间 ID
         */
        @JsonProperty("room_id")
        private int roomId;

        /**
         * 游戏轮次 ID
         */
        @JsonProperty("round_id")
        private int roundId;

        /**
         * 有序排行榜，根据玩家 ID 以分数降序排列
         */
        @JsonProperty("rank")
        private List<String> rank;

        /**
         * 是否强制结束
         */
        @JsonProperty("is_force_end")
        private boolean isForceEnd;

        /**
         * 通知动作
         */
        @JsonProperty("notify_action")
        private int notifyAction;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getRoundId() {
            return roundId;
        }

        public void setRoundId(int roundId) {
            this.roundId = roundId;
        }

        public List<String> getRank() {
            return rank;
        }

        public void setRank(List<String> rank) {
            this.rank = rank;
        }

        public boolean isForceEnd() {
            return isForceEnd;
        }

        public void setForceEnd(boolean forceEnd) {
            isForceEnd = forceEnd;
        }

        public int getNotifyAction() {
            return notifyAction;
        }

        public void setNotifyAction(int notifyAction) {
            this.notifyAction = notifyAction;
        }
    }
}
