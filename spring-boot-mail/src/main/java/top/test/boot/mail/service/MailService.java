package top.test.boot.mail.service;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailService {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEamil;

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEamil);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }
    public void sendHtmlMail(String to, String subject, String content)throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(fromEamil);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        javaMailSender.send(mimeMessage);

    }
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(fromEamil);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        mimeMessageHelper.addAttachment(fileName, file);
        javaMailSender.send(mimeMessage);
    }
}
