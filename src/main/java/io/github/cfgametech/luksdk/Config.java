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
}