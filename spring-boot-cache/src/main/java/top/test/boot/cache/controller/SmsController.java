package top.test.boot.cache.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.test.boot.cache.result.Result;
import top.test.boot.cache.service.SmsService;


/**
 * @author 34746
 */
@RestController
@RequestMapping("/sms")
@AllArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/send")
    public Result<Object> sendSms(@RequestParam("phone") String phone) {
        smsService.sendSms(phone);
        return Result.ok();
    }

}
