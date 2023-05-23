package iot.cloud.backend.config;

import iot.cloud.backend.common.utils.RandomStringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author weichuang
 */
@Configuration
@ConfigurationProperties(prefix = "iot.cloud.backend.mqtt-broker")
@Slf4j
public class ConfigForMqttClients {
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String clientId;
    @Getter
    @Setter
    private String topics;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private int timeout;
    @Getter
    @Setter
    private int keepalive;


    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel errorChannel() {
        return new DirectChannel();
    }

    @Lazy
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        String[] array = url.split(",");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(array);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(timeout);
        options.setKeepAliveInterval(keepalive);
        //
        options.setCleanSession(false);
        //
        factory.setConnectionOptions(options);
        return factory;
    }


    @Lazy
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler outbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId + "_out_" + RandomStringUtils.randomAlphanumeric(6), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setConverter(new DefaultPahoMessageConverter());
        return messageHandler;
    }


    @Bean
    public MessageProducer inbound() {
        String[] inboundTopics = topics.split(",");
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId + "_in_" + RandomStringUtils.randomAlphanumeric(6), mqttClientFactory(), inboundTopics);
        adapter.setCompletionTimeout(5000);
        adapter.setQos(1);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(mqttInboundChannel());
        adapter.setErrorChannel(errorChannel());
        return adapter;
    }
}


