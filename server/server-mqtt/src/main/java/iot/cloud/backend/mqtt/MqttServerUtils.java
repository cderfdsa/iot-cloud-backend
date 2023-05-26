package iot.cloud.backend.mqtt;

import com.codahale.metrics.MetricRegistry;
import com.hivemq.HiveMQServer;
import com.hivemq.configuration.info.SystemInformation;
import com.hivemq.configuration.info.SystemInformationImpl;
import com.hivemq.embedded.EmbeddedExtension;
import com.hivemq.exceptions.StartAbortedException;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

/**
 * @author weichuang
 */
//@Component
@Slf4j
public class MqttServerUtils {
    
    public static void start() {
        //
        final EmbeddedExtension embeddedExtension = EmbeddedExtension.builder()
                .withId("embedded-ext-1")
                .withName("Embedded Extension")
                .withVersion("1.0.0")
                .withPriority(0)
                .withStartPriority(1000)
                .withAuthor("weichuang")
                .withExtensionMain(new HiveMqEmbeddedExtensionMain())
                .build();
        //
        SystemInformation systemInformation = new SystemInformationImpl(
                false,
                true,
                Path.of("hivemq/embedded-config-folder").toFile(),
                Path.of("hivemq/embedded-data-folder").toFile(),
                Path.of("hivemq/embedded-extensions-folder").toFile()
        );
        systemInformation.init();
        MetricRegistry metricRegistry = new MetricRegistry();
        final HiveMQServer server = new HiveMQServer(systemInformation, metricRegistry, null, true);
        try {
            server.start(embeddedExtension);
        } catch (final StartAbortedException e) {
            log.info("HiveMQ start was cancelled. {}", e.getMessage());
        } catch (Exception e) {
            log.info("HiveMQ start was cancelled. {}", e.getMessage());
        }
    }
}
