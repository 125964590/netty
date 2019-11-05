package top.zhengyiwoaini.netty.zl08.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
 * 使用 NIO构建一个服务端
 *
 * @author jbzm
 * @date 2019-10-31 18:14
 */
public class NioServer {
  public static void main(String[] args) throws IOException {
    // 创建map存放连接过来的channel
    Map<String, SocketChannel> channelMap = new HashMap<>(8);
    // 创建一个选择器
    Selector selector = Selector.open();
    // 创建一个服务端socker连接
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    // 使用异步
    serverSocketChannel.configureBlocking(false);
    // 获取socket
    ServerSocket socket = serverSocketChannel.socket();
    // 绑定主机端口
    socket.bind(new InetSocketAddress(8899));
    // 将自己注册到select上
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      // 获取key set
      selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      // 遍历集合处理相关的操作
      selectionKeys.forEach(
          key -> {
            // 处理请求接受
            if (key.isAcceptable()) {
              // 强制类型转换 这里说明一下强制类型转换根据我们注册的Channel去判断
              ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
              try {
                // 获取去 SocketChannel
                SocketChannel channel = socketChannel.accept();
                channel.configureBlocking(false);
                // 注册读事件
                channel.register(selector, SelectionKey.OP_READ);
                channelMap.put(UUID.randomUUID().toString(), channel);
                System.out.println("server register:" + socketChannel);
              } catch (IOException e) {
                e.printStackTrace();
              }
              // 处理读请求
            } else if (key.isReadable()) {
              // 同上强制类型转换
              SocketChannel channel = (SocketChannel) key.channel();
              // 靠谱块空间
              ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
              try {
                int read = channel.read(byteBuffer);
                if (read > 0) {
                  byteBuffer.flip();
                  Charset charset = Charset.forName("UTF-8");
                  System.out.println("message:" + charset.decode(byteBuffer));
                  String channelkey = null;
                  for (String s : channelMap.keySet()) {
                    if (channelMap.get(s) == channel) {
                      channelkey = s;
                    }
                  }
                  // 下面的逻辑是将消息通知所有连接改服务端的client
                  String message = new String(byteBuffer.array(), 0, 64);
                  message = "[+" + channelkey + "]" + message;
                  byteBuffer.clear();
                  byteBuffer.put(message.getBytes());
                  channelMap.forEach(
                      (x, y) -> {
                        try {
                          byteBuffer.position(0);
                          y.write(byteBuffer);
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                      });
                }
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
          });
      // 这点很重要,将使用过的key清除
      selectionKeys.clear();
    }
  }
}
