package iot.cloud.backend.mqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.auth.PublishAuthorizer;
import com.hivemq.extension.sdk.api.auth.SubscriptionAuthorizer;
import com.hivemq.extension.sdk.api.auth.parameter.PublishAuthorizerInput;
import com.hivemq.extension.sdk.api.auth.parameter.PublishAuthorizerOutput;
import com.hivemq.extension.sdk.api.auth.parameter.SubscriptionAuthorizerInput;
import com.hivemq.extension.sdk.api.auth.parameter.SubscriptionAuthorizerOutput;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang
 */
@Slf4j
public class PublishAndSubscriptionAuthorizer implements PublishAuthorizer, SubscriptionAuthorizer {
    @Override
    public void authorizePublish(@NotNull PublishAuthorizerInput publishAuthorizerInput, @NotNull PublishAuthorizerOutput publishAuthorizerOutput) {
        String clientId = publishAuthorizerInput.getClientInformation().getClientId();
        String topic = publishAuthorizerInput.getPublishPacket().getTopic();
        log.info("authorizePublish,{},{}", clientId, topic);
        if (clientId.startsWith("device:") && topic.endsWith("/u")) {
            String username = clientId.substring(7);
            if (topic.startsWith("/device/" + username + "/")) {
                publishAuthorizerOutput.authorizeSuccessfully();
            } else {
                log.warn("authorizePublish fail,{},{}", clientId, topic);
                publishAuthorizerOutput.failAuthorization();
            }
        } else if (clientId.startsWith("account:") && topic.endsWith("/u")) {
            String username = clientId.substring(8);
            if (topic.startsWith("/account/" + username + "/")) {
                publishAuthorizerOutput.authorizeSuccessfully();
            } else {
                log.warn("authorizePublish fail,{},{}", clientId, topic);
                publishAuthorizerOutput.failAuthorization();
            }
        } else if (clientId.startsWith("manager:")) {
            publishAuthorizerOutput.authorizeSuccessfully();
        } else {
            log.warn("authorizePublish fail,{},{}", clientId, topic);
            publishAuthorizerOutput.failAuthorization();
        }
    }

    @Override
    public void authorizeSubscribe(@NotNull SubscriptionAuthorizerInput subscriptionAuthorizerInput, @NotNull SubscriptionAuthorizerOutput subscriptionAuthorizerOutput) {
        String clientId = subscriptionAuthorizerInput.getClientInformation().getClientId();
        String topic = subscriptionAuthorizerInput.getSubscription().getTopicFilter();
        log.info("authorizeSubscribe,{},{}", clientId, topic);
        if (clientId.startsWith("device:") && topic.endsWith("/d")) {
            String username = clientId.substring(7);
            if (topic.startsWith("/device/" + username + "/")) {
                subscriptionAuthorizerOutput.authorizeSuccessfully();
            } else {
                log.warn("authorizeSubscribe fail,{},{}", clientId, topic);
                subscriptionAuthorizerOutput.failAuthorization();
            }
        } else if (clientId.startsWith("account:") && topic.endsWith("/d")) {
            String username = clientId.substring(8);
            if (topic.startsWith("/account/" + username + "/")) {
                subscriptionAuthorizerOutput.authorizeSuccessfully();
            } else {
                log.warn("authorizeSubscribe fail,{},{}", clientId, topic);
                subscriptionAuthorizerOutput.failAuthorization();
            }
        } else if (clientId.startsWith("manager:")) {
            subscriptionAuthorizerOutput.authorizeSuccessfully();
        } else {
            log.warn("authorizeSubscribe fail,{},{}", clientId, topic);
            subscriptionAuthorizerOutput.failAuthorization();
        }
    }
}
