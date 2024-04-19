package top.test.boot.work0415.controller;



import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import top.test.boot.work0415.entity.User;
import top.test.boot.work0415.result.Result;
import top.test.boot.work0415.service.UserService;
import top.test.boot.work0415.untils.OssTemplate;

import java.util.List;

/**
 * @author 34746
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private OssTemplate ossTemplate;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User u1 = userService.login(user);
        if(u1 == null){
            return Result.error("用户不存在");
        }
        return Result.success();
    }

    /**
     * 查询所有用户信息
     */
    @GetMapping("/getUsers")
    public Result<List<User>> getUsers(){
        List<User> u1 = userService.getUsers();
        return Result.success(u1);
    }


    /**
     * 查询用户详细信息
     */
    @GetMapping("/getUserById/{id}")
    public Result<User> getUserById(@PathVariable Integer id){
        User u1 = userService.getUserById(id);
        if(u1 == null){
            return Result.error("用户不存在");
        }
        return Result.success(u1);
    }


    /**
     * 修改用户基本信息
     */
    @PostMapping("/updateUserById")
    public Result<Integer> updateUserById(@RequestBody User user){
        int result = userService.updateUserById(user);
        if(result == 0){
            return Result.error("修改失败");
        }
        return Result.success();
    }


    /**
     * oss 文件上传修改用户头像
     * @param file 文件对象
     * @return 文件上传后的地址
     */
    @PostMapping("/oss/{id}")
    public Result<?> ossUpload(MultipartFile file, @PathVariable Integer id) {
        String img = ossTemplate.ossUpload(file);
        int result = userService.updateUserById(User.builder()
                .id(id)
                .img(img)
                .build());
        if(result == 0){
            return Result.error("修改失败");
        }
        return Result.success();
    }


}