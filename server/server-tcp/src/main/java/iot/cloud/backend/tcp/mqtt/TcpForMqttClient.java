package iot.cloud.backend.tcp.mqtt;

import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.Channel;
import iot.cloud.backend.config.ConfigForMqttClients;
import iot.cloud.backend.service.utils.SpringApplicationUtils;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

/**
 * @author weichuang
 */
@Data
@Slf4j
public class TcpForMqttClient {
    @Getter
    private Channel channel;
    @Getter
    private MqttClient mqttClient;
    @Getter
    private String deviceCode;
    @Getter
    private MqttCallbackForTcp mqttCallbackForTcp;

    public TcpForMqttClient(Channel channel, String deviceCode, String devicePwd) throws MqttException {
        this.channel = channel;
        this.deviceCode = deviceCode;
        //
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(deviceCode);
        mqttConnectOptions.setPassword(devicePwd.toCharArray());
        mqttConnectOptions.setAutomaticReconnect(true);
        //
        ConfigForMqttClients configForMqttClients = SpringApplicationUtils.getBean(ConfigForMqttClients.class);
        mqttClient = new MqttClient(configForMqttClients.getUrl(), "device:" + deviceCode, new MemoryPersistence());
        //
        mqttCallbackForTcp = new MqttCallbackForTcp(channel, mqttClient, deviceCode);
        mqttClient.setCallback(mqttCallbackForTcp);
        //
        IMqttToken mqttToken = mqttClient.connectWithResult(mqttConnectOptions);
        //
        if (mqttToken.isComplete()) {
            log.info("tcp connect to mqtt broker success , code = {}", deviceCode);
            mqttClient.subscribe("/device/" + deviceCode + "/attributes/d");
        } else {
            log.error("tcp connect to mqtt broker fail , code = {} , error = {}", deviceCode, mqttToken.getException());
        }
    }

    public void publishForDeviceAttribute(JSONObject jsonObject) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(0);
        mqttMessage.setPayload(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        MqttTopic mqttTopic = mqttClient.getTopic("/device/" + deviceCode + "/attributes/u");
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }
}
