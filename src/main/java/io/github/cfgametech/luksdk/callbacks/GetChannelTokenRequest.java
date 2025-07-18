package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取渠道令牌请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetChannelTokenRequest {
    @JsonProperty("c_id")
    private Long appId; // 渠道 ID
    
    @JsonProperty("c_uid")
    private String userId; // 渠道用户 ID
    
    @JsonProperty("code")
    private String code; // 渠道用户临时 code
    
    @JsonProperty("timestamp")
    private Long timestamp; // 秒级时间戳
    
    @JsonProperty("sign")
    private String sign; // 请求签名
}