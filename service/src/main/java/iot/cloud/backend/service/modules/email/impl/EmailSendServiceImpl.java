package iot.cloud.backend.service.modules.email.impl;

import iot.cloud.backend.common.utils.RandomStringUtils;
import iot.cloud.backend.service.dto.ResDtoValidateCode;
import iot.cloud.backend.service.modules.email.EmailSendService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.service.result.ResultCodeCommon;
import jakarta.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
public class EmailSendServiceImpl implements EmailSendService {

    @Resource
    private JavaMailSender javaMailSender;
    @Resource(name = "cacheManagerForLoginOrRegister")
    private CacheManager cacheManagerForLoginOrRegister;

    @Override
    public ResResult<ResDtoValidateCode> sendValidateCodeForLoginOrRegister(String email) {
        String code = RandomStringUtils.randomNumeric(6);
        //
        Cache cache = cacheManagerForLoginOrRegister.getCache("vcForLoginOrRegister");
        if (cache.get(email, String.class) != null) {
            return ResultCodeCommon.FREQUENCY_TOO_HIGH;
        }
        //
        String subject = "验证码";
        String text = "您的【登录/注册】验证码为 " + code;
        sendEmail(email, subject, text);
        //
        cache.put(email, code);
        //
        return new ResResult<>(new ResDtoValidateCode(email));
    }

    private void sendEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cniiot@163.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
