package io.github.cfgametech.luksdk;

import com.sun.net.httpserver.HttpServer;
import io.github.cfgametech.luksdk.apimodels.GetGameServiceListRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 本地 {@link HttpServer} 在读完请求体后通过 {@link CountDownLatch} 阻塞、不发送响应，
 * 使客户端在 {@link java.net.HttpURLConnection#getResponseCode()} 上触发读超时，
 * 校验读超时映射为 {@link LukSDKExceptions#RETRY_ERROR}（100002），且 {@link LukSDKException#getCause()} 为原始超时异常。
 */
class ApisTimeoutTest {

    private static final int READ_TIMEOUT_MS = 2_000;

    @Test
    void getGameServiceList_configReadTimeout_throwsTimeoutError() throws Exception {
        CountDownLatch releaseHandler = new CountDownLatch(1);
        HttpServer server = startHangingServer(releaseHandler);
        try {
            String base = "http://127.0.0.1:" + server.getAddress().getPort();
            LukSDK sdk = LukSDK.builder()
                    .config(Config.builder()
                            .appId(1L)
                            .appSecret("secret")
                            .domain(base)
                            .readTimeoutMillis(READ_TIMEOUT_MS)
                            .build())
                    .build();

            LukSDKException ex = assertThrows(LukSDKException.class,
                    () -> sdk.getApis().getGameServiceList(new GetGameServiceListRequest()));

            assertEquals(LukSDKExceptions.RETRY_ERROR.getCode(), ex.getCode());
            assertNotNull(ex.getCause());
            assertTrue(containsInCauseChain(ex, SocketTimeoutException.class),
                    "getCause() 链上应包含 SocketTimeoutException: " + ex);
        } finally {
            releaseHandler.countDown();
            server.stop(0);
        }
    }

    @Test
    void getGameServiceList_httpCallOptionsReadTimeout_throwsTimeoutError() throws Exception {
        CountDownLatch releaseHandler = new CountDownLatch(1);
        HttpServer server = startHangingServer(releaseHandler);
        try {
            String base = "http://127.0.0.1:" + server.getAddress().getPort();
            LukSDK sdk = LukSDK.builder()
                    .config(Config.builder()
                            .appId(1L)
                            .appSecret("secret")
                            .domain(base)
                            .readTimeoutMillis(60_000)
                            .build())
                    .build();

            HttpCallOptions options = HttpCallOptions.builder()
                    .readTimeoutMillis(READ_TIMEOUT_MS)
                    .build();

            LukSDKException ex = assertThrows(LukSDKException.class,
                    () -> sdk.getApis().getGameServiceList(new GetGameServiceListRequest(), options));

            assertEquals(LukSDKExceptions.RETRY_ERROR.getCode(), ex.getCode());
            assertNotNull(ex.getCause());
            assertTrue(containsInCauseChain(ex, SocketTimeoutException.class),
                    "getCause() 链上应包含 SocketTimeoutException: " + ex);
        } finally {
            releaseHandler.countDown();
            server.stop(0);
        }
    }

    private static HttpServer startHangingServer(CountDownLatch releaseHandler) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/sdk/get_game_service_list", exchange -> {
            try {
                drain(exchange.getRequestBody());
                releaseHandler.await(3, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException ignored) {
                // 客户端超时断开时读 body 可能失败
            } finally {
                try {
                    exchange.sendResponseHeaders(500, 0);
                    OutputStream os = exchange.getResponseBody();
                    os.close();
                } catch (IOException ignored) {
                    // 连接可能已关闭
                }
            }
        });
        server.start();
        return server;
    }

    private static void drain(InputStream in) throws IOException {
        byte[] buf = new byte[4096];
        while (in.read(buf) != -1) {
            // discard
        }
    }

    private static boolean containsInCauseChain(Throwable t, Class<? extends Throwable> type) {
        while (t != null) {
            if (type.isInstance(t)) {
                return true;
            }
            t = t.getCause();
        }
        return false;
    }
}
