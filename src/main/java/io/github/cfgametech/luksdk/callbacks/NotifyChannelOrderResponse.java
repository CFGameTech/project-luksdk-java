package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 通知渠道订单响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotifyChannelOrderResponse {
    @JsonProperty("code")
    private Integer code;
    
    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private ResponseData data;

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ResponseData {
        @JsonProperty("c_uid")
        private String userId;

        @JsonProperty("coins")
        private Long coins;

        @JsonProperty("order_id")
        private String orderId;
    }
}