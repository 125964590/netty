package top.zhengyiwoaini.netty.zl08.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 编解码
 *
 * 将磁盘上的一个文件转换成字节或者字节数组
 *
 * @author jbzm
 * @date 2019-11-05 16:41
 */
public class NioTest10 {
  public static void main(String[] args) throws IOException {
    String inputFile = "netty-zl-08-nio/NioTest10_In.text";
    String outputFile = "netty-zl-08-nio/NioTest10_Out.text";

    RandomAccessFile inputRandom = new RandomAccessFile(inputFile, "r");
    RandomAccessFile outputRandom = new RandomAccessFile(outputFile, "rw");

    long fileLength = new File(inputFile).length();

    FileChannel inputRandomChannel = inputRandom.getChannel();
    FileChannel outputRandomChannel = outputRandom.getChannel();

    MappedByteBuffer mappedByteBuffer =
        inputRandomChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);

    Charset charset = Charset.forName("iso-8859-1");
    Charset charsetU8 = Charset.forName("utf-8");

    CharsetEncoder u8Encode = charsetU8.newEncoder();
    CharsetDecoder u8Decode = charsetU8.newDecoder();

    CharsetDecoder charsetDecoder = charset.newDecoder();
    CharsetEncoder charsetEncoder = charset.newEncoder();

    CharBuffer u8Buffer = charsetDecoder.decode(mappedByteBuffer);
    //    CharBuffer charBuffer = charsetDecoder.decode(mappedByteBuffer);
    ByteBuffer outputBuffer = u8Encode.encode(u8Buffer);

    outputRandomChannel.write(outputBuffer);

    inputRandom.close();
    outputRandom.close();
  }
}
