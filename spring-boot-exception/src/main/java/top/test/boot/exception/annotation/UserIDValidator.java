package top.test.boot.exception.annotation;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UserIDValidator implements ConstraintValidator<UserID, String> {

    //身份证号正则表达式
    private static final String REGEX_USERID = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}([0-9Xx])$";

    @Override
    public void initialize(UserID userID) {

    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)){
            // 忽略空值,由@NotBlank注解校验
            return true;
        }
        return Pattern.matches(REGEX_USERID,value);
    }
}
