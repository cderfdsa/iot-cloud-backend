package iot.cloud.backend.service.modules.mqtt;

/**
 * @author weichuang
 */
public interface MqttSendService {

    void sendToDevice(String deviceCode, String message);

    void sendToAccountDevice(String account, String deviceCode, String message);

    void sendToAccountOnline(String account, String message);

    void sendToAccountAlarm(String account, String message);
}
