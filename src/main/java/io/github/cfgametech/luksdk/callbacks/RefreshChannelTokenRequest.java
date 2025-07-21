package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 刷新渠道令牌请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefreshChannelTokenRequest {
    @JsonProperty("c_id")
    private String cId; // 渠道 ID
    
    @JsonProperty("c_uid")
    private String userId; // 渠道用户 ID
    
    @JsonProperty("token")
    private String token; // 当前令牌
    
    @JsonProperty("timestamp")
    private Long timestamp; // 时间戳
    
    @JsonProperty("sign")
    private String sign; // 签名

    @JsonProperty("left_time")
    private Long leftTime; // 剩余时间（秒）
}