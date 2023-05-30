package iot.cloud.backend.mqtt;

import com.alibaba.fastjson2.JSONObject;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.auth.SimpleAuthenticator;
import com.hivemq.extension.sdk.api.auth.parameter.SimpleAuthInput;
import com.hivemq.extension.sdk.api.auth.parameter.SimpleAuthOutput;
import iot.cloud.backend.common.utils.constant.ConstantForStatus;
import iot.cloud.backend.common.utils.constant.ConstantForStatusReason;
import iot.cloud.backend.config.ConfigForHiveMq;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.history.HistoryDeviceOnlineService;
import iot.cloud.backend.service.modules.mqtt.MqttSendService;
import iot.cloud.backend.service.modules.user.UserInfoService;
import iot.cloud.backend.service.utils.SpringApplicationUtils;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author weichuang
 */
@Slf4j
public class ConnectAuthenticator implements SimpleAuthenticator {
    @Override
    public void onConnect(@NotNull SimpleAuthInput simpleAuthInput, @NotNull SimpleAuthOutput simpleAuthOutput) {
        String clientId = simpleAuthInput.getConnectPacket().getClientId();
        String username = simpleAuthInput.getConnectPacket().getUserName().get();
        ByteBuffer passwordByteBuffer = simpleAuthInput.getConnectPacket().getPassword().get();
        String password = StandardCharsets.UTF_8.decode(passwordByteBuffer).toString();
        log.info("onConnect,{},{},{}", clientId, username, password);
        //
        if (SpringApplicationUtils.getApplicationContext() == null && clientId.startsWith("manager:")) {
            simpleAuthOutput.authenticateSuccessfully();
            log.info("onConnect applicationContext=null,{},{},{}", clientId, username, password);
            return;
        }
        if (clientId.startsWith("device:")) {
            String deviceCode = username;
            DeviceInfoService deviceInfoService = SpringApplicationUtils.getApplicationContext().getBean(DeviceInfoService.class);
            if (deviceInfoService.auth(deviceCode, password)) {
                //
                simpleAuthOutput.authenticateSuccessfully();
                //
                publishUserDeviceOnline(deviceCode);
                //
                addHistoryDeviceOnline(deviceCode);
            } else {
                simpleAuthOutput.failAuthentication();
            }
        } else if (clientId.startsWith("account:")) {
            String account = username;
            UserInfoService userInfoService = SpringApplicationUtils.getApplicationContext().getBean(UserInfoService.class);
            if (userInfoService.authForMqtt(account, password)) {
                simpleAuthOutput.authenticateSuccessfully();
            } else {
                simpleAuthOutput.failAuthentication();
            }
        } else if (clientId.startsWith("manager:")) {
            ConfigForHiveMq configForHiveMq = SpringApplicationUtils.getApplicationContext().getBean(ConfigForHiveMq.class);
            if (username.startsWith(configForHiveMq.getAuthManager().getUsername()) && password.equals(configForHiveMq.getAuthManager().getPassword())) {
                simpleAuthOutput.authenticateSuccessfully();
            } else {
                simpleAuthOutput.failAuthentication();
            }
        } else {
            simpleAuthOutput.failAuthentication("clientId starts with 'device:'");
        }
    }

    private void publishUserDeviceOnline(String deviceCode) {
        DeviceInfoService deviceInfoService = SpringApplicationUtils.getBean(DeviceInfoService.class);
        String account = deviceInfoService.getAccountByDeviceCode(deviceCode);
        MqttSendService mqttSendService = SpringApplicationUtils.getBean(MqttSendService.class);
        mqttSendService.sendToAccountOnline(account, JSONObject.of(deviceCode, "1").toString());
    }

    private void addHistoryDeviceOnline(String deviceCode) {
        HistoryDeviceOnlineService historyDeviceOnlineService = SpringApplicationUtils.getBean(HistoryDeviceOnlineService.class);
        historyDeviceOnlineService.add(deviceCode, ConstantForStatus.ONLINE, ConstantForStatusReason.OK);
    }
}
