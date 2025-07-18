package io.github.cfgametech.luksdk;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * LukSDK 错误类
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
}