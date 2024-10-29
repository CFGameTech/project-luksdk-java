package io.github.cfgametech;

/**
 * @param <T> Request 请求报文结构
 * @param <R> Response 业务数据响应报文结构
 */
public interface RequestHandler<T, R> {
    R handle(T request) throws Exception;
}