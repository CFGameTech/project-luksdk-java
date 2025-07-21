package io.github.cfgametech.luksdk;

import io.github.cfgametech.luksdk.apimodels.*;
import io.github.cfgametech.luksdk.callbacks.*;
import okhttp3.OkHttpClient;

/**
 * LukSDK Java 使用示例
 */
public class Example {
    private static final Long appId = Long.valueOf(System.getenv("TEST_APP_ID"));
    private static final String appSecret = System.getenv("TEST_APP_SECRET");
    private static final String domain = System.getenv("TEST_DOMAIN");
    private static final LukSDK lukSDK = new LukSDK(Config.builder().
            appId(appId).
            appSecret(appSecret).
            domain(domain).
            httpClient(new OkHttpClient()).
            build());

    public static void main(String[] args) throws LukSDKException {
        // 普通请求
        GetGameServiceListResponse response = lukSDK.getApis().getGameServiceList(new GetGameServiceListRequest());
        System.out.println(response.toString());

        // 回调请求
        GetChannelTokenRequest request = GetChannelTokenRequest.builder().appId(appId).build();
        request.setSign(SignatureUtils.signature(appSecret, request));

        // 签名验证
        if (!SignatureUtils.signature(appSecret, request).equals(request.getSign())) {
            throw LukSDKExceptions.SIGN_ERROR;
        }
    }
}