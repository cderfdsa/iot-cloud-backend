package iot.cloud.backend.service.modules.mqtt;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author weichuang
 */
@MessagingGateway
public interface MqttMessagingGateway {
    @Gateway(requestChannel = "mqttOutboundChannel")
    void send(@Header(MqttHeaders.TOPIC) String topic, String message);
}
