package iot.cloud.backend.mqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;
import com.hivemq.extension.sdk.api.events.client.ClientLifecycleEventListener;
import com.hivemq.extension.sdk.api.events.client.ClientLifecycleEventListenerProvider;
import com.hivemq.extension.sdk.api.events.client.parameters.ClientLifecycleEventListenerProviderInput;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang
 */
@Slf4j
public class MyClientLifecycleEventListenerProvider implements ClientLifecycleEventListenerProvider {
    @Override
    public @Nullable ClientLifecycleEventListener getClientLifecycleEventListener(@NotNull ClientLifecycleEventListenerProviderInput clientLifecycleEventListenerProviderInput) {
        log.debug("clientId = {} , IP = {} , mqttVersion = {}", clientLifecycleEventListenerProviderInput.getClientInformation().getClientId()
                , clientLifecycleEventListenerProviderInput.getConnectionInformation().getInetAddress().orElse(null)
                , clientLifecycleEventListenerProviderInput.getConnectionInformation().getMqttVersion());
        return new MyClientLifecycleEventListener();
    }
}
