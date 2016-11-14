package com.wenniuwuren.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 发送 query time order ，后读取服务端的响应并将结果打印出来，随后关闭连接，释放资源，程序退出执行
 * Created by hzzhuyibin on 2016/8/7.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("query time order");
            System.out.println("send order 2 server succeed.");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                in = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}

