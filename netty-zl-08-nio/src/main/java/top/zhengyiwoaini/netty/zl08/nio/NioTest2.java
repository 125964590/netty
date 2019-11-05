package top.zhengyiwoaini.netty.zl08.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Channel 测试01
 *
 * <p>在nio中存在三个基本组件 <b>channel</b> <b>buffer</b> <b>select</b>,通过这三个组件我们可以完成数据的读写操作,下面的代码是通{@link
 * FileChannel}对文件进行读写操作
 *
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
