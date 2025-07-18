package io.github.cfgametech.luksdk;

/**
 * LukSDK 枚举定义
 */
public class Enums {
    
    /**
     * 身份枚举
     */
    public static class Identity {
        public static final Integer OWNER = 1;   // 房主
        public static final Integer ADMIN = 2;   // 管理员
        public static final Integer NORMAL = 3;  // 普通用户
    }
    
    /**
     * 控制事件类型
     */
    public static class ControlEventType {
        public static final Integer JOIN_GAME = 1;
        public static final Integer LEAVE_GAME = 2;
        public static final Integer START_GAME = 3;
        public static final Integer END_GAME = 4;
        public static final Integer PAUSE_GAME = 5;
        public static final Integer RESUME_GAME = 6;
        public static final Integer USER_ITEM_GRANT = 7;
        public static final Integer USER_ITEM_CONSUME = 8;
        public static final Integer USER_COINS_GRANT = 9;
        public static final Integer USER_COINS_CONSUME = 10;
        public static final Integer USER_SCORE_GRANT = 11;
        public static final Integer USER_SCORE_CONSUME = 12;
        public static final Integer USER_LEVEL_UP = 13;
        public static final Integer USER_ACHIEVEMENT_UNLOCK = 14;
        public static final Integer ROOM_CREATE = 15;
        public static final Integer ROOM_DESTROY = 16;
        public static final Integer ROOM_JOIN = 17;
        public static final Integer ROOM_LEAVE = 18;
        public static final Integer ROOM_KICK = 19;
        public static final Integer ROOM_SETTING_CHANGE = 20;
        public static final Integer GAME_SETTING_CHANGE = 21;
        public static final Integer USER_STATUS_CHANGE = 22;
        public static final Integer SYSTEM_ANNOUNCEMENT = 23;
        public static final Integer CUSTOM_EVENT = 24;
    }
}