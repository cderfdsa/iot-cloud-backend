package iot.cloud.backend.service.modules.mqtt;

import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author weichuang
 */
@MessagingGateway
@Component
@Lazy
public interface MqttMessagingGateway {
    @Gateway(requestChannel = "mqttOutboundChannel")
    void send(@Header(MqttHeaders.TOPIC) String topic, Message<byte[]> out);
}
