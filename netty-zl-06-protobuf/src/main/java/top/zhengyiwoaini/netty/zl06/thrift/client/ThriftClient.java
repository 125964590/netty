package top.zhengyiwoaini.netty.zl06.thrift.client;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import top.zhengyiwoaini.netty.zl06.thrift.generated.Person;
import top.zhengyiwoaini.netty.zl06.thrift.generated.PersonService;

public class ThriftClient {
    public static void main(String[] args) {
        //这些与Server的进行对应，跟网络七层协议相似，两边顺序是反的
        TTransport transport=new TFastFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol=new TCompactProtocol(transport);
        PersonService.Client client=new PersonService.Client(protocol);

        try {
            //开启网络传输
            transport.open();
            /**
             * 关键：client本来就没有getPersonByUsername方法，这是通过网络传输调用
             */
            Person person=client.getPersonByUsername("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            //对于boolean型，不是get，而是is开头.但是set都一样
            System.out.println(person.isMarried());
            System.out.println("---------------");
            Person person1=new Person().setUsername("李四").setAge(20).setMarried(true);
            client.savePerson(person1);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);
        }finally {
            //最后关闭transport
            transport.close();
        }
    }
}