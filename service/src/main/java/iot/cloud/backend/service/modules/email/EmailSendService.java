package iot.cloud.backend.service.modules.email;

/**
 * @author weichuang
 */
public interface EmailSendService {
    String sendValidateCode(String email);
}
