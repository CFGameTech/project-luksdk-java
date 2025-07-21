package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


/**
 * 查询通知事件请求
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryNotifyEventRequest {
    @JsonProperty("app_id")
    private Long appId;

    @NonNull
    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("sign")
    private String sign;

    @JsonProperty("type")
    private Long type;

    @JsonProperty("start_at")
    private Long startAt;

    @JsonProperty("end_at")
    private Long endAt;

    @JsonProperty("timestamp")
    private Long timestamp;

    @NonNull
    @JsonProperty("page_no")
    private Long pageNo;

    @NonNull
    @JsonProperty("page_size")
    private Long pageSize;
}