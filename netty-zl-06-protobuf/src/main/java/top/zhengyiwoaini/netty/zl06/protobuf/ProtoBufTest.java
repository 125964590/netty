package top.zhengyiwoaini.netty.zl06.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author jbzm
 * @date 2019-10-12 14:46
 */
public class ProtoBufTest {
  public static void main(String[] args) throws InvalidProtocolBufferException {
    DataInfo.Student jbzm =
        DataInfo.Student.newBuilder().setAddress("123").setName("jbzm").setAge(23).build();
    byte[] bytes = jbzm.toByteArray();
    DataInfo.Student student = DataInfo.Student.parseFrom(bytes);
    System.out.println(student.toBuilder());
  }
}
