package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cfgametech.luksdk.Enums;
import lombok.*;

import java.util.List;

/**
 * 发布控制事件请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PublishControlEventRequest {
    @JsonProperty("app_id")
    private Long appId;

    @NonNull
    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("room_id")
    private String roomId;

    @NonNull
    @JsonProperty("type")
    private Integer type;

    @JsonProperty("data")
    private String data;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("sign")
    private String sign = "";

    public interface ControlEvent {
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinGame implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("seat")
        private Integer seat;

        @JsonProperty("ready")
        private Boolean ready;

        @JsonProperty("auto_start_num")
        private Integer autoStartNum;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LeaveGame implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangeReadyStatus implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("is_prepare")
        private Boolean isPrepare;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KickPlayer implements ControlEvent {
        @JsonProperty("op_user_id")
        private String opUserId;

        @NonNull
        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("reason")
        private String reason;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StartGame implements ControlEvent {

        @JsonProperty("op_user_id")
        private String opUserId;

        @JsonProperty("force")
        private Boolean force;

        @JsonProperty("start_ext")
        private String startExt;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ForceCloseGame implements ControlEvent {
        @JsonProperty("op_user_id")
        private String opUserId;

        @JsonProperty("clear_seat")
        private Boolean clearSeat;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangeRoomSetting implements ControlEvent {
        @JsonProperty("op_user_id")
        private String opUserId;

        @NonNull
        @JsonProperty("room_setting")
        private String roomSetting;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangeUserIdentity implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;

        @NonNull
        @JsonProperty("identity")
        private Enums.Identity identity; // 需定义 Identity 类
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoomSeatSync implements ControlEvent {

    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RefreshUserInfo implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuickStartGame implements ControlEvent {
        @JsonProperty("setting")
        private String setting;

        @JsonProperty("start_game")
        private Boolean startGame;

        @JsonProperty("user_ids")
        private java.util.List<String> userIds;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IssueProps implements ControlEvent {
        @NonNull
        @JsonProperty("unique_id")
        private String uniqueId;

        @NonNull
        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("details")
        private List<Detail> details;

        @JsonProperty("extra")
        private String extra;

        @Builder
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Detail {
            @NonNull
            @JsonProperty("prop_id")
            private String propId;

            @NonNull
            @JsonProperty("num")
            private Long num;

            @JsonProperty("duration")
            private Long duration;

            @JsonProperty("duration_reset")
            private Boolean durationReset;
        }
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FetchBagStatus implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QueryIssuePropStatus implements ControlEvent {
        @NonNull
        @JsonProperty("unique_id")
        private String uniqueId;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EquippedProp implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;

        @NonNull
        @JsonProperty("equipped_prop_id")
        private String equippedPropId;
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnequippedProp implements ControlEvent {
        @NonNull
        @JsonProperty("user_id")
        private String userId;

        @NonNull
        @JsonProperty("unequipped_prop_id")
        private String unequippedPropId;
    }
}