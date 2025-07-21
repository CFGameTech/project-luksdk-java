package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询订单响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public@ToString
class QueryOrderResponse {
    @JsonProperty("code")
    private int code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private Data data;

    /**
     * 订单数据
     */
    @Builder
    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Data {
        @JsonProperty("app_id")
        private long appId;

        @JsonProperty("app_order_id")
        private String appOrderId;

        @JsonProperty("game_order_id")
        private String gameOrderId;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("item_id")
        private String itemId;

        @JsonProperty("num")
        private Long num;

        @JsonProperty("coins_cost")
        private Long coinsCost;

        @JsonProperty("coins_award")
        private Long coinsAward;

        @JsonProperty("gain")
        private Long gain;

        @JsonProperty("anchor_draw")
        private Long anchorDraw;

        @JsonProperty("coins_official_draw")
        private Long coinsOfficialDraw;

        @JsonProperty("pay_status")
        private int payStatus;

        @JsonProperty("pay_game_status")
        private int payGameStatus;

        @JsonProperty("create_time")
        private long createTime;

        @JsonProperty("ext")
        private String ext;
    }
}