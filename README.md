# LukSDK for Java

本项目为 Java 版本的 LukSDK，提供了接入 LukSDK 平台所需的接口封装和数据结构定义。

**注意：自动处理签名已不再推荐使用，目前倾向于回调函数仅定义结构体，提供签名函数由开发者执行决定。**

## 主要功能

- **API 接口封装**: 提供标准化的 API 调用接口
- **控制事件构建器**: 简化控制事件的创建和发布
- **回调数据结构**: 定义回调接口的请求和响应结构体
- **类型安全**: 使用 Jackson 进行 JSON 序列化，提供完整的类型定义

## Maven 依赖

```xml
<dependency>
    <groupId>io.github.cfgametech</groupId>
    <artifactId>luksdk</artifactId>
</dependency>
```

## 示例代码

参考示例文件：[SDK 初始化和 API 调用示例](./src/main/java/io/github/cfgametech/luksdk/Example.java)