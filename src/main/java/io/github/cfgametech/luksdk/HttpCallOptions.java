package io.github.cfgametech.luksdk;

import lombok.Builder;
import lombok.Value;

/**
 * 单次 HTTP 调用的超时覆盖；未设置的字段沿用 {@link Config} 中的默认值。
 */
@Value
@Builder
public class HttpCallOptions {
    /**
     * 建立连接超时（毫秒）。{@code null} 表示不覆盖；与 {@link Config} 均为 {@code null} 时使用 JDK 默认行为。
     */
    Integer connectTimeoutMillis;
    /**
     * 读取响应超时（毫秒）。{@code null} 表示不覆盖。
     */
    Integer readTimeoutMillis;
}
