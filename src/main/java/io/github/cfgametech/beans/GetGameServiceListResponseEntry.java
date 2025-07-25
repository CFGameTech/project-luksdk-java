package io.github.cfgametech.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class GetGameServiceListResponseEntry {
    /**
     * 游戏 ID
     */
    @JsonProperty("g_id")
    private int id;

    /**
     * 游戏名称
     */
    @JsonProperty("g_name")
    private String name;

    /**
     * 游戏 icon
     */
    @JsonProperty("g_icon")
    private String icon;


    /**
     * 游戏地址
     */
    @JsonProperty("g_url")
    private String url;

    /**
     * 游戏预加载包地址
     */
    @JsonProperty("g_zip_url")
    private String gZipUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGZipUrl() {
        return gZipUrl;
    }

    public void setGZipUrl(String gZipUrl) {
        this.gZipUrl = gZipUrl;
    }

    @Override
    public String toString() {
        return "GetGameServiceListResponseEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", gZipUrl='" + gZipUrl + '\'' +
                '}';
    }

    public static class Builder {
        private final GetGameServiceListResponseEntry entry;
        
        public Builder() {
            this.entry = new GetGameServiceListResponseEntry();
        }
        
        public Builder setId(int id) {
            this.entry.setId(id);
            return this;
        }
        
        public Builder setName(String name) {
            this.entry.setName(name);
            return this;
        }
        
        public Builder setIcon(String icon) {
            this.entry.setIcon(icon);
            return this;
        }
        
        public Builder setUrl(String url) {
            this.entry.setUrl(url);
            return this;
        }
        
        public Builder setGZipUrl(String url) {
            this.entry.gZipUrl = url;
            return this;
        }
        
        public GetGameServiceListResponseEntry build() {
            return this.entry;
        }
    }
}
