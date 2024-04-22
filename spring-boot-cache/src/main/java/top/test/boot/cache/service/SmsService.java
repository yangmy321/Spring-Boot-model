package top.test.boot.cache.service;

/**
 * @author 34746
 */
public interface SmsService {

    /**
     * 发送短信
     *
     * @param phone 手机号
     */
    void sendSms(String phone);
}