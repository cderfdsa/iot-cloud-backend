package iot.cloud.backend.service.modules.mqtt.impl;

import iot.cloud.backend.service.modules.mqtt.MqttMessagingGateway;
import iot.cloud.backend.service.modules.mqtt.MqttSendService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Lazy
@Service
@Slf4j
public class MqttSendServiceImpl implements MqttSendService {

    @Resource
    private MqttMessagingGateway mqttMessagingGateway;


    @Override
    public void sendToDevice(String deviceCode, String message) {
        mqttMessagingGateway.send("/device/" + deviceCode + "/attributes/d", message);
    }

    @Override
    public void sendToAccountDevice(String account, String deviceCode, String payload) {
        String topic = "/account/" + account + "/" + deviceCode + "/attributes/d";
        log.debug("topic = {} , payload = {}", topic, payload);
        mqttMessagingGateway.send(topic, payload);
    }

    @Override
    public void sendToAccountOnline(String account, String payload) {
        String topic = "/account/" + account + "/online/d";
        log.debug("topic = {} , payload = {}", topic, payload);
        mqttMessagingGateway.send(topic, payload);
    }

    @Override
    public void sendToAccountAlarm(String account, String message) {
        mqttMessagingGateway.send("/account/" + account + "/alarm/d", message);
    }
}
