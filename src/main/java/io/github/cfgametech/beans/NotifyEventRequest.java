package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Map;

/**
 * 该接口是对于旧版本游戏状态通知（notify_game）接口的替代，提供更统一的数据结构及更多的事件信息。
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class NotifyEventRequest {
    public static final int TYPE_START_GAME_EVENT = 1;
    public static final int TYPE_GAME_END_EVENT = 2;
    public static final int TYPE_ROOM_USER_CHANGED_EVENT = 3;
    public static final int TYPE_ROOM_SETTING_CHANGED_EVENT = 4;
    public static final int TYPE_ROOM_SEAT_SYNC_EVENT = 5;

    @JsonProperty("app_id")
    private int appId;

    @JsonProperty("game_id")
    private int gameId;

    @JsonProperty(value = "room_id")
    private String roomId;

    @JsonProperty("round_id")
    private String roundId;

    @JsonProperty("sign")
    private String sign;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("type")
    private int type;

    @JsonProperty("data")
    private String data;

    // Getters and Setters

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public StartGameEvent parseDataAsStartGameEvent() throws JsonProcessingException {
        if (this.type != TYPE_START_GAME_EVENT || this.data == null || this.data.isEmpty()) {
            return null;
        }
        return new StartGameEvent.Builder(this.data).build();
    }
    
    public EndGameEvent parseDataAsEndGameEvent() throws JsonProcessingException {
        if (this.type != TYPE_GAME_END_EVENT || this.data == null || this.data.isEmpty()) {
            return null;
        }
        return new EndGameEvent.Builder(this.data).build();
    }
    
    public RoomUserChangedEvent parseDataAsRoomUserChangedEvent() throws JsonProcessingException {
        if (this.type != TYPE_ROOM_USER_CHANGED_EVENT || this.data == null || this.data.isEmpty()) {
            return null;
        }
        return new RoomUserChangedEvent.Builder(this.data).build();
    }
    
    public RoomSettingChangedEvent parseDataAsRoomSettingChangedEvent() throws JsonProcessingException {
        if (this.type != TYPE_ROOM_SETTING_CHANGED_EVENT || this.data == null || this.data.isEmpty()) {
            return null;
        }
        return new RoomSettingChangedEvent.Builder(this.data).build();
    }
    
    public RoomSeatSyncEvent parseDataAsRoomSeatSyncEvent() throws JsonProcessingException {
        if (this.type != TYPE_ROOM_SEAT_SYNC_EVENT || this.data == null || this.data.isEmpty()) {
            return null;
        }
        return new RoomSeatSyncEvent.Builder(this.data).build();
    }


    @Override
    public String toString() {
        return "NotifyEventRequest{" +
                "appId=" + appId +
                ", gameId=" + gameId +
                ", roomId='" + roomId + '\'' +
                ", roundId='" + roundId + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                ", data='" + data + '\'' +
                '}';
    }

    public static class Builder {
        private final NotifyEventRequest request;

        public Builder() {
            request = new NotifyEventRequest();
        }

        public Builder(String json) throws JsonProcessingException {
            this.request = new ObjectMapper().readValue(json, NotifyEventRequest.class);
        }

        public Builder setAppId(int appId) {
            request.appId = appId;
            return this;
        }

        public Builder setGameId(int gameId) {
            request.gameId = gameId;
            return this;
        }

        public Builder setRoomId(String roomId) {
            request.roomId = roomId;
            return this;
        }

        public Builder setRoundId(String roundId) {
            request.roundId = roundId;
            return this;
        }

        public Builder setSign(String sign) {
            request.sign = sign;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            request.timestamp = timestamp;
            return this;
        }

        public Builder setType(int type) {
            request.type = type;
            return this;
        }

        public Builder setData(String data) {
            request.data = data;
            return this;
        }

        public NotifyEventRequest build() {
            return request;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StartGameEvent {

        /**
         * 游戏开始时间（秒级时间戳）
         */
        @JsonProperty("start_unix_sec")
        private long startUnixSec;

        @JsonProperty("user_ids")
        private String[] userIds;

        @JsonProperty("op_user_id")
        private String opUserId;

        public long getStartUnixSec() {
            return startUnixSec;
        }

        public void setStartUnixSec(long startUnixSec) {
            this.startUnixSec = startUnixSec;
        }

        public String[] getUserIds() {
            return userIds;
        }

        public void setUserIds(String[] userIds) {
            this.userIds = userIds;
        }

        public String getOpUserId() {
            return opUserId;
        }

        public void setOpUserId(String opUserId) {
            this.opUserId = opUserId;
        }

        @Override
        public String toString() {
            return "StartGameEvent{" +
                    "startUnixSec=" + startUnixSec +
                    ", userIds=" + Arrays.toString(userIds) +
                    ", opUserId='" + opUserId + '\'' +
                    '}';
        }

        public static class Builder {
            private StartGameEvent event;

            public Builder() {
                this.event = new StartGameEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new ObjectMapper().readValue(json, StartGameEvent.class);
            }
            
            public Builder setStartUnixSec(long startUnixSec) {
                this.event.setStartUnixSec(startUnixSec);
                return this;
            }

            public Builder setUserIds(String[] userIds) {
                this.event.setUserIds(userIds);
                return this;
            }

            public Builder setOpUserId(String opUserId) {
                this.event.setOpUserId(opUserId);
                return this;
            }

            public StartGameEvent build() {
                return this.event;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EndGameEvent {

        @JsonProperty("start_unix_sec")
        private long startUnixSec;

        @JsonProperty("end_unix_sec")
        private long endUnixSec;

        @JsonProperty("user_results")
        private Map<String, UserResult> userResults;

        @JsonProperty("end_type")
        private int endType;

        @JsonProperty("end_ext")
        private String endExt;

        @JsonProperty("cost_coins")
        private int costCoins;

        @JsonProperty("op_user_id")
        private String opUserId;

        public long getStartUnixSec() {
            return startUnixSec;
        }

        public void setStartUnixSec(long startUnixSec) {
            this.startUnixSec = startUnixSec;
        }

        public long getEndUnixSec() {
            return endUnixSec;
        }

        public void setEndUnixSec(long endUnixSec) {
            this.endUnixSec = endUnixSec;
        }

        public Map<String, UserResult> getUserResults() {
            return userResults;
        }

        public void setUserResults(Map<String, UserResult> userResults) {
            this.userResults = userResults;
        }

        public int getEndType() {
            return endType;
        }

        public void setEndType(int endType) {
            this.endType = endType;
        }

        public String getEndExt() {
            return endExt;
        }

        public void setEndExt(String endExt) {
            this.endExt = endExt;
        }

        public int getCostCoins() {
            return costCoins;
        }

        public void setCostCoins(int costCoins) {
            this.costCoins = costCoins;
        }

        public String getOpUserId() {
            return opUserId;
        }

        public void setOpUserId(String opUserId) {
            this.opUserId = opUserId;
        }

        @Override
        public String toString() {
            return "EndGameEvent{" +
                    "startUnixSec=" + startUnixSec +
                    ", endUnixSec=" + endUnixSec +
                    ", userResults=" + userResults +
                    ", endType=" + endType +
                    ", endExt='" + endExt + '\'' +
                    ", costCoins=" + costCoins +
                    ", opUserId='" + opUserId + '\'' +
                    '}';
        }

        public static class UserResult {

            @JsonProperty("score")
            private int score;

            @JsonProperty("rank")
            private int rank;

            @JsonProperty("status")
            private int status;

            @JsonProperty("escape")
            private boolean escape;

            @JsonProperty("trust")
            private boolean trust;

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public boolean isEscape() {
                return escape;
            }

            public void setEscape(boolean escape) {
                this.escape = escape;
            }

            public boolean isTrust() {
                return trust;
            }

            public void setTrust(boolean trust) {
                this.trust = trust;
            }

            @Override
            public String toString() {
                return "UserResult{" +
                        "score=" + score +
                        ", rank=" + rank +
                        ", status=" + status +
                        ", escape=" + escape +
                        ", trust=" + trust +
                        '}';
            }
        }

        public static class Builder {
            private final EndGameEvent event;

            public Builder() {
                this.event = new EndGameEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new ObjectMapper().readValue(json, EndGameEvent.class);
            }

            public Builder setStartUnixSec(long startUnixSec) {
                this.event.setStartUnixSec(startUnixSec);
                return this;
            }

            public Builder setEndUnixSec(long endUnixSec) {
                this.event.setEndUnixSec(endUnixSec);
                return this;
            }

            public Builder setUserResults(Map<String, UserResult> userResults) {
                this.event.setUserResults(userResults);
                return this;
            }

            public Builder setEndType(int endType) {
                this.event.setEndType(endType);
                return this;
            }

            public Builder setEndExt(String endExt) {
                this.event.setEndExt(endExt);
                return this;
            }

            public Builder setCostCoins(int costCoins) {
                this.event.setCostCoins(costCoins);
                return this;
            }

            public Builder setOpUserId(String opUserId) {
                this.event.setOpUserId(opUserId);
                return this;
            }

            public EndGameEvent build() {
                return this.event;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoomUserChangedEvent {

        @JsonProperty("user_ids")
        private String[] userIds;

        @JsonProperty("type")
        private int type;

        @JsonProperty("player_state")
        private Map<String, Boolean> playerState;

        @JsonProperty("ob_user_ids")
        private String[] obUserIds;

        @JsonProperty("gaming")
        private boolean gaming;

        public String[] getUserIds() {
            return userIds;
        }

        public void setUserIds(String[] userIds) {
            this.userIds = userIds;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Map<String, Boolean> getPlayerState() {
            return playerState;
        }

        public void setPlayerState(Map<String, Boolean> playerState) {
            this.playerState = playerState;
        }

        public String[] getObUserIds() {
            return obUserIds;
        }

        public void setObUserIds(String[] obUserIds) {
            this.obUserIds = obUserIds;
        }

        public boolean isGaming() {
            return gaming;
        }

        public void setGaming(boolean gaming) {
            this.gaming = gaming;
        }

        @Override
        public String toString() {
            return "RoomUserChangedEvent{" +
                    "userIds=" + Arrays.toString(userIds) +
                    ", type=" + type +
                    ", playerState=" + playerState +
                    ", obUserIds=" + Arrays.toString(obUserIds) +
                    ", gaming=" + gaming +
                    '}';
        }

        public static class Builder {
            private final RoomUserChangedEvent event;

            public Builder() {
                this.event = new RoomUserChangedEvent();
            }
            
            public Builder(String json) throws JsonProcessingException {
                this.event = new ObjectMapper().readValue(json, RoomUserChangedEvent.class);
            }

            public Builder setUserIds(String[] userIds) {
                this.event.setUserIds(userIds);
                return this;
            }

            public Builder setType(int type) {
                this.event.setType(type);
                return this;
            }

            public Builder setPlayerState(Map<String, Boolean> playerState) {
                this.event.setPlayerState(playerState);
                return this;
            }

            public Builder setObUserIds(String[] obUserIds) {
                this.event.setObUserIds(obUserIds);
                return this;
            }

            public Builder setGaming(boolean gaming) {
                this.event.setGaming(gaming);
                return this;
            }

            public RoomUserChangedEvent build() {
                return this.event;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoomSettingChangedEvent {

        @JsonProperty("setting")
        private String setting;

        @JsonProperty("op_user_id")
        private String opUserId;

        public String getSetting() {
            return setting;
        }

        public void setSetting(String setting) {
            this.setting = setting;
        }

        public String getOpUserId() {
            return opUserId;
        }

        public void setOpUserId(String opUserId) {
            this.opUserId = opUserId;
        }

        @Override
        public String toString() {
            return "RoomSettingChangedEvent{" +
                    "setting='" + setting + '\'' +
                    ", opUserId='" + opUserId + '\'' +
                    '}';
        }

        public static class Builder {
            private final RoomSettingChangedEvent event;

            public Builder() {
                this.event = new RoomSettingChangedEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new ObjectMapper().readValue(json, RoomSettingChangedEvent.class);
            }

            public Builder setSetting(String setting) {
                this.event.setSetting(setting);
                return this;
            }

            public Builder setOpUserId(String opUserId) {
                this.event.setOpUserId(opUserId);
                return this;
            }

            public RoomSettingChangedEvent build() {
                return this.event;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoomSeatSyncEvent {

        @JsonProperty("seat_user_ids")
        private Map<String, String> seatUserIds;

        public Map<String, String> getSeatUserIds() {
            return seatUserIds;
        }

        public void setSeatUserIds(Map<String, String> seatUserIds) {
            this.seatUserIds = seatUserIds;
        }

        @Override
        public String toString() {
            return "RoomSeatSyncEvent{" +
                    "seatUserIds=" + seatUserIds +
                    '}';
        }

        public static class Builder {
            private final RoomSeatSyncEvent event;

            public Builder() {
                this.event = new RoomSeatSyncEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new ObjectMapper().readValue(json, RoomSeatSyncEvent.class);
            }

            public Builder setSeatUserIds(Map<String, String> seatUserIds) {
                this.event.setSeatUserIds(seatUserIds);
                return this;
            }

            public RoomSeatSyncEvent build() {
                return this.event;
            }
        }
    }
}
