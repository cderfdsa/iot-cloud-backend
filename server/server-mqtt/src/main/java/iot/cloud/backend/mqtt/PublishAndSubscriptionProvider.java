package iot.cloud.backend.mqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.Nullable;
import com.hivemq.extension.sdk.api.auth.Authorizer;
import com.hivemq.extension.sdk.api.auth.parameter.AuthorizerProviderInput;
import com.hivemq.extension.sdk.api.services.auth.provider.AuthorizerProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang
 */
@Slf4j
public class PublishAndSubscriptionProvider implements AuthorizerProvider {
    @Override
    public @Nullable Authorizer getAuthorizer(@NotNull AuthorizerProviderInput authorizerProviderInput) {
        log.info("getAuthorizer,{}", authorizerProviderInput);
        return new PublishAndSubscriptionAuthorizer();
    }
}
