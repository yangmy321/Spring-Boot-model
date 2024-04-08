package top.test.boot.exception.service;

import org.springframework.stereotype.Service;
import top.test.boot.exception.enums.ErrorCode;
import top.test.boot.exception.exception.ServerException;

@Service
public class MyService {
    /**
     * 模拟未登录异常
     */
    public void unAuthorizedError() {
        throw new ServerException(ErrorCode. UNAUTHORIZED);}

    public void serverError() {
        throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
