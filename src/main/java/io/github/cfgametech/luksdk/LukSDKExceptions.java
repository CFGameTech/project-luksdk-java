package io.github.cfgametech.luksdk;

/**
 * LukSDK 错误常量
 */
public class LukSDKExceptions {
    public static final LukSDKException INTERNAL_ERROR = new LukSDKException(100000, "LukSDK: 服务器内部异常");
    public static final LukSDKException PARAM_ERROR = new LukSDKException(100001, "LukSDK: 参数错误");
    public static final LukSDKException RETRY_ERROR = new LukSDKException(100002, "LukSDK: 请稍后再试");
    public static final LukSDKException CONTENT_ERROR = new LukSDKException(100003, "LukSDK: 资源不存在");
    public static final LukSDKException CHANNEL_ERROR = new LukSDKException(100004, "LukSDK: 渠道已禁用");
    public static final LukSDKException SIGN_ERROR = new LukSDKException(100005, "LukSDK: 签名校验失败");
    public static final LukSDKException LOGIN_ERROR = new LukSDKException(100006, "LukSDK: 未登录或 Token 已过期");
    public static final LukSDKException CALLBACK_ERROR = new LukSDKException(100007, "LukSDK: 渠道方回调地址响应解析失败");

    /**
     * 解析错误码
     */
    public static int parseErrorCode(Throwable error) {
        if (error instanceof LukSDKException) {
            return ((LukSDKException) error).getCode();
        }
        return INTERNAL_ERROR.getCode();
    }
}