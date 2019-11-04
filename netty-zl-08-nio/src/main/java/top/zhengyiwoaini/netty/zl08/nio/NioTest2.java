package top.zhengyiwoaini.netty.zl08.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jbzm
 * @date 2019-10-23 15:54
 */
public class NioTest2 {
  public static void main(String[] args) throws Exception {
    FileInputStream fileInputStream = new FileInputStream("netty-zl-08-nio/NioTest2");
    FileChannel channel = fileInputStream.getChannel();
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    channel.read(byteBuffer);
    byteBuffer.flip();
    while (byteBuffer.hasRemaining()) {
      System.out.println((char) byteBuffer.get());
    }
  }
}
