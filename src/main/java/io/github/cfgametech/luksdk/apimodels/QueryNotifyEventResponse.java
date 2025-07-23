package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * 查询通知事件响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryNotifyEventResponse {
    @JsonProperty("code")
    private Integer code;
    
    @JsonProperty("msg")
    private String msg;
    
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
    public static class ResponseData {
        @JsonProperty("list")
        private List<Event> list;

        /**
         * 事件信息
         */
        @Builder
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        public static class Event {
            @JsonProperty("type")
            private Integer type;

            @JsonProperty("data")
            private String data;
        }
    }

}