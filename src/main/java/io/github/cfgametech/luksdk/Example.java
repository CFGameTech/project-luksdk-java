package io.github.cfgametech.luksdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.cfgametech.luksdk.apimodels.*;
import io.github.cfgametech.luksdk.callbacks.*;

/**
 * LukSDK Java 使用示例
 */
public class Example {
    private static final Long appId = Long.valueOf(System.getenv("TEST_APP_ID"));
    private static final String appSecret = System.getenv("TEST_APP_SECRET");
    private static final String domain = System.getenv("TEST_DOMAIN");
    private static final LukSDK lukSDK = new LukSDK(Config.builder().
            appId(1045111L).
            appSecret("55428f6455ef4b1810c9ecb8be6a").
            domain("https://pre.luk.live").
            build());

    public static void main(String[] args) throws LukSDKException, JsonProcessingException {
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

        // 控制事件请求
        PublishControlEventRequest fetchBagStatusRequest = PublishControlEventRequest.builder().
                appId(1045111L).
                gameId(102L).
                type(1001).
                data(JacksonConfig.createObjectMapper().writeValueAsString(PublishControlEventRequest.FetchBagStatus.builder().
                        userId("1355432380151733890").
                        build())).
                build();
        PublishControlEventResponse<PublishControlEventResponse.FetchBagStatusResponse> publishControlEventResponse = lukSDK.getApis().
                publishControlEvent(fetchBagStatusRequest, PublishControlEventResponse.FetchBagStatusResponse.class);

        System.out.println(publishControlEventResponse.getCode());
        System.out.println(publishControlEventResponse.getMsg());
        publishControlEventResponse.getData().getPropList().forEach(prop -> {
            System.out.println(prop.toString());
        });
    }
}