package io.github.cfgametech.luksdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cfgametech.luksdk.apimodels.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * API 调用类
 */
public class Apis {
    private final LukSDK lukSDK;
    private final ObjectMapper objectMapper;

    public Apis(LukSDK lukSDK) {
        this.lukSDK = lukSDK;
        this.objectMapper = JacksonConfig.createObjectMapper();
    }

    /**
     * 获取游戏服务列表
     */
    public GetGameServiceListResponse getGameServiceList(GetGameServiceListRequest request)
            throws LukSDKException, JsonProcessingException {
        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.ofNullable(request.getTimestamp()).orElse(System.currentTimeMillis() / 1000));

        // 生成签名
        request.setSign(Optional.ofNullable(request.getSign())
                .orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        String json = makeRequest("/sdk/get_game_service_list", request);
        return objectMapper.readValue(json, GetGameServiceListResponse.class);
    }

    /**
     * 查询通知事件
     */
    public QueryNotifyEventResponse queryNotifyEvent(QueryNotifyEventRequest request)
            throws LukSDKException, JsonProcessingException {
        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.ofNullable(request.getTimestamp()).orElse(System.currentTimeMillis() / 1000));

        // 生成签名
        request.setSign(Optional.ofNullable(request.getSign())
                .orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        String json = makeRequest("/sdk/query_notify_event", request);
        return objectMapper.readValue(json, QueryNotifyEventResponse.class);
    }

    /**
     * 查询订单
     */
    public QueryOrderResponse queryOrder(QueryOrderRequest request) throws LukSDKException, JsonProcessingException {
        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.ofNullable(request.getTimestamp()).orElse(System.currentTimeMillis() / 1000));

        // 生成签名
        request.setSign(Optional.ofNullable(request.getSign())
                .orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        String json = makeRequest("/sdk/query_order", request);
        return objectMapper.readValue(json, QueryOrderResponse.class);
    }

    /**
     * 发布控制事件
     */
    public <T extends PublishControlEventResponse.ControlEventResponse> PublishControlEventResponse<T> publishControlEvent(
            PublishControlEventRequest request, Class<T> dataClass) throws LukSDKException, JsonProcessingException {
        // 如果 AppId 为 0，使用配置的
        if (request.getAppId() == null || request.getAppId() == 0) {
            request.setAppId(lukSDK.getConfig().getAppId());
        }

        // 如果时间戳没有
        request.setTimestamp(Optional.ofNullable(request.getTimestamp()).orElse(System.currentTimeMillis() / 1000));

        // 生成签名
        request.setSign(Optional.ofNullable(request.getSign())
                .orElse(SignatureUtils.signature(lukSDK.getConfig().getAppSecret(), request)));

        String json = makeRequest("/sdk/publish_control_event", request);

        if (dataClass == PublishControlEventResponse.Empty.class || dataClass == null) {
            // 仅处理 code 和 msg，不解析 data
            JsonNode root = objectMapper.readTree(json);
            Integer code = root.has("code") && !root.get("code").isNull() ? root.get("code").asInt() : null;
            String msg = root.has("msg") && !root.get("msg").isNull() ? root.get("msg").asText() : null;
            PublishControlEventResponse<T> onlyCodeMsg = PublishControlEventResponse.<T>builder()
                    .code(code)
                    .msg(msg)
                    .build();
            return onlyCodeMsg;
        }

        JavaType type = objectMapper.getTypeFactory().constructParametricType(PublishControlEventResponse.class,
                dataClass);
        return objectMapper.readValue(json, type);
    }

    /**
     * 发送 POST 请求
     */
    private <T> String makeRequest(String endpoint, Object requestBody) throws LukSDKException {
        try {
            String jsonBody = objectMapper.writeValueAsString(requestBody);

            HttpURLConnection conn = getHttpURLConnection(endpoint, jsonBody);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }

                return response.toString();
            } else {
                throw LukSDKExceptions.INTERNAL_ERROR.with("HTTP error: " + responseCode);
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

    private HttpURLConnection getHttpURLConnection(String endpoint, String jsonBody) throws IOException {
        URL url = new URL(lukSDK.getConfig().getDomain() + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("User-Agent", "java/v1.0.3");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return conn;
    }
}