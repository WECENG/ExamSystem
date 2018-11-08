package cn.gdou.mail;

import cn.gdou.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class SendMailService {
    public static final Logger log=LoggerFactory.getLogger(SendMailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendSimpleEmail(String destination,Message message){
        SimpleMailMessage mailMessage =new SimpleMailMessage();
        mailMessage.setFrom(env.getProperty("spring.mail.username"));
        mailMessage.setTo(destination);
        mailMessage.setSubject("ExamSystem Message");
        mailMessage.setText(message.getDate()+": "+message.getContent());

        mailSender.send(mailMessage);
        log.info("邮件已发送");
    }
}
