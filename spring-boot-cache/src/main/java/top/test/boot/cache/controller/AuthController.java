package top.test.boot.cache.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.test.boot.cache.result.Result;
import top.test.boot.cache.service.AuthService;

import java.util.concurrent.TimeUnit;

/**
 * @author 34746
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate; // 注入Redis模板

    @PostMapping("/login")
    public Result<Object> sendCode(@RequestBody LoginRequestBody requestBody) {
        String phone = requestBody.getPhone();

        // 检查Redis中是否存在该手机号的发送记录
        if (redisTemplate.opsForValue().get(phone) != null) {
            return Result.error("Verification code sent too frequently. Please wait 60 seconds.");
        }

        String code = requestBody.getCode();
        authService.LoginByPhone(phone, code);

        // 将发送记录存入Redis，并设置过期时间为60秒
        redisTemplate.opsForValue().set(phone, "sent", 60, TimeUnit.SECONDS);

        return Result.ok("Verification code sent successfully");
    }
}
