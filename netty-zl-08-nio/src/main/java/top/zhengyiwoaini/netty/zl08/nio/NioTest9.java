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
 * Selector 选择器
 *
 * <p>选择器内部实际上维护了多个key set 通过{@link SelectionKey}来记录相关的操作,我们所要做的就是不断地遍历{@code Select}然后处理相关的操作即可
 *
 * <p>选择器是NIO中的一起重要组件,这点细节需要去关注NIO的网络模型.
 *
 * <p>在这个例子里展示了如何使用一个线程的{@code Selector}来完成连接处理.
 *
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
