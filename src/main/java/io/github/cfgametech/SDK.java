package io.github.cfgametech;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cfgametech.beans.*;
import io.github.cfgametech.exceptions.Exceptions;
import io.github.cfgametech.sign.SignUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SDK {
    private final String signSecret;
    private final String domain;
    private final String apiPrefix = "/sdk";

    /**
     * @param signSecret 签名密钥
     * @param domain     域名，如：<a href="https://www.abc.example">https://www.abc.example</a>
     */
    public SDK(String signSecret, String domain) {
        this.signSecret = signSecret;
        this.domain = domain;
    }

    /**
     * @param channelId 渠道 ID
     * @return 包含游戏列表的响应
     */
    public Response<GetGameServiceListResponse> GetGameServiceList(int channelId) throws Exception {
        GetGameServiceListRequest request = new GetGameServiceListRequest.Builder()
                .setChannelId(channelId)
                .setTimestamp(System.currentTimeMillis())
                .build();
        return GetGameServiceList(request);
    }

    /**
     * @param request 给定的请求，其中签名字段如果为空字符串将自动计算签名
     * @return 包含游戏列表的响应
     */
    public Response<GetGameServiceListResponse> GetGameServiceList(GetGameServiceListRequest request) throws IllegalAccessException, IOException {
        if (domain.isEmpty()) {
            throw new RuntimeException("domain is empty");
        }

        String url = domain + apiPrefix + "/get_game_service_list/";
        ObjectMapper objectMapper = new ObjectMapper();
        if (request.getSign() == null || request.getSign().isEmpty()) {
            request.setSign(generateSignature(request));
        }
        String jsonInputString = objectMapper.writeValueAsString(request);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            String responseJsonStr = response.toString();
            ObjectMapper responseObjectMapper = new ObjectMapper();
            Response<GetGameServiceListResponse> responseObject = responseObjectMapper.readValue(responseJsonStr, new TypeReference<Response<GetGameServiceListResponse>>() {
            });

            if (responseObject.getCode() != 0) {
                throw new RuntimeException("Error Code: " + responseObject.getCode() + " Message: " + responseObject.getMessage());
            }
            return responseObject;
        } else {
            throw new RuntimeException("Url: " + url + " Error Code: " + responseCode);
        }

    }


    /**
     * @param request 给定的请求，其中签名字段如果为空字符串将自动计算签名
     * @return 包含游戏列表的响应
     */
    public <T> Response<T> PublishControlEvent(PublishControlEventRequest request) throws IllegalAccessException, IOException {
        if (domain.isEmpty()) {
            throw new RuntimeException("domain is empty");
        }

        String url = domain + apiPrefix + "/publish_control_event/";
        ObjectMapper objectMapper = new ObjectMapper();
        if (request.getSign() == null || request.getSign().isEmpty()) {
            request.setSign(generateSignature(request));
        }
        String jsonInputString = objectMapper.writeValueAsString(request);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            String responseJsonStr = response.toString();
            ObjectMapper responseObjectMapper = new ObjectMapper();
            Response<T> responseObject = responseObjectMapper.readValue(responseJsonStr, new TypeReference<Response<T>>() {
            });

            if (responseObject.getCode() != 0) {
                throw new RuntimeException("Error Code: " + responseObject.getCode() + " Message: " + responseObject.getMessage());
            }
            return responseObject;
        } else {
            throw new RuntimeException("Url: " + url + " Error Code: " + responseCode);
        }

    }

    /**
     * 该函数可用于验证签名是否正确
     *
     * @param sign   签名
     * @param params 请求参数结构体
     * @return true 验证通过
     * @throws Exceptions.SDKError 签名错误
     */
    public boolean verifySignature(String sign, Object params) throws Exceptions.SDKError, IllegalAccessException {
        String verify = SignUtils.signature(signSecret, params);
        if (!verify.equals(sign)) {
            throw Exceptions.ErrInvalidSignature;
        }
        return true;
    }

    /**
     * 生成签名
     *
     * @param params 请求参数结构体
     * @return 签名
     */
    public String generateSignature(Object params) throws IllegalAccessException {
        return SignUtils.signature(signSecret, params);
    }

    /**
     * CFGame向接入方获取用户令牌
     *
     * @param request        请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<GetChannelTokenResponse> getChannelToken(GetChannelTokenRequest request, RequestHandler<GetChannelTokenRequest, GetChannelTokenResponse>... successHandler) {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 刷新用户令牌过期时间
     *
     * @param request        请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<RefreshChannelTokenResponse> refreshChannelToken(RefreshChannelTokenRequest request, RequestHandler<RefreshChannelTokenRequest, RefreshChannelTokenResponse>... successHandler) {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 获取渠道用户信息
     *
     * @param request        请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<GetChannelUserInfoResponse> getChannelUserInfo(GetChannelUserInfoRequest request, RequestHandler<GetChannelUserInfoRequest, GetChannelUserInfoResponse>... successHandler) {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 向渠道下订单
     *
     * @param request        请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<List<CreateChannelOrderResponseEntry>> createChannelOrder(CreateChannelOrderRequest request, RequestHandler<CreateChannelOrderRequest, List<CreateChannelOrderResponseEntry>>... successHandler) {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 下注开奖通知结果
     *
     * @param request        请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<List<NotifyChannelOrderResponseEntry>> notifyChannelOrder(NotifyChannelOrderRequest request, RequestHandler<NotifyChannelOrderRequest, List<NotifyChannelOrderResponseEntry>>... successHandler) {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    // NotifyGame 向渠道通知游戏状态
    @SafeVarargs
    public final Response<Empty> notifyGame(NotifyGameRequest request, RequestHandler<NotifyGameRequest, Empty>... successHandler) {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    // NotifyGame 游戏通知事件回调
    @SafeVarargs
    public final Response<Empty> notifyEvent(NotifyEventRequest request, RequestHandler<NotifyEventRequest, Empty>... successHandler) {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    @SafeVarargs
    protected static <Req, Res> Response<Res> generateHandler(String signSecret, String requestSign, Req request, RequestHandler<Req, Res>... successHandler) {
        Response<Res> response = new Response<Res>();
        try {
            String verify = SignUtils.signature(signSecret, request);
            if (!verify.equals(requestSign)) {
                return response.withError(Exceptions.ErrInvalidSignature, requestSign, verify);
            }

            for (RequestHandler<Req, Res> handler : successHandler) {
                try {
                    Res res = handler.handle(request);
                    if (res instanceof Empty) {
                        res = null;
                    }
                    response.withData(res);
                } catch (Exception e) {
                    response.withError(Exceptions.ErrChannelDataException, e.getMessage());
                }
            }

            return response;
        } catch (IllegalAccessException e) {
            return response.withError(Exceptions.ErrInvalidSignature, requestSign, e.getMessage());
        }
    }
}


