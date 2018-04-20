package com.wenniuwuren.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;


import com.wenniuwuren.Constants;

public class KafkaProducerExample {

    public static final String TOPIC = "test";

    static Producer<String, String> producer;

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", Constants.KAFKA_HOST);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    public static void main(String[] args) {

        try {
            for(int i = 0; i < 100; i++) {
                System.out.println(i);
                Future<RecordMetadata> recordMetadataFuture =  producer.send(
                        new ProducerRecord<>(TOPIC, Integer.toString(i), "message-" +Integer.toString(i)));
                RecordMetadata metadata = recordMetadataFuture.get();
                System.out.println(RecordMetadataConvertor.metaDateToString(metadata));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

    }
}