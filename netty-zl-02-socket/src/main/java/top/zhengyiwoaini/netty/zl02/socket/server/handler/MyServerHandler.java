package top.zhengyiwoaini.netty.zl02.socket.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author jbzm
 * @date 2019-09-02 21:51
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    System.out.println(ctx.channel().remoteAddress() + "," + msg);
    ctx.channel().writeAndFlush("from server:" + UUID.randomUUID());
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("register:" + ctx.channel().remoteAddress());
    ctx.writeAndFlush("register success");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
