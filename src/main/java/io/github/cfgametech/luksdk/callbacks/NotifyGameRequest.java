package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * 通知游戏请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotifyGameRequest {
    @JsonProperty("c_id")
    private Long appId; // App ID

    @JsonProperty("data")
    private String data; // 通知数据

    @JsonProperty("ext")
    private String ext; // 房间透传字段

    @JsonProperty("g_id")
    private Long gameId; // 游戏 ID

    @JsonProperty("notify_type")
    private Long notifyType; // 通知类型枚举

    @JsonProperty("sign")
    private String sign; // 请求签名

    @JsonProperty("timestamp")
    private Long timestamp; // 秒级时间戳

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class GameStartBeforeData {
        @JsonProperty("game_setting")
        private String gameSetting; // 游戏当前设置

        @JsonProperty("notify_action")
        private Long notifyAction; // 操作

        @JsonProperty("player_ready_status")
        private Map<String, Boolean> playerReadyStatus; // 是否已准备，字典键指向用户ID

        @JsonProperty("room_id")
        private String roomId; // 房间ID

        @JsonProperty("round_id")
        private String roundId; // 游戏局次ID
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class GameRunningData {
        @JsonProperty("notify_action")
        private Long notifyAction; // 操作

        @JsonProperty("player_num")
        private Long playerNum; // 玩家数量

        @JsonProperty("player_uids")
        private List<String> playerUids; // 玩家ID列表

        @JsonProperty("room_id")
        private String roomId; // 房间ID

        @JsonProperty("round_id")
        private String roundId; // 游戏局次ID
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class GameEndData {
        @JsonProperty("is_force_end")
        private Boolean isForceEnd; // 是否强制结束游戏

        @JsonProperty("notify_action")
        private Long notifyAction; // 操作

        @JsonProperty("player_score")
        private Map<String, Long> playerScore; // 玩家分数，字典键指向用户ID

        @JsonProperty("rank")
        private List<String> rank; // 排名，根据排名排序的玩家ID数组

        @JsonProperty("room_id")
        private String roomId; // 房间ID

        @JsonProperty("round_id")
        private String roundId; // 游戏局次ID
    }
}