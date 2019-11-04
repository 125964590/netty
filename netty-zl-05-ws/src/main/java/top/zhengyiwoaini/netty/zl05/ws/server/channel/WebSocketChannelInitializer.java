package top.zhengyiwoaini.netty.zl05.ws.server.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import top.zhengyiwoaini.netty.zl05.ws.server.handler.TextWebSocketFrameHandler;

/**
 * @author jbzm
 * @date 2019-10-10 20:53
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new HttpServerCodec());
    pipeline.addLast(new ChunkedWriteHandler());
    pipeline.addLast(new HttpObjectAggregator(4096));
    pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
    pipeline.addLast(new TextWebSocketFrameHandler());
  }
}
