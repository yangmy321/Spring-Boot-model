package top.test.boot.cache.config;

public class RedisKeys {
    /**
     * 验证码 Key
     */
    public static String getSmsKey(String phone) {
        return "sms:captcha:" + phone;
    }
}
