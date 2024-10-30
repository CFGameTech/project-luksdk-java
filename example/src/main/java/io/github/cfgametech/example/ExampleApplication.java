package io.github.cfgametech.example;

import io.github.cfgametech.Response;
import io.github.cfgametech.SDK;
import io.github.cfgametech.beans.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class ExampleApplication {
    private static final Logger logger = LoggerFactory.getLogger(ExampleApplication.class);
    private SDK sdk;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @PostConstruct
    public void init() {
        sdk = new SDK("fa7ad21fdbe10218024f88538a86");
    }

    @RestController
    @RequestMapping("/sdk")
    public class SdkController {

        @PostMapping("/get_channel_token")
        public Response<GetChannelTokenResponse> getChannelToken(@RequestBody GetChannelTokenRequest request) {
            Response<GetChannelTokenResponse> response = sdk.getChannelToken(request, req -> new GetChannelTokenResponse.Builder().
                    setToken("my-token").
                    setLeftTime(7200).
                    build());
            logger.info("get_channel_token - request: {}, response: {}", request, response);
            return response;
        }

        @PostMapping("/refresh_channel_token")
        public Response<RefreshChannelTokenResponse> refreshChannelToken(@RequestBody RefreshChannelTokenRequest request) {
            Response<RefreshChannelTokenResponse> response = sdk.refreshChannelToken(request, req -> new RefreshChannelTokenResponse.Builder().
                    setToken("my-token").
                    setLeftTime(7200).
                    build());
            logger.info("refresh_channel_token - request: {}, response: {}", request, response);
            return response;
        }

        @PostMapping("/get_channel_user_info")
        public Response<GetChannelUserInfoResponse> getChannelUserInfo(@RequestBody GetChannelUserInfoRequest request) {
            Response<GetChannelUserInfoResponse> response = sdk.getChannelUserInfo(request, req -> new GetChannelUserInfoResponse.Builder().
                    setUserId(req.getUserId()).
                    setName("my-name").
                    setCoins(100000).
                    build());
            logger.info("get_channel_user_info - request: {}, response: {}", request, response);
            return response;
        }

        @PostMapping("/create_channel_order")
        public Response<List<CreateChannelOrderResponseEntry>> createChannelOrder(@RequestBody CreateChannelOrderRequest request) {
            Response<List<CreateChannelOrderResponseEntry>> response = sdk.createChannelOrder(request, req -> req.getData().stream().map(datum -> new CreateChannelOrderResponseEntry.Builder().
                    setUserId(datum.getUserId()).
                    setOrderId(datum.getGameOrderId()).
                    setCoins(1000000).
                    setStatus(CreateChannelOrderResponseEntry.STATUS_SUCCESS).
                    build()).toList());
            logger.info("create_channel_order - request: {}, response: {}", request, response);
            return response;
        }

        @PostMapping("/notify_channel_order")
        public Response<List<NotifyChannelOrderResponseEntry>> notifyChannelOrder(@RequestBody NotifyChannelOrderRequest request) {
            Response<List<NotifyChannelOrderResponseEntry>> response = sdk.notifyChannelOrder(request, req -> req.getData().stream().map(datum -> new NotifyChannelOrderResponseEntry.Builder().
                    setUserId(datum.getUserId()).
                    setOrderId(datum.getGameOrderId()).
                    setCoins(1000000).
                    setScore(100000).
                    build()).toList());
            logger.info("notify_channel_order - request: {}, response: {}", request.toString(), response.toString());
            return response;
        }

        @PostMapping("/notify_game")
        public Response<Empty> notifyGame(@RequestBody NotifyGameRequest request) {
            logger.info("notify_game - request: {}", request);
            return sdk.notifyGame(request, req -> new Empty());
        }
    }
}
