package top.zhengyiwoaini.netty.zl03.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import top.zhengyiwoaini.netty.zl03.chat.server.channel.MyChatServerInitializer;

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
          .childHandler(new MyChatServerInitializer());
      ChannelFuture sync = serverBootstrap.bind(8899).sync();
      sync.channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
