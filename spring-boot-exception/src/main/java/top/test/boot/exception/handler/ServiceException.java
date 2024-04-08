package top.test.boot.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.test.boot.exception.exception.ServerException;
import top.test.boot.exception.result.Result;

@Slf4j
@RestControllerAdvice
public class ServiceException {
    @ExceptionHandler(value = ServerException.class)
    public Result<?> handleServerException(ServerException e) {
        return Result.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<?> handleException(Exception e) {
        return Result.error(e.getMessage());
    }
}
