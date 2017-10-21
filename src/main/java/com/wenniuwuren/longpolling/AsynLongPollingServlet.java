package com.wenniuwuren.longpolling;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;



/**
 * 异步Servelt处理 LongPolling 请求
 * servlet 3.0支持异步处理，使用异步处理doGet方法执行完成后，结果也不会返回到客户端，会等到请求的context被complete
 * 才会写回客户端，这样一来，容器的处理线程不会受阻，请求结果可由另外的业务线程进行写回，也就轻松实现了hold操作。
 * @author wenniuwuren
 */
@WebServlet(urlPatterns = "/asynLongPolling", asyncSupported = true) // Servlet3.0 开启异步
public class AsynLongPollingServlet extends HttpServlet{

    private Random random = new Random();

    private final AtomicLong sequence = new AtomicLong();

    private final AtomicLong value = new AtomicLong();

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000));


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        final long currentSequence = sequence.incrementAndGet();

        System.out.println("第" + (currentSequence) + "次 async long polling");

        // 异步处理:在子线程中执行业务调用，并由其负责输出响应，主线程退出
        AsyncContext asyncContext = request.startAsync();

        // 如果超时，异步处理没有完成（通过是否asyncContext.complete()来进行判断），将会重试（会再次调用doGet方法）。
        // 这里由于客户端long polling设置的是50s，所以这里如果小于50，会导致重试。
        asyncContext.setTimeout(51000);

        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {

            }

            // 超时处理，注意asyncContext.complete();，表示请求处理完成
            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                AsyncContext asyncContext = asyncEvent.getAsyncContext();
                asyncContext.complete();
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

            }
        });

        // 提交线程池异步写会结果
        // 具体场景中可以有具体的策略进行操作
        executor.submit(new HandlePollingTask(currentSequence, asyncContext));
    }

    class HandlePollingTask implements Runnable {

        private AsyncContext asyncContext;

        private long sequense;

        public HandlePollingTask(long sequense, AsyncContext asyncContext) {
            this.sequense = sequense;
            this.asyncContext = asyncContext;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = asyncContext.getResponse().getWriter();

                int sleepSecond = random.nextInt(100);

                System.out.println(sequense + " wait " + sleepSecond + " second");

                Thread.sleep(sleepSecond * 1000);

                long result = value.getAndIncrement();

                out.write(Long.toString(result));
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //数据写回客户端
                asyncContext.complete();
            }
        }
    }

}