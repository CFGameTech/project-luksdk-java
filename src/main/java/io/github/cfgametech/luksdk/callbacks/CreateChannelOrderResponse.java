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
 * 创建渠道订单响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateChannelOrderResponse {
    @JsonProperty("code")
    private Integer code; // 请求状态码，当值为 0 时表示请求成功
    
    @JsonProperty("msg")
    private String msg; // 请求状态说明
    
    @JsonProperty("data")
    private List<Order> data; // 订单响应列表

    /**
     * 订单响应数据
     */
    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {
        @JsonProperty("c_uid")
        private String userId; // 渠道用户 ID
        
        @JsonProperty("order_id")
        private String orderId; // 渠道订单 ID
        
        @JsonProperty("coins")
        private Long coins; // 用户当前金币
        
        @JsonProperty("status")
        private int status; // 付款状态 1成功 0失败
    }
}