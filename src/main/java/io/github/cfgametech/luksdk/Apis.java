package io.github.cfgametech.luksdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cfgametech.luksdk.apimodels.*;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

/**
 * API 调用类
 */
public class Apis {
    private final LukSDK lukSDK;
    private final ObjectMapper objectMapper;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public Apis(LukSDK lukSDK) {
        this.lukSDK = lukSDK;
        this.objectMapper = JacksonConfig.createObjectMapper();
    }

    /**
     * 获取游戏服务列表
     */
    public GetGameServiceListResponse getGameServiceList(GetGameServiceListRequest request) throws LukSDKException {
        // 生成签名
        request.setSign(Optional.
                ofNullable(request.getSign()).
                orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.
                ofNullable(request.getTimestamp()).
                orElse(System.currentTimeMillis() / 1000));

        return makeRequest("/sdk/get_game_service_list", request, GetGameServiceListResponse.class);
    }

    /**
     * 查询通知事件
     */
    public QueryNotifyEventResponse queryNotifyEvent(QueryNotifyEventRequest request) throws LukSDKException {
        // 生成签名
        request.setSign(Optional.
                ofNullable(request.getSign()).
                orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.
                ofNullable(request.getTimestamp()).
                orElse(System.currentTimeMillis() / 1000));

        return makeRequest("/sdk/query_notify_event", request, QueryNotifyEventResponse.class);
    }

    /**
     * 查询订单
     */
    public QueryOrderResponse queryOrder(QueryOrderRequest request) throws LukSDKException {
        // 生成签名
        request.setSign(Optional.
                ofNullable(request.getSign()).
                orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.
                ofNullable(request.getTimestamp()).
                orElse(System.currentTimeMillis() / 1000));

        return makeRequest("/sdk/query_order", request, QueryOrderResponse.class);
    }

    /**
     * 发布控制事件
     */
    public PublishControlEventResponse publishControlEvent(PublishControlEventRequest request) throws LukSDKException {
        // 生成签名
        request.setSign(Optional.
                ofNullable(request.getSign()).
                orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.
                ofNullable(request.getTimestamp()).
                orElse(System.currentTimeMillis() / 1000));

        return makeRequest("/sdk/publish_control_event", request, PublishControlEventResponse.class);
    }

    /**
     * 发送 POST 请求
     */
    private <T> T makeRequest(String endpoint, Object requestBody, Class<T> responseClass) throws LukSDKException {
        try {
            // 序列化请求体
            String jsonBody = objectMapper.writeValueAsString(requestBody);

            // 构建请求
            RequestBody body = RequestBody.create(jsonBody, JSON);
            Request request = new Request.Builder()
                    .url(lukSDK.getConfig().getDomain() + endpoint)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", "java/v1.0.0")
                    .build();

            // 发送请求
            try (Response response = lukSDK.getConfig().getHttpClient().newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw LukSDKExceptions.INTERNAL_ERROR.with("HTTP error: " + response.code());
                }

                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    throw LukSDKExceptions.INTERNAL_ERROR.with("Empty response body");
                }

                String responseJson = responseBody.string();

                // 反序列化响应
                return objectMapper.readValue(responseJson, responseClass);
            }
        } catch (IOException e) {
            throw LukSDKExceptions.INTERNAL_ERROR.with("Request failed: " + e.getMessage());
        } catch (Exception e) {
            if (e instanceof LukSDKException) {
                throw (LukSDKException) e;
            }
            throw LukSDKExceptions.INTERNAL_ERROR.with("Unexpected error: " + e.getMessage());
        }
    }
}