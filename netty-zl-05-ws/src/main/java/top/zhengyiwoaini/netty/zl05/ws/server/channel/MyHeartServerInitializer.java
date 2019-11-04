package top.zhengyiwoaini.netty.zl05.ws.server.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import top.zhengyiwoaini.netty.zl05.ws.server.handler.TextWebSocketFrameHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author jbzm
 * @date 2019-10-10 16:47
 */
public class MyHeartServerInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
    pipeline.addLast(new TextWebSocketFrameHandler());
//    pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
//    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
//
//    pipeline.addLast(new MyChatServerHandler());
  }
}
