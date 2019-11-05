package top.zhengyiwoaini.netty.zl08.nio;

import java.nio.ByteBuffer;

/**
 * Slice Buffer
 *
 * <p>{@link ByteBuffer#slice()}这个方法可以把当前的byteBuffer切分成一个新的{@code
 * ByteBuffer}但是不会开辟新的存储空间,也就是说对于存储的数据而言底层两个对象使用的是同样的存储空间. 但是对于position/limit/capacity的管理是各自维护的
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
