package top.zhengyiwoaini.netty.zl06.protobuf.client.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import top.zhengyiwoaini.netty.zl06.protobuf.DataInfo;
import top.zhengyiwoaini.netty.zl06.protobuf.client.handler.ProtoBufClientHandler;

/**
 * @author jbzm
 * @date 2019-10-12 15:50
 */
public class ProtoBufClientInitialzer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new ProtobufVarint32FrameDecoder());
    pipeline.addLast(new ProtobufDecoder(DataInfo.Student.getDefaultInstance()));
    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
    pipeline.addLast(new ProtobufEncoder());
    pipeline.addLast(new ProtoBufClientHandler());
  }
}
