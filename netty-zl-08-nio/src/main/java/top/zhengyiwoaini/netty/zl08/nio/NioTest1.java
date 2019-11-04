package top.zhengyiwoaini.netty.zl08.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
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
