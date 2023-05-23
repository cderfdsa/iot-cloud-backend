package iot.cloud.backend.tcp;

import iot.cloud.backend.service.modules.mqtt.MqttReceiveService;
import iot.cloud.backend.service.modules.mqtt.MqttSendService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author weichuang
 */
@Component
@Order(1)
public class TcpServerStartListener implements ApplicationRunner {
    @Resource
    private TcpServer tcpServer;
    @Resource
    private MqttSendService mqttSendService;
    @Resource
    private MqttReceiveService mqttReceiveService;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //
        this.tcpServer.start();
        //
        mqttSendService.sendToAccountOnline("default", "hello world");
        //
        mqttReceiveService.start();
    }
}
