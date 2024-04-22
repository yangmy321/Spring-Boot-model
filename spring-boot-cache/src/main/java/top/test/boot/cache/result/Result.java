package top.test.boot.cache.result;

import lombok.Getter;
import lombok.Setter;
import top.test.boot.cache.exception.ErrorCode;

@Getter
@Setter
public class Result<T> {
    private int code=0;
    private String msg="success";
    private T data;

    public static <T> top.test.boot.cache.result.Result<T> ok() {
        return new top.test.boot.cache.result.Result<>();
    }

    public static <T> top.test.boot.cache.result.Result<T> ok(T data) {
        top.test.boot.cache.result.Result<T> rs = new top.test.boot.cache.result.Result<>();
        rs.setData(data);
        return rs;
    }

    public static <T> top.test.boot.cache.result.Result<T> error() {
        top.test.boot.cache.result.Result<T> rs = new top.test.boot.cache.result.Result<>();
        rs.setMsg(ErrorCode.UNAUTHORIZED.getMsg());
        return rs;
    }

    public static <T> top.test.boot.cache.result.Result<T> error(String msg) {
        top.test.boot.cache.result.Result<T> rs = new top.test.boot.cache.result.Result<>();
        rs.setMsg(msg);
        return rs;
    }

    public static <T> top.test.boot.exception.result.Result<T> error(int code, String msg) {
        top.test.boot.exception.result.Result<T> rs = new top.test.boot.exception.result.Result<>();
        rs.setCode(code);
        rs.setMsg(msg);
        return rs;
    }
}
