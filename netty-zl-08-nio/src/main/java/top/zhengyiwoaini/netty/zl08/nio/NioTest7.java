package top.zhengyiwoaini.netty.zl08.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jbzm
 * @date 2019-10-28 17:17
 */
public class NioTest7 {
  public static void main(String[] args) throws IOException {
      RandomAccessFile randomAccessFile = new RandomAccessFile("netty-zl-08-nio/NioTest7", "rw");
      FileChannel channel = randomAccessFile.getChannel();
      MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
      map.put(0,(byte)'a');
  }
}
