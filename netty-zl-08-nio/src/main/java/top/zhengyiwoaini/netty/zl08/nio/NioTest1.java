package top.zhengyiwoaini.netty.zl08.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * 创建Buffer
 *
 * <p>通过{@link IntBuffer#allocate(int)}方法直接开辟内存空间大小.
 *
 * <p>在使用的时候为了完成读写需要通过{@code flip()}方法进行切换.
 *
 * <p>在Buffer中存在三个比较重要的概念:
 *
 * <pre>
 * 1. position:可写的位置
 * 2. limit:可读的终结位置
 * 3. capacity:空间终点位置
 * </pre>
 *
 * @author jbzm
 * @date 2019-10-23 15:25
 */
public class NioTest1 {
  public static void main(String[] args) {
    IntBuffer intBuffer = IntBuffer.allocate(10);
    for (int i = 0; i < intBuffer.capacity(); i++) {
      int i1 = new SecureRandom().nextInt(20);
      intBuffer.put(i1);
    }
    intBuffer.flip();
    while (intBuffer.hasRemaining()) {
      System.out.println(intBuffer.get());
    }
  }
}
