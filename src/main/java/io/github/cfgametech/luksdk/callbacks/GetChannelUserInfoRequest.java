package io.github.cfgametech.luksdk.callbacks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取渠道用户信息请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetChannelUserInfoRequest {
    @JsonProperty("c_id")
    private String cId; // 渠道 ID
    
    @JsonProperty("c_uid")
    private String userId; // 渠道用户 ID
    
    @JsonProperty("timestamp")
    private Long timestamp; // 时间戳
    
    @JsonProperty("sign")
    private String sign; // 签名
}