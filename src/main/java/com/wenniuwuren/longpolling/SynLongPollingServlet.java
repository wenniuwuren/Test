package com.wenniuwuren.longpolling;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 服务端（同步 hold 住请求）
 * 同步servlet，性能较差，能支撑的连接数比较少.所有的请求操作必须在doGet方法中完成，
 * 包括等待数据，占用了容器的处理线程，会导致后续的请求阻塞，来不及处理。
 * @author wenniuwuren
 */
@WebServlet(urlPatterns = "/synlongPolling") // Servelet3.0注解
public class SynLongPollingServlet extends HttpServlet{

    private Random random = new Random();

    private AtomicLong sequenceId = new AtomicLong();

    private AtomicLong count = new AtomicLong();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("第" + (count.incrementAndGet()) + "次 longpolling");

        //由于客户端设置的超时时间是50s，
        //为了更好的展示长轮询，这边random 100，模拟服务端hold住大于50和小于50的情况。
        //再具体场景中，这块在具体实现上，
        //对于同步servlet，首先这里必须阻塞，因为一旦doGet方法走完，容器就认为可以结束这次请求，返回结果给客户端。
        //所以一般实现如下：
        // while(结束){ //结束条件，超时或者拿到数据
        //    data = fetchData();
        //    if(data == null){
        //       sleep();
        //    }
        // }
        int sleepSecends = random.nextInt(100);//随机获取等待时间，来通过sleep模拟服务端是否准备好数据

        try {
            System.out.println("wait " + sleepSecends + " second");
            TimeUnit.SECONDS.sleep(sleepSecends);//sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        long value = sequenceId.getAndIncrement();
        out.write(Long.toString(value)); // 数据已准备好，就写回客户端
        out.flush();
    }

}