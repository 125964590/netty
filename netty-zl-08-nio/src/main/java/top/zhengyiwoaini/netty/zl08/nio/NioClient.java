package top.zhengyiwoaini.netty.zl08.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * 使用NIO建立一个客户端
 *
 * @author jbzm
 * @date 2019-11-05 12:11
 */
public class NioClient {
  public static void main(String[] args) throws IOException {
    Selector selector = Selector.open();
    // 创建一个SocketChannel
    SocketChannel socketChannel = SocketChannel.open();
    // 开启异步
    socketChannel.configureBlocking(false);
    // 注册
    socketChannel.register(selector, SelectionKey.OP_CONNECT);
    // 这里需要注意  与服务端不同的是 connect
    socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

    while (true) {
      selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();

      selectionKeys.forEach(
          key -> {
            // 处理连接时间
            if (key.isConnectable()) {
              ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
              try {
                SocketChannel channel = (SocketChannel) key.channel();
                // 处理连接等待
                if (channel.isConnectionPending()) {
                  // 完成连接请求  必须手动执行与服务端完成连接建立
                  channel.finishConnect();

                  byteBuffer.put("服务连接成功~~".getBytes());
                  byteBuffer.flip();
                  channel.write(byteBuffer);
                  // 单独开启一个线程去接受控制台的输入,并且将输入发送给服务端
                  Executors.newSingleThreadExecutor()
                      .execute(
                          () -> {
                            while (true) {
                              try {
                                byteBuffer.clear();
                                InputStreamReader inputStreamReader =
                                    new InputStreamReader(System.in);
                                BufferedReader bufferedReader =
                                    new BufferedReader(inputStreamReader);
                                byteBuffer.put(bufferedReader.readLine().getBytes());
                                byteBuffer.flip();
                                channel.write(byteBuffer);
                              } catch (IOException e) {
                                e.printStackTrace();
                              }
                            }
                          });
                }
                // 注册读请求
                channel.register(selector, SelectionKey.OP_READ);
              } catch (IOException e) {
                e.printStackTrace();
              }
            } else if (key.isReadable()) {
              try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                SocketChannel channel = (SocketChannel) key.channel();
                channel.read(byteBuffer);
                System.out.println(new String(byteBuffer.array()));
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
          });
      // 清除
      selectionKeys.clear();
    }
  }
}
