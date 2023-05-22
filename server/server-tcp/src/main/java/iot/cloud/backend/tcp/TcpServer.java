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
 * @author weichuang
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


    public void start() {
        //
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(configForTcp.getBossThread());
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap();
        this.serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(this.socketInitializer);
        this.serverBootstrap.bind(configForTcp.getPort());
        //
        log.info("tcp server started on port: {} (TCP) with boss thread {}", configForTcp.getPort(), configForTcp.getBossThread());
    }

}

