package io.github.cfgametech;

import io.github.cfgametech.beans.*;
import io.github.cfgametech.exceptions.Exceptions;
import io.github.cfgametech.sign.SignUtils;

import java.util.*;

public class SDK {

    private final String signSecret;

    public SDK(String signSecret) {
        this.signSecret = signSecret;
    }

    /**
     * 该函数可用于验证签名是否正确
     * @param sign 签名
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
     * @param params 请求参数结构体
     * @return 签名
     */
    public String generateSignature(Object params) throws IllegalAccessException {
        return SignUtils.signature(signSecret, params);
    }

    /**
     * CFGame向接入方获取用户令牌
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<GetChannelTokenResponse> getChannelToken(GetChannelTokenRequest request, RequestHandler<GetChannelTokenRequest, GetChannelTokenResponse>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 刷新用户令牌过期时间
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<RefreshChannelTokenResponse> refreshChannelToken(RefreshChannelTokenRequest request, RequestHandler<RefreshChannelTokenRequest, RefreshChannelTokenResponse>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 获取渠道用户信息
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<GetChannelUserInfoResponse> getChannelUserInfo(GetChannelUserInfoRequest request, RequestHandler<GetChannelUserInfoRequest, GetChannelUserInfoResponse>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 向渠道下订单
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<List<CreateChannelOrderResponseEntry>> createChannelOrder(CreateChannelOrderRequest request, RequestHandler<CreateChannelOrderRequest, List<CreateChannelOrderResponseEntry>>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 下注开奖通知结果
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<List<NotifyChannelOrderResponseEntry>> notifyChannelOrder(NotifyChannelOrderRequest request, RequestHandler<NotifyChannelOrderRequest, List<NotifyChannelOrderResponseEntry>>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    // NotifyGame 向渠道通知游戏状态
    @SafeVarargs
    public final Response<Empty> notifyGame(NotifyGameRequest request, RequestHandler<NotifyGameRequest, Empty>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    @SafeVarargs
    protected static <Req, Res> Response<Res> generateHandler(String signSecret, String requestSign, Req request, RequestHandler<Req, Res>... successHandler) throws IllegalAccessException {
        String verify = SignUtils.signature(signSecret, request);
        Response<Res> response = new Response<Res>();
        if (!verify.equals(requestSign)) {
            return response.withError(Exceptions.ErrInvalidSignature, requestSign, verify);
        }

        for (RequestHandler<Req, Res> handler : successHandler) {
            try {
                response.withData(handler.handle(request));
            } catch (Exception e) {
                response.withError(Exceptions.ErrChannelDataException, e.getMessage());
            }
        }

        return response;
    }

}


