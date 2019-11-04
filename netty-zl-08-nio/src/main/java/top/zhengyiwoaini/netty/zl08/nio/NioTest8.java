package top.zhengyiwoaini.netty.zl08.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering与Gathering Scattering与Gathering会分别读取多个Buffer
 *
 * @author jbzm
 * @date 2019-10-28 17:24
 */
public class NioTest8 {
  public static void main(String[] args) throws IOException {
    int messageLength = 2 + 4 + 5;
    ByteBuffer[] byteBuffers = new ByteBuffer[3];
    byteBuffers[0] = ByteBuffer.allocate(2);
    byteBuffers[1] = ByteBuffer.allocate(4);
    byteBuffers[2] = ByteBuffer.allocate(5);
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    ServerSocketChannel bind = serverSocketChannel.bind(new InetSocketAddress(8899));
    SocketChannel accept = bind.accept();

    while (true) {
      long read = 0;
      while (read < messageLength) {
        long readLength = accept.read(byteBuffers);
        System.out.println("read message length:" + read);
        Arrays.stream(byteBuffers)
            .map(
                x ->
                    "capacity:" + x.capacity() + " limit:" + x.limit() + " position" + x.position())
            .forEach(System.out::println);
        read += readLength;
      }
      Arrays.stream(byteBuffers).forEach(Buffer::flip);
      long bytesWritten = 0;
      while (bytesWritten < messageLength) {
        long write = accept.write(byteBuffers);
        bytesWritten += write;
      }
      Arrays.stream(byteBuffers).forEach(Buffer::clear);
      System.out.println("bytesRead: " + read + " bytesWrite: " + bytesWritten);
    }
  }
}
