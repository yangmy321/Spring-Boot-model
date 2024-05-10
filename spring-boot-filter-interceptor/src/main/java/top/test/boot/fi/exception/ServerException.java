package top.test.boot.fi.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.test.boot.fi.enums.ErrorCode;


/**
 * @author 34746
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends RuntimeException{
    private final int code;
    private final String msg;

    public ServerException(String msg){
        super(msg);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }


}
