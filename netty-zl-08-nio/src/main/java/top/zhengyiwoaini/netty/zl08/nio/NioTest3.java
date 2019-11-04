package top.zhengyiwoaini.netty.zl08.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jbzm
 * @date 2019-10-23 16:06
 */
public class NioTest3 {
  public static void main(String[] args) throws Exception {
    FileOutputStream fileOutputStream = new FileOutputStream("netty-zl-08-nio/NioTest3");
    FileChannel channel = fileOutputStream.getChannel();
    byte[] bytes = "hello world ~~~".getBytes();
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    byteBuffer.put(bytes);
    byteBuffer.flip();
    channel.write(byteBuffer);
  }
}
