package iot.cloud.backend.service.modules.mqtt.impl;

import iot.cloud.backend.service.modules.mqtt.MqttMessagingGateway;
import iot.cloud.backend.service.modules.mqtt.MqttSendService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Service
@Lazy
public class MqttSendServiceImpl implements MqttSendService {

    @Resource
    private MqttMessagingGateway mqttMessagingGateway;


    @Override
    public void sendToDevice(String deviceCode, Message<byte[]> message) {
        mqttMessagingGateway.send("/device/" + deviceCode + "/attributes/d", message);
    }

    @Override
    public void sendToAccountDevice(String account, String deviceCode, Message<byte[]> message) {
        mqttMessagingGateway.send("/account/" + account + "/" + deviceCode + "/attributes/d", message);
    }

    @Override
    public void sendToAccountOnline(String account, Message<byte[]> message) {
        mqttMessagingGateway.send("/account/" + account + "/online/d", message);
    }

    @Override
    public void sendToAccountAlarm(String account, Message<byte[]> message) {
        mqttMessagingGateway.send("/account/" + account + "/alarm/d", message);
    }
}
