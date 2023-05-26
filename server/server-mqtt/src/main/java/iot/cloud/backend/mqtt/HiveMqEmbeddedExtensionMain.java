package iot.cloud.backend.mqtt;

import com.hivemq.extension.sdk.api.ExtensionMain;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartOutput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopOutput;
import com.hivemq.extension.sdk.api.services.Services;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang
 */
@Slf4j
public class HiveMqEmbeddedExtensionMain implements ExtensionMain {
    @Override
    public void extensionStart(@NotNull ExtensionStartInput extensionStartInput, @NotNull ExtensionStartOutput extensionStartOutput) {
        log.info("extensionStart,{},{}", extensionStartInput, extensionStartOutput);
        Services.securityRegistry().setAuthenticatorProvider(new ConnectProvider());
        Services.securityRegistry().setAuthorizerProvider(new PublishAndSubscriptionProvider());
    }

    @Override
    public void extensionStop(@NotNull ExtensionStopInput extensionStopInput, @NotNull ExtensionStopOutput extensionStopOutput) {
        log.info("extensionStop,{},{}", extensionStopInput, extensionStopOutput);
    }
}
