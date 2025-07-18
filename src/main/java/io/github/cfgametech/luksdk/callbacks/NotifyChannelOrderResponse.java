package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 通知渠道订单响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NotifyChannelOrderResponse {
    @JsonProperty("code")
    private Integer code = 0; // 请求状态码，当值为 0 时表示请求成功
    
    @JsonProperty("msg")
    private String msg; // 请求状态说明

    @JsonProperty("data")
    private Data data; // 请求状态说明

    @Builder
    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        @JsonProperty("c_uid")
        private String userId;

        @JsonProperty("coins")
        private Long coins;

        @JsonProperty("order_id")
        private String orderId;
    }
}