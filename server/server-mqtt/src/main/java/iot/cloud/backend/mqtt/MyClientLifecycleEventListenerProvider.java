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
    /**
     * This method is called by HiveMQ every time a new MQTT connection is started.
     * <p>
     * This provider can either supply the same EventListener (stateless or must be thread-safe) or a new one (stateful,
     * must not be thread-safe) for each MQTT connection.
     *
     * @param clientLifecycleEventListenerProviderInput The {@link ClientLifecycleEventListenerProviderInput}.
     * @return An implementation of {@link ClientLifecycleEventListener}. The return value <code>null</code> is ignored
     * and has the same effect as if this provider would had not been set for the connecting client.
     * @since 4.0.0, CE 2019.1
     */
    @Override
    public @Nullable ClientLifecycleEventListener getClientLifecycleEventListener(@NotNull ClientLifecycleEventListenerProviderInput clientLifecycleEventListenerProviderInput) {
        log.debug("running");
        return new MyClientLifecycleEventListener();
    }
}
