package top.test.boot.exception.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import top.test.boot.exception.annotation.Phone;
import top.test.boot.exception.annotation.UserID;

@Data
public class User {
    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    private String password;

    @Max(value=100,message="年龄不能超过100岁")
    @Min(value=10,message="年龄不能超过10岁")
    private int age;

    @Phone
    private String phone;

    @UserID
    private String userID;

}
