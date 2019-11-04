package top.zhengyiwoaini.netty.zl08.nio;

import io.netty.channel.ServerChannel;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author jbzm
 * @date 2019-10-31 18:14
 */
public class NioServer {
  public static void main(String[] args) throws IOException {
    Map<String, SocketChannel> channelMap = new HashMap<>(8);
    Selector selector = Selector.open();
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false);
    ServerSocket socket = serverSocketChannel.socket();
    socket.bind(new InetSocketAddress(8899));
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      selectionKeys.forEach(
          key -> {
            if (key.isAcceptable()) {
              ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
              try {
                SocketChannel channel = socketChannel.accept();
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
                channelMap.put(UUID.randomUUID().toString(), channel);
                System.out.println("server register:" + socketChannel);
              } catch (IOException e) {
                e.printStackTrace();
              }
            } else if (key.isReadable()) {
              SocketChannel channel = (SocketChannel) key.channel();
              ByteBuffer byteBuffer = ByteBuffer.allocate(128);
              try {
                channel.read(byteBuffer);
                byteBuffer.flip();
                Charset charset = Charset.forName("UTF-8");
                System.out.println("message:" + charset.decode(byteBuffer));
                channelMap.forEach(
                    (x, y) -> {
                      try {
                        byteBuffer.position(0);
                        y.write(byteBuffer);
                      } catch (IOException e) {
                        e.printStackTrace();
                      }
                    });
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
            selectionKeys.remove(key);
          });
    }
  }
}
