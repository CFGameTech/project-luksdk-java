package io.github.cfgametech.luksdk;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * LukSDK 错误类。
 * <p>网络/IO 等场景下若由底层异常触发，可通过 {@link Throwable#getCause()} 取得原始异常（如超时时的
 * {@link java.net.SocketTimeoutException}）。</p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LukSDKException extends Exception {
    private final int code;
    private final String msg;

    public LukSDKException(int code, String msg) {
        super(String.format("code: %d, msg: %s", code, msg));
        this.code = code;
        this.msg = msg;
    }

    public LukSDKException(int code, String msg, Throwable cause) {
        super(String.format("code: %d, msg: %s", code, msg), cause);
        this.code = code;
        this.msg = msg;
    }

    public LukSDKException with(Object... messages) {
        StringBuilder sb = new StringBuilder(this.msg);
        if (messages.length > 0) {
            sb.append(": ");
            for (int i = 0; i < messages.length; i++) {
                if (i > 0) sb.append(", ");
                sb.append(messages[i]);
            }
        }
        return new LukSDKException(this.code, sb.toString());
    }

    /**
     * 与 {@link #with(Object...)} 相同的消息拼接规则，并保留 {@code cause} 供 {@link #getCause()} 使用。
     */
    public LukSDKException withCause(Throwable cause, Object... messages) {
        StringBuilder sb = new StringBuilder(this.msg);
        if (messages.length > 0) {
            sb.append(": ");
            for (int i = 0; i < messages.length; i++) {
                if (i > 0) sb.append(", ");
                sb.append(messages[i]);
            }
        }
        return new LukSDKException(this.code, sb.toString(), cause);
    }
}