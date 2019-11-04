package top.zhengyiwoaini.netty.zl06.protobuf.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.zhengyiwoaini.netty.zl06.protobuf.DataInfo;

/**
 * @author jbzm
 * @date 2019-10-12 15:50
 */
public class ProtoBufClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
    DataInfo.Student student =
            DataInfo.Student.newBuilder().setAge(200).setName("bj").setAddress("10.1.12.5").build();
    ctx.channel().writeAndFlush(student);
    System.out.println(msg.toString());
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("client active");
    DataInfo.Student student =
        DataInfo.Student.newBuilder().setAge(20).setName("bj").setAddress("10.1.12.5").build();
    ctx.channel().writeAndFlush(student);
  }
}
