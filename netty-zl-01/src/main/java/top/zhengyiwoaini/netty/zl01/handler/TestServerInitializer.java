package top.zhengyiwoaini.netty.zl01.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author jbzm
 * @date 2019-09-02 20:37
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast("httpServerCodec", new HttpServerCodec());
    pipeline.addLast("httpServerServlet", new TestHttpServerHandler());
  }
}
