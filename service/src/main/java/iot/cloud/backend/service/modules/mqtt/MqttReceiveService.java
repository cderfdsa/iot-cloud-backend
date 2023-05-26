package iot.cloud.backend.service.modules.mqtt;

import org.springframework.messaging.MessageHandler;

/**
 * @author weichuang
 */
public interface MqttReceiveService {

    MessageHandler handleMessage();

}
