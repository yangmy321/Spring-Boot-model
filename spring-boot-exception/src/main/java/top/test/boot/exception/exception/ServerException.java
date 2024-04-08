package top.test.boot.exception.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import top.test.boot.exception.enums.ErrorCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends RuntimeException{
    private final int code;
    private final String msg;

    public ServerException(String msg){
        this.code = ErrorCode. INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }


}
