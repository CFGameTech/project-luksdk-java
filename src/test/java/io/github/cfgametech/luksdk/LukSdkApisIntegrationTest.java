package io.github.cfgametech.luksdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.cfgametech.luksdk.apimodels.GetGameServiceListRequest;
import io.github.cfgametech.luksdk.apimodels.GetGameServiceListResponse;
import io.github.cfgametech.luksdk.apimodels.PublishControlEventRequest;
import io.github.cfgametech.luksdk.apimodels.PublishControlEventResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LukSdkApisIntegrationTest {

    private static final String DEFAULT_GAME_ID = "102";
    private static final String DEFAULT_CONTROL_TYPE = "1001";
    private static final String DEFAULT_CONTROL_USER_ID = "1355432380151733890";

    private LukSDK lukSDK;

    @BeforeEach
    void setUp() {
        Assumptions.assumeTrue(isEnvConfigured(),
                "跳过：请设置环境变量 TEST_APP_ID、TEST_APP_SECRET、TEST_DOMAIN");
        long appId = parseLongEnv("TEST_APP_ID");
        lukSDK = LukSDK.builder()
                .config(Config.builder()
                        .appId(appId)
                        .appSecret(requiredEnv("TEST_APP_SECRET"))
                        .domain(normalizeDomain(requiredEnv("TEST_DOMAIN")))
                        .build())
                .build();
    }

    @Test
    void getGameServiceList_returnsParsedResponse() throws LukSDKException, JsonProcessingException {
        GetGameServiceListResponse response = lukSDK.getApis().getGameServiceList(new GetGameServiceListRequest());
        assertNotNull(response);
        assertNotNull(response.getCode());
    }

    @Test
    void getGameServiceList_withHttpCallOptions_usesOverload() throws LukSDKException, JsonProcessingException {
        HttpCallOptions options = HttpCallOptions.builder()
                .connectTimeoutMillis(15_000)
                .readTimeoutMillis(60_000)
                .build();
        GetGameServiceListResponse response = lukSDK.getApis()
                .getGameServiceList(new GetGameServiceListRequest(), options);
        assertNotNull(response);
        assertNotNull(response.getCode());
    }

    @Test
    void publishControlEvent_fetchBagStatus_likeExample() throws LukSDKException, JsonProcessingException {
        long appId = parseLongEnv("TEST_APP_ID");
        long gameId = Long.parseLong(envOrDefault("TEST_GAME_ID", DEFAULT_GAME_ID).trim());
        int type = Integer.parseInt(envOrDefault("TEST_CONTROL_TYPE", DEFAULT_CONTROL_TYPE).trim());
        String userId = envOrDefault("TEST_CONTROL_USER_ID", DEFAULT_CONTROL_USER_ID);

        String dataJson = JacksonConfig.createObjectMapper().writeValueAsString(
                PublishControlEventRequest.FetchBagStatus.builder().userId(userId).build());

        PublishControlEventRequest request = PublishControlEventRequest.builder()
                .appId(appId)
                .gameId(gameId)
                .type(type)
                .data(dataJson)
                .build();

        PublishControlEventResponse<PublishControlEventResponse.FetchBagStatusResponse> response = lukSDK.getApis()
                .publishControlEvent(request, PublishControlEventResponse.FetchBagStatusResponse.class);

        assertNotNull(response);
        assertNotNull(response.getCode());
    }

    private static boolean isEnvConfigured() {
        return notBlank(System.getenv("TEST_APP_ID"))
                && notBlank(System.getenv("TEST_APP_SECRET"))
                && notBlank(System.getenv("TEST_DOMAIN"));
    }

    private static String requiredEnv(String name) {
        String v = System.getenv(name);
        if (v == null || v.trim().isEmpty()) {
            throw new IllegalStateException("Missing env: " + name);
        }
        return v.trim();
    }

    private static long parseLongEnv(String name) {
        return Long.parseLong(requiredEnv(name));
    }

    private static String envOrDefault(String name, String defaultValue) {
        String v = System.getenv(name);
        return notBlank(v) ? v.trim() : defaultValue;
    }

    private static boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private static String normalizeDomain(String domain) {
        String d = domain.trim();
        while (d.endsWith("/")) {
            d = d.substring(0, d.length() - 1);
        }
        return d;
    }
}
