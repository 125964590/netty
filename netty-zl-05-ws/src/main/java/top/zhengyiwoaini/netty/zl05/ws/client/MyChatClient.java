package top.zhengyiwoaini.netty.zl05.ws.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.zhengyiwoaini.netty.zl05.ws.client.channel.MyChatClientInitializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author jbzm
 * @date 2019-10-10 17:25
 */
public class MyChatClient {

  public static void main(String[] args) throws Exception {
    EventLoopGroup eventExecutors = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap
          .group(eventExecutors)
          .channel(NioSocketChannel.class)
          .handler(new MyChatClientInitializer());
      Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      for (; ; ) {
        channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
      }
    } finally {
      eventExecutors.shutdownGracefully();
    }
  }
}
