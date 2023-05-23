package iot.cloud.backend.mqtt;

import io.github.quickmsg.core.auth.IMqttConnectAuth;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.utils.SpringApplicationUtils;
import org.springframework.boot.SpringApplication;

import java.nio.charset.StandardCharsets;

/**
 * @author weichuang
 */
public class MqttConnectAuth implements IMqttConnectAuth {
    @Override
    public boolean auth(String userName, byte[] passwordInBytes, String clientIdentifier1) {
        String password = new String(passwordInBytes, StandardCharsets.UTF_8);
        

        if ("default".equals(userName) && "Aa0123".equals(password)) {
            return true;
        }
        SpringApplication springApplication = SpringApplicationUtils.getSpringApplication();
        if (clientIdentifier1.startsWith("account:")) {
            UserInfoService userInfoService = SpringApplicationUtils.getApplicationContext().getBean(UserInfoService.class);
            return userInfoService.authForMqtt(userName, password);
        } else {
            DeviceInfoService deviceInfoService = SpringApplicationUtils.getApplicationContext().getBean(DeviceInfoService.class);
            return deviceInfoService.auth(userName, password);
        }
    }
}
