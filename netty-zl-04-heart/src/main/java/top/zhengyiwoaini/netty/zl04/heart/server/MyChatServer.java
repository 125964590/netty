package top.zhengyiwoaini.netty.zl04.heart.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import top.zhengyiwoaini.netty.zl04.heart.server.channel.MyHeartServerInitializer;

/**
 * @author jbzm
 * @date 2019-10-10 16:36
 */
public class MyChatServer {
  public static void main(String[] args) throws Exception {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap
          .group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
              .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new MyHeartServerInitializer());
      ChannelFuture sync = serverBootstrap.bind(8899).sync();
      sync.channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
