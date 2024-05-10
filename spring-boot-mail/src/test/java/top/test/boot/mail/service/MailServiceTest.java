package top.test.boot.mail.service;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MailServiceTest {
    @Resource
    private MailService mailService;

    @Test
    void sendSimpleMail() {
        mailService.sendSimpleMail("3474640164@qq.com","ymy","ymy");
    }
    @Test
    void sendHtmlMail() throws MessagingException {
        String content = """
            <body>
            <h2>ymy</h2>
            <img src="https://mail.qq.com/cgi-bin/getqqicon?sid=o4eEj0IvhpykygVj&uin=-3638965389&mode=newaddr&mailaddr=3474640164%40qq.com"/>
            <a href="http://www.baidu.com" >百度</a>
            </body>
            """;
        mailService.sendHtmlMail("3474640164@qq.com","html",content);
    }
    @Test
    void sendAttachmentsMail() throws MessagingException {
        String filePath = "D:\\YMY\\后端作业\\image\\test.jpg";
        mailService.sendAttachmentsMail("3474640164@qq.com","ymy","ymy",filePath);
    }
}