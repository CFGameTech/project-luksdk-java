# 介绍
本项目为 Java 版本的 LukSDK，可直接引入使用，其中提供了需接入接口的通用实现，仅需结合业务逻辑将其返回即可。

> 仅需将 HTTP 请求转换为对应 Bean 后调用相关函数并填充返回值即可，关于参数的校验等行为交由 SDK 内部处理。

# Maven
可通过以下方式引入依赖

```xml
<dependency>
    <groupId>io.github.cfgametech</groupId>
    <artifactId>luksdk</artifactId>
    <version>0.0.1</version>
</dependency>
```

# 示例代码

```java
package org.example;

import io.github.cfgametech.Response;
import io.github.cfgametech.SDK;
import io.github.cfgametech.beans.GetChannelTokenRequest;
import io.github.cfgametech.beans.GetChannelTokenResponse;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // 初始化 SDK
        SDK sdk = new SDK("123465");

        // 来自 SDK 请求的参数结构
        GetChannelTokenRequest request = new GetChannelTokenRequest();

        request.setChannelId(1000);
        request.setUserId("userId");
        request.setTimestamp(167456789);
        request.setSign(sdk.generateSignature(request));

        // 处理请求
        Response<GetChannelTokenResponse> resp = sdk.getChannelToken(request, getChannelTokenRequest -> {
            // 业务逻辑
            GetChannelTokenResponse response = new GetChannelTokenResponse();

            // 生成 Token
            response.setToken("token");

            // 设置 Token 过期时间
            response.setExpirationTime(7200);

            return response;
        });

        // 将 resp 作为 JSON 写入 HTTP 响应
        System.out.println(resp.getCode());
        System.out.println(resp.getMessage());
        System.out.println(resp.getData().getToken());
    }
}
```