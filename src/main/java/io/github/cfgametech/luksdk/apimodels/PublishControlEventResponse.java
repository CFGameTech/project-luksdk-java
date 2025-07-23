package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * 发布控制事件响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublishControlEventResponse<T extends PublishControlEventResponse.ControlEventResponse> {
    @JsonProperty("code")
    private Integer code;
    
    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private T data;

    public interface ControlEventResponse { }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @ToString
    public static class Empty implements ControlEventResponse {}

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class FetchBagStatusResponse implements ControlEventResponse {

        @JsonProperty("props")
        private List<Prop> propList;


        @Builder
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        public static class Prop {
            @JsonProperty("expire_time")
            private Long expireTime;

            @JsonProperty("is_equipped")
            private Boolean isEquipped;

            @JsonProperty("num")
            private Long num;

            @JsonProperty("prop_id")
            private String propId;

            @JsonProperty("prop_type")
            private Integer propType;
        }
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class QueryIssuePropStatus implements ControlEventResponse {
        @JsonProperty("app_id")
        private Long appId;

        @JsonProperty("unique_id")
        private String uniqueId;

        @JsonProperty("game_id")
        private Long gameId;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("created_time")
        private Long createdTime;

        @JsonProperty("status")
        private Long status;

        @JsonProperty("details")
        private java.util.List<Detail> details;

        @JsonProperty("extra")
        private String extra;

        @Builder
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        public static class Detail {
            @JsonProperty("prop_id")
            private String propId;

            @JsonProperty("num")
            private Long num;

            @JsonProperty("duration")
            private Long duration;

            @JsonProperty("duration_reset")
            private Boolean durationReset;
        }
    }
}