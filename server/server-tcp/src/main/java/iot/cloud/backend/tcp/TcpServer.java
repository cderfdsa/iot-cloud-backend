package iot.cloud.backend.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import iot.cloud.backend.config.ConfigForTcp;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author weichuang 2023/5/21 23:08
 */
@Slf4j
@Component
public class TcpServer {
    @Resource
    private SocketInitializer socketInitializer;
    @Getter
    private ServerBootstrap serverBootstrap;
    @Resource
    private ConfigForTcp configForTcp;


    /**
     * 启动netty服务器
     */
    public void start() {
        this.init();
        this.serverBootstrap.bind(configForTcp.getPort());
        log.info("tcp server started on port: {} (TCP) with boss thread {}", configForTcp.getPort(), configForTcp.getBossThread());
    }

    /**
     * 初始化netty配置
     */
    private void init() {
        // 创建两个线程组，bossGroup为接收请求的线程组，一般1-2个就行
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(configForTcp.getBossThread());
        // 实际工作的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap();
        this.serverBootstrap.group(bossGroup, workerGroup) // 两个线程组加入进来
                .channel(NioServerSocketChannel.class)  // 配置为nio类型
                .childHandler(this.socketInitializer); // 加入自己的初始化器
    }
}

