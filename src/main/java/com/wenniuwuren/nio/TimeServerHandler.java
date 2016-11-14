package com.wenniuwuren.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by hzzhuyibin on 2016/8/6.
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {

        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            out = new PrintWriter(this.socket.getOutputStream(), true);

            String currentTime = null;
            String body = null;

            while (true) {
                // read one line
                body = in.readLine();
                if (body == null) {
                    break;
                }

                System.out.println("the time server receive order:" + body);

                currentTime = System.currentTimeMillis() + "";

                // 发送给客户端
                out.println(currentTime);


            }
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
                out = null;
            }

            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }


        }
    }
}

