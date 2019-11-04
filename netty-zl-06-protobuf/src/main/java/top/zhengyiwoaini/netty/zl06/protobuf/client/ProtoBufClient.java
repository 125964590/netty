package top.zhengyiwoaini.netty.zl06.protobuf.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.zhengyiwoaini.netty.zl06.protobuf.DataInfo;
import top.zhengyiwoaini.netty.zl06.protobuf.client.channel.ProtoBufClientInitialzer;

/**
 * @author jbzm
 * @date 2019-10-12 15:17
 */
public class ProtoBufClient {
  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup eventExecute = new NioEventLoopGroup();

    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap
          .group(eventExecute)
          .channel(NioSocketChannel.class)
          .handler(new ProtoBufClientInitialzer());

      ChannelFuture localhost = bootstrap.connect("localhost", 8899).sync();
      localhost.channel().closeFuture().sync();
    } finally {
      eventExecute.shutdownGracefully();
    }
  }
}
