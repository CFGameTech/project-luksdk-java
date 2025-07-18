package io.github.cfgametech.exceptions;

@Deprecated
public class Exceptions {
    /**
     * 参数有误
     */
    public static final SDKError ErrInvalidParams = regError(1000, "invalid params");          // 
    /**
     * 渠道有误
     */
    public static final SDKError ErrInvalidChannel = regError(1001, "invalid channel");        // 
    /**
     * 渠道请求异常
     */
    public static final SDKError ErrInvalidChannelOrder = regError(1002, "invalid channel request"); // 
    /**
     * 签名有误
     */
    public static final SDKError ErrInvalidSignature = regError(1003, "invalid signature");    // 
    /**
     * 游戏有误
     */
    public static final SDKError ErrInvalidGame = regError(1004, "invalid game");              // 
    /**
     * 渠道返回数据异常
     */
    public static final SDKError ErrChannelDataException = regError(1005, "channel data exception");  // 
    /**
     * 重复下订单
     */
    public static final SDKError ErrRepeatOrder = regError(1006, "repeat order");              // 
    /**
     * 下单失败
     */
    public static final SDKError ErrOrderFailed = regError(1007, "order failed");              // 
    /**
     * 订单不存在
     */
    public static final SDKError ErrOrderNotExist = regError(1008, "order not exist");         // 

    private static SDKError regError(int code, String msg) {
        return new SDKError(code, msg);
    }

    public static class SDKError extends Exception {
        private final int code;
        public SDKError(int code, String message) {
            super(message);
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }
}
