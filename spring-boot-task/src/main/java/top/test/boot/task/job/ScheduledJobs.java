package top.test.boot.task.job;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.test.boot.task.service.MailService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * @author 34746
 */
@Component
@Slf4j
public class ScheduledJobs {

    @Autowired
    private MailService mailService;

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelay() throws InterruptedException{
//        log.info("fixedDelay start:{}",new Date());
//        log.info("fixedDelay end:{}",new Date());
//    }
//
//    @Scheduled(fixedRate = 4000)
//    public void fixedRateJob() throws InterruptedException{
//        log.info("fixedRateJobs start:{}",new Date());
//        log.info("fixedRateJobs end:{}",new Date());
//    }

//    @Scheduled(cron = "0 13 16 6 5 * ")
//    public void cronJob() throws InterruptedException{
//        log.info("生日快乐 :{}",new Date());
//    }


//    @Scheduled(cron = "0/5 * * * * ? ")
//    public void fixedRateJob() throws InterruptedException{
//        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        QrCodeUtil.generate(time, 300, 300, FileUtil.file("D:\\ideaCode\\spring-boot-learning\\spring-boot-task\\src\\main\\resources\\picture\\"+ UUID.randomUUID() +".jpg"));
//    }



    @Scheduled(cron = "0 00 23 6 5  * ")
    public void cronJob() throws MessagingException {
        String content = """
                <body>
                    <div>
                        <h1>杨明月的测试邮件hello world</h1>
                   <p>这是仓库地址:https://github.com/yangmy321/Spring-Boot-model?tab=repositories</p>
                           </div>
                </body>
                </html>
                """;
        try{
            mailService.sendHtmlMail("16422802@qq.com","软件2114 14 ",content);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("发送成功");
    }

}
