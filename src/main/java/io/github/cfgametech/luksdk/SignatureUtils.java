package io.github.cfgametech.luksdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 签名工具类
 */
public class SignatureUtils {
    // 使用宽松配置的ObjectMapper，支持向前向后兼容
    private static final ObjectMapper objectMapper = JacksonConfig.createObjectMapper();

    /**
     * 生成签名
     */
    public static String signature(String secret, Object params) {
        Map<String, String> signatureParams = castToSignatureParams(params);
        return generateSignature(secret, signatureParams);
    }

    /**
     * 生成 MD5 签名
     */
    private static String generateSignature(String secret, Map<String, String> params) {
        // 按键排序
        TreeMap<String, String> sortedParams = new TreeMap<>(params);

        // 构建签名字符串，过滤空值
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        // 拼接 &key=secret
        sb.append("key=").append(secret);

        String signatureString = sb.toString();

        // 计算 MD5 并转大写
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(signatureString.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }

    /**
     * 将对象转换为签名参数
     */
    private static Map<String, String> castToSignatureParams(Object obj) {
        Map<String, String> result = new HashMap<>();
        
        try {
            JsonNode jsonNode = objectMapper.valueToTree(obj);
            flattenJsonNode("", jsonNode, result);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to signature params", e);
        }
        
        // 移除 sign 字段
        result.remove("sign");
        
        return result;
    }

    /**
     * 递归展平 JSON 节点
     */
    private static void flattenJsonNode(String prefix, JsonNode node, Map<String, String> result) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String key = prefix.isEmpty() ? field.getKey() : prefix + "." + field.getKey();
                flattenJsonNode(key, field.getValue(), result);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                String key = prefix + "[" + i + "]";
                flattenJsonNode(key, node.get(i), result);
            }
        } else if (!node.isNull()) {
            result.put(prefix, node.asText());
        }
    }
}