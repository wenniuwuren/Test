package com.wenniuwuren.longpolling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletResponse;


/**
 * 客户端
 * @author wenniuwuren
 */
public class ClientBootstrap {

    //同步URL
    private static final String URL = "http://localhost:8080/synlongPolling";

    //异步URL
    private static final String ASYNC_URL = "http://localhost:8080/asynLongPolling";

    private final AtomicLong count = new AtomicLong();

    public static void main(String[] args) throws IOException {
        ClientBootstrap bootstrap = new ClientBootstrap();
        //发起 long polling
        bootstrap.poll();

        System.in.read();
    }

    protected void poll() {
        //循环执行，保证每次longpolling结束，再次发起longpolling
        while (!Thread.interrupted()) {
            doPoll();
        }
    }

    protected void doPoll() {
        while (true) {
            System.out.println("第" + count.incrementAndGet() + "次 longpolling");

            long startMillis = System.currentTimeMillis();

            HttpURLConnection connection = null;
            try {
                URL getUrl = new URL(ASYNC_URL);
                connection = (HttpURLConnection) getUrl.openConnection();
                connection.setReadTimeout(50000);// 50s 长轮询超时时间
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");
                connection.connect();

                if (HttpServletResponse.SC_OK == connection.getResponseCode()) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                        StringBuilder result = new StringBuilder(256);
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        System.out.println("结果 " + result);

                    } finally {
                        if (reader != null) {
                            reader.close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                long elapsed = (System.currentTimeMillis() - startMillis) / 1000;

                if (connection != null) {
                    connection.disconnect();
                }

                System.out.println("connection close, elapse=" + elapsed + "s");
            }
        }
    }

}