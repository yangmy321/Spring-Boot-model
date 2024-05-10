package top.test.boot.fi.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.test.boot.fi.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import top.test.boot.fi.util.OssTemplate;

/**
 * @author 34746
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/info")
    public String getUserInfo(HttpServletResponse response) {
        String userId = response.getHeader("userId");
        String nickname = response.getHeader("nickname");
        return "User ID: " + userId + ", Nickname: " + nickname;
    }

    /**
     * oss 文件上传
     * @param file 文件对象
     * @return 文件上传后的地址
     */
    @PostMapping("/oss")
    public Result ossUpload(MultipartFile file) {
        return Result.ok();
    }

    @Autowired
    private OssTemplate ossTemplate;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 调用您的上传方法
                String ossUrl = ossTemplate.ossUpload(file);
                return "File uploaded successfully. OSS URL: " + ossUrl;
            } catch (Exception e) {
                return "Failed to upload file: " + e.getMessage();
            }
        } else {
            return "Failed to upload file: File is empty.";
        }
    }
}
