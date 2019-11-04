package top.zhengyiwoaini.netty.zl04.heart.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author jbzm
 * @date 2019-10-10 18:21
 */
public class MyHeartServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
      String eventType = null;
      switch (idleStateEvent.state()) {
        case READER_IDLE:
          eventType = "读空闲";
          break;
        case WRITER_IDLE:
          eventType = "写空闲";
          break;
        case ALL_IDLE:
          eventType = "度写空闲";
          break;
        default:
      }
      System.out.println(ctx.channel().remoteAddress() + "超时实践" + eventType);
      ctx.channel().close();
    }
  }
}
