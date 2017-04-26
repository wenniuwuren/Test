package com.wenniuwuren.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消息消费者，阻塞等待消息到来
 */
public class Consumer {
    //队列名称  
    private final static String QUEUE_NAME = "RabbitMQDurableTest";
  
    public static void main(String[] argv) throws Exception {
        // 打开连接和创建频道，与 producer 一样
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();
        boolean durable = true;
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。  
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println("Waiting for the messages...");

        //设置最大服务转发消息数量
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        //创建队列消费者  
        QueueingConsumer consumer = new QueueingConsumer(channel);  

        // RabbitMQ支持消息应答（message acknowledgments）
        // 消费者发送应答给RabbitMQ，告诉它信息已经被接收和处理，
        // 然后RabbitMQ可以自由的进行信息删除。
        boolean autoAck = false; // true 关闭自动应答。 false 打开应答，即使消费者被杀死，消息也不会被丢失，用来保证消息消费成功
        // 指定消费队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        while (true) {
            // nextDelivery 阻塞取数据
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            System.out.println("Received " + message);

            // 配合 autoAck = false 的情况，需要在每次处理完成一个消息，发送一次应答
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }  
  
    }  
}  