package top.zhengyiwoaini.netty.zl08.nio;

import java.nio.ByteBuffer;

/**
 * Slice Buffer
 *
 * @author jbzm
 * @date 2019-10-25 18:19
 */
public class NioTest5 {
  public static void main(String[] args) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(10);
    for (int i = 0; i < byteBuffer.capacity(); i++) {
      byteBuffer.put((byte) i);
    }
    byteBuffer.position(2);
    byteBuffer.limit(6);
    ByteBuffer sliceBuffer = byteBuffer.slice();
    for (int i = 0; i < sliceBuffer.capacity(); i++) {
      byte b = sliceBuffer.get(i);
      sliceBuffer.put(i, (byte) (b * 2));
    }
    byteBuffer.clear();
    for (int i = 1; i < byteBuffer.capacity(); i++) {
      System.out.println(byteBuffer.get());
    }
  }
}
