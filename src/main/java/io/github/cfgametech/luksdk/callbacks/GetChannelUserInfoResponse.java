package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 获取渠道用户信息响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetChannelUserInfoResponse {
    @JsonProperty("code")
    private Integer code; // 请求状态码，当值为 0 时表示请求成功
    
    @JsonProperty("msg")
    private String msg; // 请求状态说明
    
    @JsonProperty("data")
    private ResponseData data; // 响应数据

    /**
     * 响应数据
     */
    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ResponseData {
        @JsonProperty("nickname")
        private String nickname; // 用户昵称
        
        @JsonProperty("avatar")
        private String avatar; // 用户头像
        
        @JsonProperty("coins")
        private Long coins; // 用户金币

        @JsonProperty("identity")
        private Integer identity;
    }
}