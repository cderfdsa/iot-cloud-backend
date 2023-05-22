package iot.cloud.backend.service.modules.mqtt.impl;

import iot.cloud.backend.service.modules.mqtt.MqttReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHandler;

/**
 * @author weichuang
 */
@Slf4j
public class MqttReceiveServiceImpl implements MqttReceiveService {
    @Override
    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler handleMessage() {
        return message -> {
            log.info("----------------------");
            log.info("message:" + message.getPayload());
            log.info("PacketId:" + message.getHeaders().getId());
            log.info("Qos:" + message.getHeaders().get(MqttHeaders.QOS));

            String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
            log.info("topic:" + topic);
        };
    }
}
