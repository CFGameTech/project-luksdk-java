package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 获取游戏服务列表请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetGameServiceListRequest {
    @JsonProperty("c_id")
    private Long appId;

    @JsonProperty("timestamp")
    private Long timestamp;
    
    @JsonProperty("sign")
    private String sign;
}