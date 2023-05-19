package iot.cloud.backend.service.modules.email.impl;

import iot.cloud.backend.common.utils.RandomStringUtils;
import iot.cloud.backend.service.modules.email.EmailSendService;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author weichuang
 */
public class EmailSendServiceImpl implements EmailSendService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public String sendValidateCode(String email) {
        String code = RandomStringUtils.randomNumeric(6);
        String subject = "验证码";
        String text = "您的验证码为 " + code;
        sendEmail(email, subject, text);
        return code;
    }

    private void sendEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
