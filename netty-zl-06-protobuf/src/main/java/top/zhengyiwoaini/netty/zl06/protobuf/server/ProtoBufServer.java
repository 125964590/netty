package top.zhengyiwoaini.netty.zl06.protobuf.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import top.zhengyiwoaini.netty.zl06.protobuf.server.channel.ProtoBufServerInitialzer;

import java.io.File;
import java.net.InetSocketAddress;

/**
 * @author jbzm
 * @date 2019-10-12 14:54
 */
public class ProtoBufServer {
  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup bossThread = new NioEventLoopGroup();
    NioEventLoopGroup workerThread = new NioEventLoopGroup();

    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap
          .group(bossThread, workerThread)
          .channel(NioServerSocketChannel.class)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new ProtoBufServerInitialzer());
      ChannelFuture sync = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
      sync.channel().closeFuture().sync();
    } finally {
      bossThread.shutdownGracefully();
      workerThread.shutdownGracefully();
    }
  }
}
