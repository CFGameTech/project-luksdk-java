package io.github.cfgametech.luksdk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

/**
 * Jackson 配置类 - 提供宽松的序列化和反序列化配置
 */
public class JacksonConfig {

    /**
     * 创建配置好的 ObjectMapper 实例
     * 配置为宽松模式，支持向前向后兼容
     */
    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // 反序列化配置 - 宽松处理
        // 忽略未知属性，避免新增字段导致反序列化失败
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 忽略空对象，避免空JSON对象导致反序列化失败
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        // 允许空字符串转换为null对象
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        // 允许单个值作为数组
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        // 允许数字作为字符串
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

        // 序列化配置 - 宽松处理
        // 不序列化null值，减少传输数据量
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 不序列化空集合和空字符串
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        // 禁用在遇到空Bean时抛出异常
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 禁用将日期序列化为时间戳
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 格式化输出（开发环境可启用，生产环境建议关闭）
        // mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        return mapper;
    }

}