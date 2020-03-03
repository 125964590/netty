package top.zhengyiwoaini.netty.zl09.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jbzm
 * @date 2019-11-08 10:56
 */
public class OldIOServer {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8899);

    while (true) {
      Socket socket = serverSocket.accept();

      DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

      try {
        byte[] bytes = new byte[4096];
        int i = 0;
        while (dataInputStream.read(bytes, i, bytes.length) != -1) {}

      } catch (Exception ex) {

      }
    }
  }
}
