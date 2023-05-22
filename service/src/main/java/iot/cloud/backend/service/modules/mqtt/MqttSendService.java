package iot.cloud.backend.service.modules.mqtt;

import org.springframework.messaging.Message;

/**
 * @author weichuang
 */
public interface MqttSendService {

    void sendToDevice(String deviceCode, Message<byte[]> message);

    void sendToAccountDevice(String account, String deviceCode, Message<byte[]> message);

    void sendToAccountOnline(String account, Message<byte[]> message);

    void sendToAccountAlarm(String account, Message<byte[]> message);
}
