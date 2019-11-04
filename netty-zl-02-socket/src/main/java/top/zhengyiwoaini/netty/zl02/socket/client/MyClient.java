package top.zhengyiwoaini.netty.zl02.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.zhengyiwoaini.netty.zl02.socket.client.channel.MyClientInitializer;

/**
 * @author jbzm
 * @date 2019-09-02 21:57
 */
public class MyClient {
  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap
          .group(eventExecutors)
          .channel(NioSocketChannel.class)
          .handler(new MyClientInitializer());
      ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
      channelFuture.channel().closeFuture().sync();
    } finally {
      eventExecutors.shutdownGracefully();
    }
  }
}
