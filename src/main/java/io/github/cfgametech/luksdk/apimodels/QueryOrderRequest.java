package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询订单请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrderRequest {
    /**
     * App ID
     */
    @JsonProperty("app_id")
    private Long appId;

    /**
     * 游戏ID
     */
    @NonNull
    @JsonProperty("game_id")
    private Long gameId;

    /**
     * 渠道订单ID，和gameOrderNo二选一
     */
    @JsonProperty("app_order_no")
    private String appOrderNo;

    /**
     * 游戏订单ID，和appOrderNo二选一
     */
    @JsonProperty("game_order_no")
    private String gameOrderNo;

    /**
     * 随机字符串
     */
    @JsonProperty("nonce")
    private String nonce;

    /**
     * 秒级时间戳
     */
    @JsonProperty("timestamp")
    private Long timestamp;

    /**
     * 请求签名
     */
    @JsonProperty("sign")
    private String sign;
}