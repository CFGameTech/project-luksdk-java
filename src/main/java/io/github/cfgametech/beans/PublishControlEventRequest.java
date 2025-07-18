package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class PublishControlEventRequest {
    public static final int TYPE_JOIN_GAME_EVENT = 1;
    public static final int TYPE_LEAVE_GAME_EVENT = 2;
    public static final int TYPE_CHANGE_PREPARE_STATE_EVENT = 3;
    public static final int TYPE_KICK_EVENT = 4;
    public static final int TYPE_START_GAME_EVENT = 5;
    public static final int TYPE_FORCE_CLOSE_GAME_EVENT = 6;
    public static final int TYPE_CHANGE_ROOM_SETTING_EVENT = 7;
    public static final int TYPE_CHANGE_USER_IDENTITY_EVENT = 8;
    public static final int TYPE_FETCH_ROOM_SEAT_SYNC_EVENT = 9;
    public static final int TYPE_REFRESH_USER_INFO_EVENT = 10;
    
    @JsonProperty("app_id")
    private int appId;

    @JsonProperty("game_id")
    private int gameId;

    @JsonProperty("room_id")
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
    
    

    public static class Builder {
        private PublishControlEventRequest request = new PublishControlEventRequest();

        public Builder() {
            request = new PublishControlEventRequest();
        }

        public Builder(String json) throws JsonProcessingException {
            request = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, PublishControlEventRequest.class);
        }
        
        public Builder setAppId(int appId) {
            request.setAppId(appId);
            return this;
        }

        public Builder setGameId(int gameId) {
            request.setGameId(gameId);
            return this;
        }

        public Builder setRoomId(String roomId) {
            request.setRoomId(roomId);
            return this;
        }

        public Builder setRoundId(String roundId) {
            request.setRoundId(roundId);
            return this;
        }

        public Builder setSign(String sign) {
            request.setSign(sign);
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            request.setTimestamp(timestamp);
            return this;
        }

        public Builder setType(int type) {
            request.setType(type);
            return this;
        }

        public Builder setData(String data) {
            request.setData(data);
            return this;
        }
        
        public Builder withJoinRoomEvent(JoinGameEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_JOIN_GAME_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public Builder withLeaveRoomEvent(LeaveGameEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_LEAVE_GAME_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public Builder withChangePrepareStateEvent(ChangePrepareStateEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_CHANGE_PREPARE_STATE_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public Builder withKickEvent(KickEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_KICK_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public Builder withStartGameEvent(StartGameEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_START_GAME_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public Builder withForceCloseGameEvent(ForceCloseGameEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_FORCE_CLOSE_GAME_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public Builder withChangeRoomSettingEvent(ChangeRoomSettingEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_CHANGE_ROOM_SETTING_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public Builder withChangeUserIdentityEvent(ChangeUserIdentityEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_CHANGE_USER_IDENTITY_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }
        
        public Builder withFetchRoomSeatSyncEvent() {
            request.setType(TYPE_FETCH_ROOM_SEAT_SYNC_EVENT);
            request.setData("{}");
            return this;
        }
        
        public Builder withRefreshUserInfoEvent(RefreshUserInfoEvent event) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            request.setType(TYPE_REFRESH_USER_INFO_EVENT);
            request.setData(objectMapper.writeValueAsString(event));
            return this;
        }

        public PublishControlEventRequest build() {
            return request;
        }
    }

    @Override
    public String toString() {
        return "PublishControlEventRequest{" +
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

    public static class JoinGameEvent {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("seat")
        private int seat;

        @JsonProperty("ready")
        private boolean ready;

        @JsonProperty("auto_start_num")
        private int autoStartNum;

        // Getters and Setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public boolean isReady() {
            return ready;
        }

        public void setReady(boolean ready) {
            this.ready = ready;
        }

        public int getAutoStartNum() {
            return autoStartNum;
        }

        public void setAutoStartNum(int autoStartNum) {
            this.autoStartNum = autoStartNum;
        }

        @Override
        public String toString() {
            return "JoinGameEvent{" +
                    "userId='" + userId + '\'' +
                    ", seat=" + seat +
                    ", ready=" + ready +
                    ", autoStartNum=" + autoStartNum +
                    '}';
        }

        public static class Builder {
            private final JoinGameEvent event;

            public Builder() {
                this.event = new JoinGameEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, JoinGameEvent.class);
            }

            public Builder setUserId(String userId) {
                this.event.setUserId(userId);
                return this;
            }

            public Builder setSeat(int seat) {
                this.event.setSeat(seat);
                return this;
            }

            public Builder setReady(boolean ready) {
                this.event.setReady(ready);
                return this;
            }

            public Builder setAutoStartNum(int autoStartNum) {
                this.event.setAutoStartNum(autoStartNum);
                return this;
            }
        }
    }

    public static class LeaveGameEvent {

        @JsonProperty("user_id")
        private String userId;

        // Getter and Setter
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "UserIdEvent{" +
                    "userId='" + userId + '\'' +
                    '}';
        }

        public static class Builder {
            private final LeaveGameEvent event;

            public Builder() {
                this.event = new LeaveGameEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, LeaveGameEvent.class);
            }

            public Builder setUserId(String userId) {
                this.event.setUserId(userId);
                return this;
            }

            public LeaveGameEvent build() {
                return this.event;
            }
        }
    }

    public static class ChangePrepareStateEvent {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("is_prepare")
        private boolean isPrepare;

        // Getter and Setter
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public boolean isPrepare() {
            return isPrepare;
        }

        public void setPrepare(boolean isPrepare) {
            this.isPrepare = isPrepare;
        }

        @Override
        public String toString() {
            return "ChangePrepareStateEvent{" +
                    "userId='" + userId + '\'' +
                    ", isPrepare=" + isPrepare +
                    '}';
        }

        public static class Builder {
            private final ChangePrepareStateEvent event;

            public Builder() {
                this.event = new ChangePrepareStateEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, ChangePrepareStateEvent.class);
            }

            public Builder setUserId(String userId) {
                this.event.setUserId(userId);
                return this;
            }

            public Builder setPrepare(boolean isPrepare) {
                this.event.setPrepare(isPrepare);
                return this;
            }

            public ChangePrepareStateEvent build() {
                return this.event;
            }
        }
    }

    public static class KickEvent {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("op_user_id")
        private String opUserId;

        @JsonProperty("reason")
        private String reason;

        // Getters and Setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getOpUserId() {
            return opUserId;
        }

        public void setOpUserId(String opUserId) {
            this.opUserId = opUserId;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        @Override
        public String toString() {
            return "KickEvent{" +
                    "userId='" + userId + '\'' +
                    ", opUserId='" + opUserId + '\'' +
                    ", reason='" + reason + '\'' +
                    '}';
        }

        public static class Builder {
            private final KickEvent event;

            public Builder() {
                this.event = new KickEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, KickEvent.class);
            }

            public Builder setUserId(String userId) {
                this.event.setUserId(userId);
                return this;
            }

            public Builder setOpUserId(String opUserId) {
                this.event.setOpUserId(opUserId);
                return this;
            }

            public Builder setReason(String reason) {
                this.event.setReason(reason);
                return this;
            }

            public KickEvent build() {
                return this.event;
            }
        }
    }


    public static class StartGameEvent {

        @JsonProperty("force")
        private boolean force;

        @JsonProperty("op_user_id")
        private String opUserId;

        // Getter and Setter
        public boolean isForce() {
            return force;
        }

        public void setForce(boolean force) {
            this.force = force;
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
                    "force=" + force +
                    ", opUserId='" + opUserId + '\'' +
                    '}';
        }

        public static class Builder {
            private final StartGameEvent event;

            public Builder() {
                this.event = new StartGameEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, StartGameEvent.class);
            }

            public Builder setForce(boolean force) {
                this.event.setForce(force);
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

    public static class ForceCloseGameEvent {

        @JsonProperty("clear_seat")
        private boolean clearSeat;

        @JsonProperty("op_user_id")
        private String opUserId;

        // Getters and Setters
        public boolean isClearSeat() {
            return clearSeat;
        }

        public void setClearSeat(boolean clearSeat) {
            this.clearSeat = clearSeat;
        }

        public String getOpUserId() {
            return opUserId;
        }

        public void setOpUserId(String opUserId) {
            this.opUserId = opUserId;
        }

        @Override
        public String toString() {
            return "ForceCloseGameEvent{" +
                    "clearSeat=" + clearSeat +
                    ", opUserId='" + opUserId + '\'' +
                    '}';
        }

        public static class Builder {
            private final ForceCloseGameEvent event;

            public Builder() {
                this.event = new ForceCloseGameEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, ForceCloseGameEvent.class);
            }

            public Builder setClearSeat(boolean clearSeat) {
                this.event.setClearSeat(clearSeat);
                return this;
            }

            public Builder setOpUserId(String opUserId) {
                this.event.setOpUserId(opUserId);
                return this;
            }

            public ForceCloseGameEvent build() {
                return this.event;
            }
        }
    }

    public static class ChangeRoomSettingEvent {

        @JsonProperty("op_user_id")
        private String opUserId;

        @JsonProperty("room_setting")
        private String roomSetting;

        // Getter and Setter
        public String getOpUserId() {
            return opUserId;
        }

        public void setOpUserId(String opUserId) {
            this.opUserId = opUserId;
        }

        public String getRoomSetting() {
            return roomSetting;
        }

        public void setRoomSetting(String roomSetting) {
            this.roomSetting = roomSetting;
        }

        @Override
        public String toString() {
            return "ChangeRoomSettingEvent{" +
                    "opUserId='" + opUserId + '\'' +
                    ", roomSetting='" + roomSetting + '\'' +
                    '}';
        }

        public static class Builder {
            private final ChangeRoomSettingEvent event;

            public Builder() {
                this.event = new ChangeRoomSettingEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, ChangeRoomSettingEvent.class);
            }

            public Builder setOpUserId(String opUserId) {
                this.event.setOpUserId(opUserId);
                return this;
            }

            public Builder setRoomSetting(String roomSetting) {
                this.event.setRoomSetting(roomSetting);
                return this;
            }

            public ChangeRoomSettingEvent build() {
                return this.event;
            }
        }
    }
    

    public static class ChangeUserIdentityEvent {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("identity")
        private int identity;

        // Getters and Setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        @Override
        public String toString() {
            return "ChangeUserIdentityEvent{" +
                    "userId='" + userId + '\'' +
                    ", identity=" + identity +
                    '}';
        }

        public static class Builder {
            private final ChangeUserIdentityEvent event;

            public Builder() {
                this.event = new ChangeUserIdentityEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, ChangeUserIdentityEvent.class);
            }

            public Builder setUserId(String userId) {
                this.event.setUserId(userId);
                return this;
            }

            public Builder setIdentity(int identity) {
                this.event.setIdentity(identity);
                return this;
            }

            public ChangeUserIdentityEvent build() {
                return this.event;
            }
        }
    }
    

    public static class RefreshUserInfoEvent {

        @JsonProperty("user_id")
        private String userId;

        // Getter and Setter
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "RefreshUserInfoEvent{" +
                    "userId='" + userId + '\'' +
                    '}';
        }

        public static class Builder {
            private final RefreshUserInfoEvent event;

            public Builder() {
                this.event = new RefreshUserInfoEvent();
            }

            public Builder(String json) throws JsonProcessingException {
                this.event = new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, RefreshUserInfoEvent.class);
            }

            public Builder setUserId(String userId) {
                this.event.setUserId(userId);
                return this;
            }

            public RefreshUserInfoEvent build() {
                return this.event;
            }
        }
    }
}
