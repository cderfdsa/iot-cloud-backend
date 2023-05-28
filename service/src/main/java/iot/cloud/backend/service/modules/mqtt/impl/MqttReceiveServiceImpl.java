package iot.cloud.backend.service.modules.mqtt.impl;

import iot.cloud.backend.common.utils.JSONUtils;
import iot.cloud.backend.service.modules.mqtt.MqttReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

/**
 * @author weichuang
 */
@Slf4j
@Service
public class MqttReceiveServiceImpl implements MqttReceiveService {
    @Override
    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler handleMessage() {
        return message -> {
            MessageHeaders messageHeaders = message.getHeaders();
            String messagePayload = (String) message.getPayload();
            log.info("messageHeaders={}", messageHeaders);
            log.info("messagePayload={}", JSONUtils.formatString(messagePayload));
        };
    }
}
