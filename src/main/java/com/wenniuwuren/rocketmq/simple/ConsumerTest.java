
package com.wenniuwuren.rocketmq.simple;

import com.wenniuwuren.Constants;

/**
 * @author zhuyibin
 */
public class ConsumerTest {

    public static void main(String[] args) {


        String mqNameServer = Constants.ROCKETMQ_HOST_HOME;
        String mqTopics = "test-topic";

        String consumerMqGroupName = "test-group";
        RocketMQListener mqListener = new RocketMQListener();
        Consumer mqConsumer = new Consumer(mqListener, mqNameServer, consumerMqGroupName, mqTopics);
        mqConsumer.init();


        try {
            Thread.sleep(1000 * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}