package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 创建渠道订单请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateChannelOrderRequest {
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
    public static class Order {
        @JsonProperty("c_id")
        private Long appId;

        @JsonProperty("g_id")
        private Long gameId;

        @JsonProperty("room_id")
        private String roomID;

        @JsonProperty("c_uid")
        private String userId;

        @JsonProperty("coins_cost")
        private Long coinsCost;

        @JsonProperty("ext")
        private String ext;

        @JsonProperty("game_order_id")
        private String gameOrderID;

        @JsonProperty("timestamp")
        private Long timestamp;

        @JsonProperty("token")
        private String token;
    }
}