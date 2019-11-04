package top.zhengyiwoaini.netty.zl02.socket.client.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import top.zhengyiwoaini.netty.zl02.socket.client.handler.MyClientHandler;

/**
 * @author jbzm
 * @date 2019-09-02 22:03
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
    pipeline.addLast(new LengthFieldPrepender(4));
    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
    pipeline.addLast(new MyClientHandler());
  }
}
