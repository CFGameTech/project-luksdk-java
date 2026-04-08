package io.github.cfgametech.luksdk;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * SDK 配置类
 */
@Data
@RequiredArgsConstructor
@Builder
public class Config {
    @NonNull
    private final Long appId;
    @NonNull
    private final String appSecret;
    @NonNull
    private final String domain;

    /**
     * 建立连接超时（毫秒）。{@code null} 表示不设置，由 JDK 决定。
     */
    @Builder.Default
    private final Integer connectTimeoutMillis = null;
    /**
     * 读取响应超时（毫秒）。{@code null} 表示不设置。
     */
    @Builder.Default
    private final Integer readTimeoutMillis = null;
}