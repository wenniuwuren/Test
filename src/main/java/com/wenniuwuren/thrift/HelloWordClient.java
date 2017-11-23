package com.wenniuwuren.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


import com.wenniuwuren.thrift.service.HelloWordService;
import com.wenniuwuren.thrift.service.Request;
import com.wenniuwuren.thrift.service.RequestType;

public class HelloWordClient {
    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 9999);
        TProtocol protocol = new TBinaryProtocol(transport);

        // 创建client
        HelloWordService.Client client = new HelloWordService.Client(protocol);

        transport.open();  // 建立连接

        // 第一种请求类型
        Request request = new Request()
                .setType(RequestType.SAY_HELLO).setName("wenniuwuren").setAge(24);
        System.out.println(client.doAction(request));

        // 第二种请求类型
        request.setType(RequestType.QUERY_TIME).setName("wenniuwuren");
        System.out.println(client.doAction(request));

        transport.close();  // 请求结束，断开连接
    }
}

