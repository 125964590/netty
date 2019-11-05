package top.zhengyiwoaini.netty.zl08.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 特殊的例子(操作{@code ByteBuffer}之前不适用{@link ByteBuffer#clear()}方法)
 *
 * <p>在对块空间进行操作的时候需要记得调用{@link ByteBuffer}函数
 *
 * @author jbzm
 * @date 2019-10-28 16:05
 */
public class NioTest6 {
  public static void main(String[] args) throws Exception {
    FileInputStream fileInputStream = new FileInputStream("netty-zl-08-nio/input2.text");
    FileOutputStream fileOutputStream = new FileOutputStream("netty-zl-08-nio/output2.text");
    FileChannel inputChannel = fileInputStream.getChannel();
    FileChannel outputChannel = fileOutputStream.getChannel();
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(300);
    while (true) {
      byteBuffer.clear();
      // 当无法读取数据时会返回-1,
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
