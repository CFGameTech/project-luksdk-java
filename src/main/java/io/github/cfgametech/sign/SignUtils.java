package io.github.cfgametech.sign;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignUtils {
    // 签名方法
    public static String signature(String signSecret, Object params) throws IllegalAccessException {
        Map<String, String> paramsMap = mapToStringMap(objectToMap(params));
        return generateSignature(signSecret, paramsMap);
    }

    // 生成签名
    private static String generateSignature(String signSecret, Map<String, String> params) {
        // 排序参数键
        SortedMap<String, String> sortedParams = new TreeMap<>(params);

        // 构建签名字符串
        StringBuilder signatureBuilder = new StringBuilder();
        boolean hasSuffix = false;
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null && !value.isEmpty()) {
                signatureBuilder.append(key).append("=").append(value).append("&");
                hasSuffix = true;
            }
        }

        // 删除末尾的 '&' 并拼接密钥
        if (hasSuffix) {
            signatureBuilder.deleteCharAt(signatureBuilder.length() - 1);
        }
        signatureBuilder.append("&key=").append(signSecret);

        String signatureString = signatureBuilder.toString();

        // 生成 MD5 哈希并转换为大写
        return md5Hash(signatureString).toUpperCase();
    }

    // MD5 哈希函数
    private static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    private static Map<String, String> mapToStringMap(Map<String, Object> map) {
        Map<String, String> stringMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            switch (entry.getValue()) {
                case String value -> {if (value.isEmpty() || entry.getKey().equals("sign")) {continue;}}
                case Number value -> {if (value.doubleValue() == 0) {continue;}}
                case Boolean value -> {if (!value) {continue;}}
                case null -> {continue;}
                default -> {}
            }

            stringMap.put(entry.getKey(), entry.getValue().toString());
        }
        return stringMap;
    }
}