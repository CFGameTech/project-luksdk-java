package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 通知渠道订单请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotifyChannelOrderRequest {
    @JsonProperty("data")
    private List<Order> data;

    @JsonProperty("nonce")
    private String nonce;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("sign")
    private String sign;

    /**
     * 订单数据
     */
    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Order {
        @JsonProperty("c_id")
        private Long appId;

        @JsonProperty("c_uid")
        private String userId;

        @JsonProperty("coins_award")
        private Long coinsAward;

        @JsonProperty("coins_cost")
        private Long coinsCost;

        @JsonProperty("g_id")
        private Long gameId;

        @JsonProperty("game_order_id")
        private String gameOrderID;

        @JsonProperty("status")
        private Long status;

        @JsonProperty("timestamp")
        private Long timestamp;

        @JsonProperty("token")
        private String token;
    }
}