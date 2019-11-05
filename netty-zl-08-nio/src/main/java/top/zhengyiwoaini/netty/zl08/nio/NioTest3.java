package top.zhengyiwoaini.netty.zl08.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Channel 测试02
 *
 * <p>在nio中存在三个基本组件 <b>channel</b> <b>buffer</b> <b>select</b>,通过这三个组件我们可以完成数据的读写操作,下面的代码是通{@link *
 * FileChannel}对文件进行读写操作
 *
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
