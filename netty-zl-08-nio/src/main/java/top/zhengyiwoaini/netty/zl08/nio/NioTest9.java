package top.zhengyiwoaini.netty.zl08.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author jbzm
 * @date 2019-10-31 11:27
 */
public class NioTest9 {
  public static void main(String[] args) throws IOException {
    int[] ports = new int[5];
    ports[0] = 5000;
    ports[1] = 5001;
    ports[2] = 5002;
    ports[3] = 5003;
    ports[4] = 5004;
    Selector selector = Selector.open();
    for (int i = 0; i < ports.length; i++) {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      ServerSocket socket = serverSocketChannel.socket();
      socket.bind(new InetSocketAddress(ports[i]));

      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("监听端口:" + ports[i]);
    }
    while (true) {
      int selectNum = selector.select();
      System.out.println("numbers:" + selectNum);
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      System.out.println("selectedKeys:" + selectionKeys);
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey selectionKey = iterator.next();
        if (selectionKey.isAcceptable()) {
          ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
          SocketChannel socketChannel = channel.accept();
          socketChannel.configureBlocking(false);
          socketChannel.register(selector, SelectionKey.OP_READ);

          iterator.remove();

          System.out.println("获取客户端连接:" + socketChannel);
        } else if (selectionKey.isReadable()) {
          SocketChannel channel = (SocketChannel) selectionKey.channel();

          ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5);
          int read;

          while (true) {
            byteBuffer.clear();
            read = channel.read(byteBuffer);
            byteBuffer.flip();
            channel.write(byteBuffer);
            if (read <= 0) {
              break;
            }
          }

          iterator.remove();

          System.out.println("获取客户端数据:" + byteBuffer.toString());
        }
      }
    }
  }
}
