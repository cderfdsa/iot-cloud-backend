package iot.cloud.backend.mqtt;

import com.alibaba.fastjson2.JSONObject;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.events.client.ClientLifecycleEventListener;
import com.hivemq.extension.sdk.api.events.client.parameters.*;
import com.hivemq.extension.sdk.api.packets.connect.ConnectPacket;
import iot.cloud.backend.common.utils.constant.ConstantForStatus;
import iot.cloud.backend.common.utils.constant.ConstantForStatusReason;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.modules.history.HistoryDeviceOnlineService;
import iot.cloud.backend.service.modules.mqtt.MqttSendService;
import iot.cloud.backend.service.utils.SpringApplicationUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang
 */
@Slf4j
public class MyClientLifecycleEventListener implements ClientLifecycleEventListener {

    private String clientId;
    private String username;

    @Override
    public void onMqttConnectionStart(@NotNull ConnectionStartInput connectionStartInput) {
        log.info("running");
        ConnectPacket connectPacket = connectionStartInput.getConnectPacket();
        clientId = connectPacket.getClientId();
        username = connectPacket.getUserName().orElse("");
    }

    @Override
    public void onAuthenticationSuccessful(@NotNull AuthenticationSuccessfulInput authenticationSuccessfulInput) {
        log.info("running");
        // TODO if device publish online to account
        // TODO if device insert history online
    }

    @Override
    public void onDisconnect(@NotNull DisconnectEventInput disconnectEventInput) {
        log.info("running");
        // if device publish offline to account
        // if device insert history offline
        if (clientId.startsWith("device:")) {
            publishUserDeviceOffline(username);
            addHistoryDeviceOffline(username);
        }
    }

    @Override
    public void onAuthenticationFailedDisconnect(@NotNull AuthenticationFailedInput authenticationFailedInput) {
        ClientLifecycleEventListener.super.onAuthenticationFailedDisconnect(authenticationFailedInput);
    }

    @Override
    public void onConnectionLost(@NotNull ConnectionLostInput connectionLostInput) {
        ClientLifecycleEventListener.super.onConnectionLost(connectionLostInput);
    }

    @Override
    public void onClientInitiatedDisconnect(@NotNull ClientInitiatedDisconnectInput clientInitiatedDisconnectInput) {
        ClientLifecycleEventListener.super.onClientInitiatedDisconnect(clientInitiatedDisconnectInput);
    }

    @Override
    public void onServerInitiatedDisconnect(@NotNull ServerInitiatedDisconnectInput serverInitiatedDisconnectInput) {
        ClientLifecycleEventListener.super.onServerInitiatedDisconnect(serverInitiatedDisconnectInput);
    }

    private void publishUserDeviceOffline(String deviceCode) {
        DeviceInfoService deviceInfoService = SpringApplicationUtils.getBean(DeviceInfoService.class);
        String account = deviceInfoService.getAccountByDeviceCode(deviceCode);
        MqttSendService mqttSendService = SpringApplicationUtils.getBean(MqttSendService.class);
        mqttSendService.sendToAccountOnline(account, JSONObject.of(deviceCode, "2").toString());
    }

    private void addHistoryDeviceOffline(String deviceCode) {
        HistoryDeviceOnlineService historyDeviceOnlineService = SpringApplicationUtils.getBean(HistoryDeviceOnlineService.class);
        historyDeviceOnlineService.add(deviceCode, ConstantForStatus.OFFLINE, ConstantForStatusReason.OK);
    }
}
