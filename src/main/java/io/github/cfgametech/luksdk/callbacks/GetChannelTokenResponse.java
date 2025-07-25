package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 获取渠道令牌响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetChannelTokenResponse {
    @JsonProperty("code")
    private Integer code; // 请求状态码，当值为 0 时表示请求成功
    
    @JsonProperty("msg")
    private String msg; // 请求状态说明
    
    @JsonProperty("data")
    private ResponseData data;

    /**
     * 响应数据
     */
    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class ResponseData {
        @JsonProperty("token")
        private String token; // 用户令牌

        @JsonProperty("left_time")
        private long leftTime; // 剩余秒数
    }
}