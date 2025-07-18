package io.github.cfgametech.luksdk;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * LukSDK 客户端
 */
@RequiredArgsConstructor
@Getter
@Builder
public class LukSDK {
    protected final Config config;
    private final Apis apis = new Apis(this);
}