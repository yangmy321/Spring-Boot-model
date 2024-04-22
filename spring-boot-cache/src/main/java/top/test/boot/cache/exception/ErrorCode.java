package top.test.boot.cache.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401,"登陆失效，请重新登陆"),
    INTERNAL_SERVER_ERROR(500,"服务器异常,请稍后再试"),
    UNKNOW_ERROR( 999, "未知错误"),

    CODE_SEND_FAIL(3001,"短信发送失败"),
    SMS_CODE_ERROR(3002,"短信验证码错误"),
    SMS_CODE_EXPIRE(3003,"验证码已失效"),
    PARAMS_ERROR(3004,"参数异常");

    private final int code;
    private final String msg;
}
