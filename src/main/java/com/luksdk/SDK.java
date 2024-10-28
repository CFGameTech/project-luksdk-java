package com.luksdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.security.MessageDigest;
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
    public final Response<Beans.GetChannelTokenResponse> getChannelToken(Beans.GetChannelTokenRequest request, RequestHandler<Beans.GetChannelTokenRequest, Beans.GetChannelTokenResponse>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 刷新用户令牌过期时间
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<Beans.RefreshChannelTokenResponse> refreshChannelToken(Beans.RefreshChannelTokenRequest request, RequestHandler<Beans.RefreshChannelTokenRequest, Beans.RefreshChannelTokenResponse>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 获取渠道用户信息
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<Beans.GetChannelUserInfoResponse> getChannelUserInfo(Beans.GetChannelUserInfoRequest request, RequestHandler<Beans.GetChannelUserInfoRequest, Beans.GetChannelUserInfoResponse>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 向渠道下订单
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<List<Beans.CreateChannelOrderResponseEntry>> createChannelOrder(Beans.CreateChannelOrderRequest request, RequestHandler<Beans.CreateChannelOrderRequest, List<Beans.CreateChannelOrderResponseEntry>>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    /**
     * 下注开奖通知结果
     * @param request 请求参数结构体
     * @param successHandler 业务处理函数，返回值为业务处理结果
     * @return 可直接作为 JSON 响应的通用响应数据结构
     */
    @SafeVarargs
    public final Response<List<Beans.NotifyChannelOrderResponseEntry>> notifyChannelOrder(Beans.NotifyChannelOrderRequest request, RequestHandler<Beans.NotifyChannelOrderRequest, List<Beans.NotifyChannelOrderResponseEntry>>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }

    // NotifyGame 向渠道通知游戏状态
    @SafeVarargs
    public final Response<Beans.Empty> notifyGame(Beans.NotifyGameRequest request, RequestHandler<Beans.NotifyGameRequest, Beans.Empty>... successHandler) throws IllegalAccessException {
        return generateHandler(signSecret, request.getSign(), request, successHandler);
    }


    /**
     * @param <T> Request 请求报文结构
     * @param <R> Response 业务数据响应报文结构
     */
    public interface RequestHandler<T, R> {
        R handle(T request) throws Exception;
    }

    public static class Response<T> {
        private int code;
        private String msg;
        private T data;

        public Response() {}

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public Response<T> withError(Exception err, String... msg) {
            if (err == null) {
                return this;
            }
            if (err instanceof Exceptions.SDKError customError) {
                this.code = customError.getCode();
                this.msg = customError.getMessage();
            }else {
                this.msg = err.getMessage();
            }

            if (msg != null && msg.length > 0) {
                this.msg = String.join(", ", err.getMessage(), String.join(", ", msg));
            }
            if (this.code == 0) {
                this.code = -1;
            }
            return this;
        }

        public Response<T> withData(T data) {
            this.data = data;
            if (this.code == 0) {
                this.msg = "成功";
            }
            return this;
        }

        public boolean suc() {
            return code == 0;
        }

        public String getMessage() {
            return msg;
        }

        public T getData() {
            return data;
        }
    }

    protected static class SignUtils {
        // 签名方法
        protected static String signature(String signSecret, Object params) throws IllegalAccessException {
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

    public static class Exceptions {

        public static final SDKError ErrInvalidParams = regError(1000, "invalid params");          // 参数有误
        public static final SDKError ErrInvalidChannel = regError(1001, "invalid channel");        // 渠道有误
        public static final SDKError ErrInvalidChannelOrder = regError(1002, "invalid channel request"); // 渠道请求异常
        public static final SDKError ErrInvalidSignature = regError(1003, "invalid signature");    // 签名有误
        public static final SDKError ErrInvalidGame = regError(1004, "invalid game");              // 游戏有误
        public static final SDKError ErrChannelDataException = regError(1005, "channel data exception");  // 渠道返回数据异常
        public static final SDKError ErrRepeatOrder = regError(1006, "repeat order");              // 重复下订单
        public static final SDKError ErrOrderFailed = regError(1007, "order failed");              // 下单失败
        public static final SDKError ErrOrderNotExist = regError(1008, "order not exist");         // 订单不存在


        private static SDKError regError(int code, String msg) {
            return new SDKError(code, msg);
        }

        public static class SDKError extends Exception {
            private final int code;
            public SDKError(int code, String message) {
                super(message);
                this.code = code;
            }

            public int getCode() {
                return code;
            }

        }
    }

    public static class Beans {

        public static class NotifyTypes {
            public static final int NotifyTypeStartBefore = 1; // 游戏开始前状态
            public static final int NotifyTypeGaming = 2;      // 游戏开始中状态
            public static final int NotifyTypeEnd = 3;         // 游戏结束状态
        }

        public static class Actions {
            public static final int ActionJoinGame = 1;       // 加入游戏操作
            public static final int ActionExitGame = 2;       // 退出游戏操作
            public static final int ActionSettingGame = 3;    // 设置游戏操作
            public static final int ActionKickOut = 4;        // 踢人操作
            public static final int ActionStartGame = 5;      // 开始游戏操作
            public static final int ActionPrepare = 6;        // 准备操作
            public static final int ActionCancelPrepare = 7;  // 取消准备操作
            public static final int ActionGameEnd = 8;        // 游戏结束操作
        }

        public static class Empty {}

        public static class GetChannelTokenRequest {
            @JsonProperty("c_id")
            private int cId;

            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("code")
            private String code;

            @JsonProperty("timestamp")
            private long timestamp;

            @JsonProperty("sign")
            private String sign;

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }

            public int getChannelId() {
                return cId;
            }

            public void setChannelId(int cId) {
                this.cId = cId;
            }
        }

        public static class GetChannelTokenResponse {
            @JsonProperty("token")
            private String token;

            @JsonProperty("left_time")
            private long leftTime;

            public long getLeftTime() {
                return leftTime;
            }

            public void setLeftTime(long leftTime) {
                this.leftTime = leftTime;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }

        public static class RefreshChannelTokenRequest {
            @JsonProperty("c_id")
            private int cId;

            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("token")
            private String token;

            @JsonProperty("timestamp")
            private long timestamp;

            @JsonProperty("sign")
            private String sign;

            @JsonProperty("left_time")
            private long leftTime;

            public long getLeftTime() {
                return leftTime;
            }

            public void setLeftTime(long leftTime) {
                this.leftTime = leftTime;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }

            public int getChannelId() {
                return cId;
            }

            public void setChannelId(int cId) {
                this.cId = cId;
            }
        }

        public static class RefreshChannelTokenResponse {
            @JsonProperty("token")
            private String token;

            @JsonProperty("left_time")
            private long leftTime;

            public long getLeftTime() {
                return leftTime;
            }

            public void setLeftTime(long leftTime) {
                this.leftTime = leftTime;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }

        public static class GetChannelUserInfoRequest {
            @JsonProperty("c_id")
            private int cId;

            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("token")
            private String token;

            @JsonProperty("sign")
            private String sign;

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }

            public int getChannelId() {
                return cId;
            }

            public void setChannelId(int cId) {
                this.cId = cId;
            }
        }

        public static class GetChannelUserInfoResponse {
            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("name")
            private String name;

            @JsonProperty("avatar")
            private String avatar;

            @JsonProperty("coins")
            private long coins;

            public long getCoins() {
                return coins;
            }

            public void setCoins(long coins) {
                this.coins = coins;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }
        }

        public static class CreateChannelOrderRequest {
            @JsonProperty("sign")
            private String sign;

            @JsonProperty("data")
            private List<CreateChannelOrderRequestEntry> data;

            public List<CreateChannelOrderRequestEntry> getData() {
                return data;
            }

            public void setData(List<CreateChannelOrderRequestEntry> data) {
                this.data = data;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }

        public static class CreateChannelOrderRequestEntry {
            @JsonProperty("c_id")
            private int cId;

            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("c_room_id")
            private String cRoomId;

            @JsonProperty("g_id")
            private int gId;

            @JsonProperty("coins_cost")
            private long coinsCost;

            @JsonProperty("score_cost")
            private long scoreCost;

            @JsonProperty("game_order_id")
            private String gameOrderId;

            @JsonProperty("token")
            private String token;

            @JsonProperty("timestamp")
            private long timestamp;

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getGameOrderId() {
                return gameOrderId;
            }

            public void setGameOrderId(String gameOrderId) {
                this.gameOrderId = gameOrderId;
            }

            public long getScoreCost() {
                return scoreCost;
            }

            public void setScoreCost(long scoreCost) {
                this.scoreCost = scoreCost;
            }

            public long getCoinsCost() {
                return coinsCost;
            }

            public void setCoinsCost(long coinsCost) {
                this.coinsCost = coinsCost;
            }

            public int getGameId() {
                return gId;
            }

            public void setGameId(int gId) {
                this.gId = gId;
            }

            public String getcRoomId() {
                return cRoomId;
            }

            public void setcRoomId(String cRoomId) {
                this.cRoomId = cRoomId;
            }

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }

            public int getChannelId() {
                return cId;
            }

            public void setChannelId(int cId) {
                this.cId = cId;
            }
        }

        public static class CreateChannelOrderResponseEntry {
            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("order_id")
            private String orderId;

            @JsonProperty("coins")
            private long coins;

            @JsonProperty("status")
            private int status;

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public long getCoins() {
                return coins;
            }

            public void setCoins(long coins) {
                this.coins = coins;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class NotifyChannelOrderRequest {
            @JsonProperty("sign")
            private String sign;

            @JsonProperty("data")
            private List<NotifyChannelOrderRequestEntry> data;

            public List<NotifyChannelOrderRequestEntry> getData() {
                return data;
            }

            public void setData(List<NotifyChannelOrderRequestEntry> data) {
                this.data = data;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }

        public static class NotifyChannelOrderRequestEntry {
            @JsonProperty("c_id")
            private int cId;

            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("g_id")
            private int gId;

            @JsonProperty("game_order_id")
            private String gameOrderId;

            @JsonProperty("token")
            private String token;

            @JsonProperty("coins_cost")
            private long coinsCost;

            @JsonProperty("coins_award")
            private long coinsAward;

            @JsonProperty("score_cost")
            private long scoreCost;

            @JsonProperty("score_award")
            private long scoreAward;

            @JsonProperty("timestamp")
            private long timestamp;

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public long getScoreAward() {
                return scoreAward;
            }

            public void setScoreAward(long scoreAward) {
                this.scoreAward = scoreAward;
            }

            public long getScoreCost() {
                return scoreCost;
            }

            public void setScoreCost(long scoreCost) {
                this.scoreCost = scoreCost;
            }

            public long getCoinsAward() {
                return coinsAward;
            }

            public void setCoinsAward(long coinsAward) {
                this.coinsAward = coinsAward;
            }

            public long getCoinsCost() {
                return coinsCost;
            }

            public void setCoinsCost(long coinsCost) {
                this.coinsCost = coinsCost;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getGameOrderId() {
                return gameOrderId;
            }

            public void setGameOrderId(String gameOrderId) {
                this.gameOrderId = gameOrderId;
            }

            public int getGameId() {
                return gId;
            }

            public void setGameId(int gId) {
                this.gId = gId;
            }

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }

            public int getChannelId() {
                return cId;
            }

            public void setChannelId(int cId) {
                this.cId = cId;
            }
        }

        public static class NotifyChannelOrderResponseEntry {
            @JsonProperty("c_uid")
            private String cUid;

            @JsonProperty("order_id")
            private String orderId;

            @JsonProperty("coins")
            private long coins;

            @JsonProperty("score")
            private long score;

            public long getScore() {
                return score;
            }

            public void setScore(long score) {
                this.score = score;
            }

            public long getCoins() {
                return coins;
            }

            public void setCoins(long coins) {
                this.coins = coins;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getUserId() {
                return cUid;
            }

            public void setUserId(String cUid) {
                this.cUid = cUid;
            }
        }

        public static class NotifyGameRequest {
            @JsonProperty("c_id")
            private int cId;

            @JsonProperty("g_id")
            private int gId;

            @JsonProperty("notify_type")
            private int notifyType;

            @JsonProperty("ext")
            private String ext;

            @JsonProperty("data")
            private String data;

            @JsonProperty("timestamp")
            private long timestamp;

            @JsonProperty("sign")
            private String sign;


            public NotifyGameRequestStartBefore getStartBefore() throws JsonProcessingException {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(this.data, NotifyGameRequestStartBefore.class);
            }

            public NotifyGameRequestGaming getGaming() throws JsonProcessingException {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(this.data, NotifyGameRequestGaming.class);
            }

            public NotifyGameRequestEnd getEnd() throws JsonProcessingException {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(this.data, NotifyGameRequestEnd.class);
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }

            public int getNotifyType() {
                return notifyType;
            }

            public void setNotifyType(int notifyType) {
                this.notifyType = notifyType;
            }

            public int getGameId() {
                return gId;
            }

            public void setGameId(int gId) {
                this.gId = gId;
            }

            public int getChannelId() {
                return cId;
            }

            public void setChannelId(int cId) {
                this.cId = cId;
            }
        }

        public static class NotifyGameRequestStartBefore {
            @JsonProperty("room_id")
            private int roomId;

            @JsonProperty("round_id")
            private int roundId;

            @JsonProperty("player_ready_status")
            private Map<String, Boolean> playerReadyStatus;

            @JsonProperty("notify_action")
            private int notifyAction;

            @JsonProperty("game_setting")
            private String gameSetting;

            public String getGameSetting() {
                return gameSetting;
            }

            public void setGameSetting(String gameSetting) {
                this.gameSetting = gameSetting;
            }

            public int getNotifyAction() {
                return notifyAction;
            }

            public void setNotifyAction(int notifyAction) {
                this.notifyAction = notifyAction;
            }

            public Map<String, Boolean> getPlayerReadyStatus() {
                return playerReadyStatus;
            }

            public void setPlayerReadyStatus(Map<String, Boolean> playerReadyStatus) {
                this.playerReadyStatus = playerReadyStatus;
            }

            public int getRoundId() {
                return roundId;
            }

            public void setRoundId(int roundId) {
                this.roundId = roundId;
            }

            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
            }

        }

        public static class NotifyGameRequestGaming {
            @JsonProperty("room_id")
            private int roomId;

            @JsonProperty("round_id")
            private int roundId;

            @JsonProperty("player_num")
            private int playerNum;

            @JsonProperty("player_uids")
            private List<String> playerUids;

            @JsonProperty("notify_action")
            private int notifyAction;

            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
            }

            public int getRoundId() {
                return roundId;
            }

            public void setRoundId(int roundId) {
                this.roundId = roundId;
            }

            public int getPlayerNum() {
                return playerNum;
            }

            public void setPlayerNum(int playerNum) {
                this.playerNum = playerNum;
            }

            public List<String> getPlayerUserIds() {
                return playerUids;
            }

            public void setPlayerUserIds(List<String> playerUids) {
                this.playerUids = playerUids;
            }

            public int getNotifyAction() {
                return notifyAction;
            }

            public void setNotifyAction(int notifyAction) {
                this.notifyAction = notifyAction;
            }

        }

        public static class NotifyGameRequestEnd {
            @JsonProperty("room_id")
            private int roomId;

            @JsonProperty("round_id")
            private int roundId;

            @JsonProperty("rank")
            private List<String> rank;

            @JsonProperty("is_force_end")
            private boolean isForceEnd;

            @JsonProperty("notify_action")
            private int notifyAction;


            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
            }

            public int getRoundId() {
                return roundId;
            }

            public void setRoundId(int roundId) {
                this.roundId = roundId;
            }

            public List<String> getRank() {
                return rank;
            }

            public void setRank(List<String> rank) {
                this.rank = rank;
            }

            public boolean isForceEnd() {
                return isForceEnd;
            }

            public void setForceEnd(boolean forceEnd) {
                isForceEnd = forceEnd;
            }

            public int getNotifyAction() {
                return notifyAction;
            }

            public void setNotifyAction(int notifyAction) {
                this.notifyAction = notifyAction;
            }
        }
    }
}
