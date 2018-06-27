
package com.wenniuwuren.rocketmq.simple;

import org.apache.rocketmq.common.message.Message;


import com.wenniuwuren.Constants;

/**
 * @author zhuyibin
 */
public class ProducerTest {

    public static void main(String[] args) {

        String mqNameServer = Constants.ROCKETMQ_HOST_HOME;
        String mqTopics = "test-topic";

        String producerMqGroupName = "test-group";
        SyncProducer mqProducer = new SyncProducer(mqNameServer, producerMqGroupName, mqTopics);
        mqProducer.init();


        for (int i = 0; i < 5; i++) {

            Message message = new Message();
            message.setBody(("I send message to RocketMQ " + i).getBytes());
            mqProducer.send(message);
        }



    }

}