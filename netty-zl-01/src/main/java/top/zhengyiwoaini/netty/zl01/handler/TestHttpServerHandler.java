package top.zhengyiwoaini.netty.zl01.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author jbzm
 * @date 2019-09-02 20:42
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
    if (msg instanceof HttpRequest) {
      HttpRequest httpRequest = (HttpRequest) msg;
      System.out.println("请求方法名:" + httpRequest.method());
      URI uri = new URI(httpRequest.uri());
      System.out.println("请求路径:" + uri.getPath());
      ByteBuf helloWorld = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
      FullHttpResponse response =
          new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, helloWorld);
      response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
      response.headers().set(HttpHeaderNames.CONTENT_LENGTH, helloWorld.readableBytes());
      System.out.println("hello world");
      ctx.writeAndFlush(response);
    }
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelRegistered");
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelUnregistered");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelActive");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelInactive");
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelReadComplete");
  }
}
