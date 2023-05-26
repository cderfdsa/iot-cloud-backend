//package iot.cloud.backend.tcp;
//
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @author weichuang
// */
//@Component
//@Order(2)
//@Slf4j
//public class TcpServerStartListener implements ApplicationRunner {
//    @Resource
//    private TcpServer tcpServer;
//
//    /**
//     * Callback used to run the bean.
//     *
//     * @param args incoming application arguments
//     * @throws Exception on error
//     */
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        this.tcpServer.start();
//    }
//}
