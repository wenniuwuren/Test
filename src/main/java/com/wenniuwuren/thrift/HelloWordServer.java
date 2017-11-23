package com.wenniuwuren.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;


import com.wenniuwuren.thrift.service.HelloWordService;
import com.wenniuwuren.thrift.service.impl.HelloWordServiceImpl;

public class HelloWordServer {
    public static void main(String[] args) throws Exception {
        TServerSocket socket = new TServerSocket(9999);
        HelloWordService.Processor processor = new HelloWordService.Processor(new HelloWordServiceImpl());
        TServer server = new TSimpleServer(new TServer.Args(socket).processor(processor));
        System.out.println("Running server...");
        server.serve();
    }
}

