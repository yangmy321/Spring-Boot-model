package top.test.boot.cache.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.test.boot.cache.result.Result;
import top.test.boot.cache.service.AuthService;
import top.test.boot.cache.service.SmsService;

/**
 * @author 34746
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private  AuthService authService;


    @PostMapping("/login")
    public Result<Object> sendCode(@RequestBody LoginRequestBody requestBody) {
        String phone = requestBody.getPhone();
        String code = requestBody.getCode();
            authService.LoginByPhone(phone,code);
            return Result.ok("Verification code sent successfully");

    }

}
