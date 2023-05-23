package iot.cloud.backend.service.modules.mqtt.impl;

import iot.cloud.backend.service.modules.mqtt.MqttReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Slf4j
@Service
@Lazy
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

    @Override
    public void start() {
        log.info("mqtt receive service started");
    }
}
