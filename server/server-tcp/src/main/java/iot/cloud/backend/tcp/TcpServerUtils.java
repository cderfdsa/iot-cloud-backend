package iot.cloud.backend.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import iot.cloud.backend.config.ConfigForTcp;
import iot.cloud.backend.service.utils.SpringApplicationUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weichuang
 */
@Slf4j
public class TcpServerUtils {
    public static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final TcpServerUtils INSTANCE = new TcpServerUtils();
    private SocketInitializer socketInitializer;
    private ServerBootstrap serverBootstrap;
    private ConfigForTcp configForTcp;


    public static void start() {
        //
        INSTANCE.socketInitializer = new SocketInitializer();
        INSTANCE.configForTcp = SpringApplicationUtils.getApplicationContext().getBean(ConfigForTcp.class);
        //
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(INSTANCE.configForTcp.getBossThread());
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        INSTANCE.serverBootstrap = new ServerBootstrap();
        INSTANCE.serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(INSTANCE.socketInitializer);
        INSTANCE.serverBootstrap.bind(INSTANCE.configForTcp.getPort());
        //
        log.info("tcp server started on port: {} (TCP) with boss thread {}", INSTANCE.configForTcp.getPort(), INSTANCE.configForTcp.getBossThread());
    }

}

