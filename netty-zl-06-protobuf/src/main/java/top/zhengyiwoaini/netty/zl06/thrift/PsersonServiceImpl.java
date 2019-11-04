package top.zhengyiwoaini.netty.zl06.thrift;

import org.apache.thrift.TException;
import top.zhengyiwoaini.netty.zl06.thrift.generated.DataException;
import top.zhengyiwoaini.netty.zl06.thrift.generated.Person;
import top.zhengyiwoaini.netty.zl06.thrift.generated.PersonService;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author jbzm
 * @date 2019-10-16 11:54
 */
public class PsersonServiceImpl implements PersonService.Iface {
  @Override
  public Person getPersonByUsername(String username) throws DataException, TException {
    System.out.println("Got Client Param:" + username);
    Person person = new Person();
    person.setAge(20);
    person.setUsername("bj");
    return person;
  }

  @Override
  public void savePerson(Person person) throws DataException, TException {}

  public static void main(String[] args) throws Exception {
    Mac mac = Mac.getInstance("HmacSHA1");
    SecretKeySpec keySpec = new SecretKeySpec("4317977758073859".getBytes(StandardCharsets.UTF_8), "UTF-8");
    mac.init(keySpec);
    byte[] bytes =
        mac.doFinal(
            "access_key_id=4317977758073859&demo_end_date=2019-02-18T10:55:55&demo_start_date=2019-02-18T10:16:16&request_body={\"demo_param1\":\"pass1\",\"demo_param2\":\"pass2\"}&signature_nonce=3d5bc8b5-0863-4990-aeea-61da3bd48776&timestamp=2019-03-20T19:39:43"
                .getBytes(StandardCharsets.UTF_8));
    System.out.println(new String(bytes, StandardCharsets.UTF_8));
//    byte[] encode =
//        Base64.getEncoder().encode("7a810a4245534cdab787bd82d0a63ca9".getBytes("UTF-8"));
//    System.out.println(new String(encode, "utf-8"));
  }
}
