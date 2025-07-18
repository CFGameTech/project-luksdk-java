package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取游戏服务列表请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetGameServiceListRequest {
    @JsonProperty("c_id")
    private Long appId;

    @JsonProperty("timestamp")
    private Long timestamp;
    
    @JsonProperty("sign")
    private String sign;
}