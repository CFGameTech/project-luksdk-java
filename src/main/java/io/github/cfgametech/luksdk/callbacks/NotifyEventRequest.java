package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 通知事件请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NotifyEventRequest {
    @JsonProperty("app_id")
    private Long appId; // App ID

    @JsonProperty("game_id")
    private Long gameId; // 游戏 ID

    @JsonProperty("room_id")
    private String roomId; // 房间 ID

    @JsonProperty("round_id")
    private String roundId; // 游戏局次 ID

    @JsonProperty("sign")
    private String sign; // 请求签名

    @JsonProperty("timestamp")
    private Long timestamp; // 秒级时间戳

    @JsonProperty("type")
    private Integer type; // 事件类型

    @JsonProperty("data")
    private String data; // 事件数据

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StartGame {
        @JsonProperty("op_user_id")
        private String opUserId; // 操作用户 ID

        @JsonProperty("start_ext")
        private String startExt; // 开始游戏透传数据

        @JsonProperty("start_unix_sec")
        private Long startUnixSec; // 游戏开始时间，秒级时间戳

        @JsonProperty("user_ids")
        private List<String> userIds; // 参与游戏的玩家 ID 列表
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GameOver {
        @JsonProperty("cost_coins")
        private Long costCoins; // 本局消耗货币数

        @JsonProperty("end_ext")
        private String endExt; // 结束扩展信息

        @JsonProperty("end_type")
        private Long endType; // 游戏结束类型

        @JsonProperty("end_unix_sec")
        private Long endUnixSec; // 游戏结束时间，秒级时间戳

        @JsonProperty("op_user_id")
        private String opUserId; // 操作用户 ID

        @JsonProperty("start_ext")
        private String startExt; // 开始游戏透传数据

        @JsonProperty("start_unix_sec")
        private Long startUnixSec; // 游戏开始时间，秒级时间戳

        @JsonProperty("user_results")
        private List<GameOverUserResult> userResults; // 玩家游戏结果

        @Data
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class GameOverUserResult {
            @JsonProperty("escape")
            private Boolean escape; // 是否逃跑

            @JsonProperty("rank")
            private Long rank; // 玩家排名

            @JsonProperty("score")
            private Long score; // 玩家得分

            @JsonProperty("status")
            private Integer status; // 玩家状态

            @JsonProperty("trust")
            private Boolean trust; // 是否托管
        }
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoomUserChange {
        @JsonProperty("gaming")
        private Boolean gaming; // 是否游戏进行中

        @JsonProperty("ob_user_ids")
        private List<String> obUserIds; // 观众用户 ID 列表

        @JsonProperty("player_state")
        private Map<String, Boolean> playerState; // 玩家准备状态

        @JsonProperty("type")
        private Integer type; // 变更类型

        @JsonProperty("user_ids")
        private List<String> userIds; // 变更的用户 ID 列表
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoomSettingChange {
        @JsonProperty("op_user_id")
        private String opUserId; // 操作用户 ID

        @JsonProperty("setting")
        private String setting; // 房间设置
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoomSeatSync {
        @JsonProperty("seat_user_ids")
        private Map<Integer, String> seatUserIds; // 座位用户 ID 集合
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoomGameFeature {
        @JsonProperty("feature")
        private String feature; // 游戏特色事件 JSON
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoomPropEquip {
        @JsonProperty("equipped_prop_id")
        private String equippedPropId; // 装配的道具 ID

        @JsonProperty("unequipped_prop_id")
        private String unequippedPropId; // 卸下的道具 ID

        @JsonProperty("user_id")
        private String userId; // 用户 ID
    }
}