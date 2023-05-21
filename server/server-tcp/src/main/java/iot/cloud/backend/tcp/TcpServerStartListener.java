package iot.cloud.backend.tcp;

import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author weichuang 2023/5/21 23:16
 */
@Component
public class TcpServerStartListener implements ApplicationRunner {
    @Resource
    private TcpServer tcpServer;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.tcpServer.start();
    }
}
