package iot.cloud.backend.service.modules.mqtt.impl;

import com.alibaba.fastjson2.JSONObject;
import iot.cloud.backend.common.utils.JSONUtils;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.history.HistoryDeviceAttributeService;
import iot.cloud.backend.service.modules.mqtt.MqttReceiveService;
import iot.cloud.backend.service.modules.mqtt.MqttSendService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.Executor;

/**
 * @author weichuang
 */
@Slf4j
@Service
public class MqttReceiveServiceImpl implements MqttReceiveService {
    @Resource
    private Executor saveDeviceAttributesExecutor;
    @Resource
    private HistoryDeviceAttributeService historyDeviceAttributeService;
    @Resource
    private DeviceInfoService deviceInfoService;
    @Resource
    private MqttSendService mqttSendService;

    @Override
    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler handleMessage() {
        return message -> {
            //
            MessageHeaders messageHeaders = message.getHeaders();
            String topic = messageHeaders.get("mqtt_receivedTopic", String.class);
            String payload = (String) message.getPayload();
            log.info("topic = {}", topic);
            log.info("payload = {}", JSONUtils.formatString(payload));
            //
            if (topic.matches("/device/[a-zA-Z0-9]{6,10}/attributes/u")) {
                //
                String deviceCode = topic.split("/")[2];
                //
                log.info("handle topic = {}", topic);
                saveDeviceAttributesExecutor.execute(() -> {
                    JSONObject jsonObject = JSONUtils.parseObject(payload);
                    Set<String> keySet = jsonObject.keySet();
                    // TODO 可优化成批量插入
                    for (String attributeCode : keySet) {
                        long value = jsonObject.getLongValue(attributeCode);
                        historyDeviceAttributeService.add(deviceCode, attributeCode, value);
                    }
                });
                //
                log.info("forward publish topic = {}", topic);
                String account = deviceInfoService.getAccountByDeviceCode(deviceCode);
                mqttSendService.sendToAccountDevice(account, deviceCode, payload);
            } else {
                log.warn("no handle topic = {}", topic);
            }
        };
    }
}
