package top.test.boot.cache.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 34746
 */
@Getter
@Setter
public class LoginRequestBody {
    private String phone;
    private String code;

}
