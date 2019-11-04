package top.zhengyiwoaini.netty.zl06.protobuf.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.zhengyiwoaini.netty.zl06.protobuf.DataInfo;

/**
 * @author jbzm
 * @date 2019-10-12 15:06
 */
public class ProtoBufHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
    System.out.println(msg.toString());
    DataInfo.Student student =
        DataInfo.Student.newBuilder().setAge(300).setName("bj").setAddress("10.1.12.5").build();
    ctx.channel().writeAndFlush(student);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("服务注册:" + ctx.channel().remoteAddress());
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    System.out.println("服务离开:" + ctx.channel().remoteAddress());
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("服务激活:" + ctx.channel().remoteAddress());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("服务失活:" + ctx.channel().remoteAddress());
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
  }
}
