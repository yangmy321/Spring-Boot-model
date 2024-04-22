package top.test.boot.cache.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.test.boot.cache.config.RedisKeys;
import top.test.boot.cache.config.RedisCache;
import top.test.boot.cache.exception.ErrorCode;
import top.test.boot.cache.exception.ServerException;
import top.test.boot.cache.service.AuthService;

import java.util.UUID;

/**
 * @author 34746
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Resource
    private RedisCache redisCache;

    @Override
    public String LoginByPhone(String phone, String code) {
        // 获取验证码cacheKey
        String smsCacheKey = RedisKeys.getSmsKey(phone);
        //从redis中获取验证码
        Integer redisCode = (Integer) redisCache.get(smsCacheKey);
        // 校验验证码合法性
        if(ObjectUtils.isEmpty(redisCode)) {
            throw new ServerException(ErrorCode.SMS_CODE_EXPIRE);
        }
        else if (!redisCode.toString().equals(code)) {
            //验证码错误
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        //删除用过的验证码
        redisCache.delete(smsCacheKey);
        //返回 token,这里用 UUID 模拟,实际上是要根据用户id,构造一个 JWT Token
        return UUID. randomUUID().toString();
    }
}
