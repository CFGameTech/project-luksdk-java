package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class GetGameServiceListResponse {
    /**
     * 用户 ID
     */
    @JsonProperty("game_list")
    private List<GetGameServiceListResponseEntry> gameList;
    
    public List<GetGameServiceListResponseEntry> getGameList() {
        return gameList;
    }
    
    public void setGameList(List<GetGameServiceListResponseEntry> gameList) {
        this.gameList = gameList;
    }

    @Override
    public String toString() {
        return "GetGameServiceListResponse{" +
                "gameList=" + gameList +
                '}';
    }

    public static class Builder {
        private List<GetGameServiceListResponseEntry> gameList;
        
        public Builder() {
            this.gameList = new java.util.ArrayList<>();
        }

        public Builder setGameList(List<GetGameServiceListResponseEntry> gameList) {
            this.gameList = gameList;
            return this;
        }
        
        public GetGameServiceListResponse build() {
            GetGameServiceListResponse response = new GetGameServiceListResponse();
            response.setGameList(gameList);
            return response;
        }
    }
}
