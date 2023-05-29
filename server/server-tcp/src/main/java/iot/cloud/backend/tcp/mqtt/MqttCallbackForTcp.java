package iot.cloud.backend.tcp.mqtt;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author weichuang
 */
@Data
@AllArgsConstructor
@Slf4j
public class MqttCallbackForTcp implements MqttCallback {
    @Getter
    private Channel channel;
    @Getter
    private MqttClient mqttClient;
    @Getter
    private String deviceCode;

    @Override
    public void connectionLost(Throwable cause) {
        log.error(cause.getMessage(), cause);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("mqtt broker to tcp : topic = {} , message = {}", topic, message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        if (token.isComplete()) {
            log.info("tcp publish to mqtt broker success");
        } else {
            log.error("tcp publish to mqtt broker fail", token.getException());
        }
    }
}
