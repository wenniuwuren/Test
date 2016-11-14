package com.wenniuwuren.nio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步的 TimeServer
 * 首先创建线程池，当接收到客户端连接时，将请求 Socket 封装成 Task， 然后用线程池执行
 * Created by hzzhuyibin on 2016/8/6.
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("time server is start in" + port);
            Socket socket = null;

            TimeServerHandlerExecutePool timeServerHandlerExecutePool = new TimeServerHandlerExecutePool(50, 10000);

            while (true) {
                socket = serverSocket.accept();
                // socket 封装成 Task （实现 Runnable）
                timeServerHandlerExecutePool.execute(new TimeServerHandler(socket));

            }

        } catch (Exception e) {

        }
    }
}

