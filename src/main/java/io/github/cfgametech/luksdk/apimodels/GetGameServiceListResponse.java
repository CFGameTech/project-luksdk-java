package io.github.cfgametech.luksdk.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * 获取游戏服务列表响应
 */
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetGameServiceListResponse {
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
        @JsonProperty("game_list")
        private List<Game> gameList ;
    }

    /**
     * 游戏信息
     */
    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Game {
        @JsonProperty("g_id")
        private String gameId;
        
        @JsonProperty("g_name")
        private String gameName;
        
        @JsonProperty("g_icon")
        private String gameIcon;
        
        @JsonProperty("g_url")
        private String gameURL;
    }
}