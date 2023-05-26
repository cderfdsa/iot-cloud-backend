package iot.cloud.backend.mqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;
import com.hivemq.extension.sdk.api.auth.Authenticator;
import com.hivemq.extension.sdk.api.auth.parameter.AuthenticatorProviderInput;
import com.hivemq.extension.sdk.api.services.auth.provider.AuthenticatorProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang
 */
@Slf4j
public class ConnectProvider implements AuthenticatorProvider {
    @Override
    public @Nullable Authenticator getAuthenticator(@NotNull AuthenticatorProviderInput authenticatorProviderInput) {
        log.info("getAuthenticator,{}", authenticatorProviderInput);
        return new ConnectAuthenticator();
    }
}
