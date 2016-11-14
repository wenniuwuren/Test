package com.wenniuwuren.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 消息生产者
 */
public class Producer {
    //队列名称  
    private final static String QUEUE_NAME = "RabbitMQDurableTest";

    // 发送数次
    private final static int sendNumbers = 100;

    public static void main(String[] argv) throws Exception {

        /** 
         * 创建连接连接到MabbitMQ 
         */  
        ConnectionFactory factory = new ConnectionFactory();  
        // 设置 RabbitMQ 所在主机 ip 或者主机名
        factory.setHost("localhost");  
        // 创建一个连接
        Connection connection = factory.newConnection();  
        // 创建一个频道
        Channel channel = connection.createChannel();  
        // 当RabbitMQ退出或者异常退出，将会丢失所有的队列和信息，除非开启持久化。持久化给定队列
        // 1. 指定队列为持久化  boolean durable = true;
        // 2. 消息为持久消息 MessageProperties.PERSISTENT_TEXT_PLAIN
        boolean durable = true;
        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        //发送的消息  

        // 往队列中发出一条消息
        for (int i = 0; i < sendNumbers; i++) {
            String message = "hello RabbitMQ " + i;
            // 配合 boolean durable = true; 持久化消息标志 MessageProperties.PERSISTENT_TEXT_PLAIN
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("Sent " + message);
        }

        // 关闭频道和连接
        channel.close();  
        connection.close();  
     }  
} 