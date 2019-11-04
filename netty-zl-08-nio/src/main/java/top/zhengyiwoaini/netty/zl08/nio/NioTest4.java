package top.zhengyiwoaini.netty.zl08.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jbzm
 * @date 2019-10-25 17:43
 */
public class NioTest4 {
  public static void main(String[] args) throws Exception {
    FileInputStream fileInputStream = new FileInputStream("netty-zl-08-nio/input.text");
    FileOutputStream fileOutputStream = new FileOutputStream("netty-zl-08-nio/output.text");
    FileChannel inputChannel = fileInputStream.getChannel();
    FileChannel outputChannel = fileOutputStream.getChannel();
    ByteBuffer byteBuffer = ByteBuffer.allocate(300);
    while (true) {
      byteBuffer.clear();
      //当无法读取数据时会返回-1,
      int read = inputChannel.read(byteBuffer);
      if (read == -1) {
        break;
      }
      byteBuffer.flip();
      outputChannel.write(byteBuffer);
    }
    fileInputStream.close();
    fileOutputStream.close();
  }
}
