package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 刷新渠道令牌响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefreshChannelTokenResponse {
    @JsonProperty("code")
    private int code; // 请求状态码，当值为 0 时表示请求成功
    
    @JsonProperty("msg")
    private String msg; // 请求状态说明
    
    @JsonProperty("data")
    private Data data; // 响应数据

    /**
     * 响应数据
     */
    @Builder
    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Data {
        @JsonProperty("token")
        private String token; // 新的令牌

        @JsonProperty("left_time")
        private Long leftTime; // 剩余时间（秒）
    }
}