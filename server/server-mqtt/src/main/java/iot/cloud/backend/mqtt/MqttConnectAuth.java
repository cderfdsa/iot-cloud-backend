package iot.cloud.backend.mqtt;

import io.github.quickmsg.common.config.AuthConfig;
import io.github.quickmsg.core.auth.IMqttConnectAuth;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.utils.SpringApplicationUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author weichuang
 */
public class MqttConnectAuth implements IMqttConnectAuth {
    @Override
    public boolean auth(String userName, byte[] passwordInBytes, String clientIdentifier1, AuthConfig.SpiAuthConfig spiAuthConfig) {
        String password = new String(passwordInBytes, StandardCharsets.UTF_8);

        if (clientIdentifier1.startsWith("device:")) {
            DeviceInfoService deviceInfoService = SpringApplicationUtils.getApplicationContext().getBean(DeviceInfoService.class);
            return deviceInfoService.auth(userName, password);
        } else if (clientIdentifier1.startsWith("account:")) {
            UserInfoService userInfoService = SpringApplicationUtils.getApplicationContext().getBean(UserInfoService.class);
            return userInfoService.authForMqtt(userName, password);
        } else if (clientIdentifier1.startsWith("manager:")) {
            if (spiAuthConfig.getManagerUsername().equals(userName) && spiAuthConfig.getManagerPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
