
package com.wenniuwuren.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;


public class RecordMetadataConvertor {
    public static String metaDateToString(RecordMetadata recordMetadata) {

        if (recordMetadata == null) {
            return null;
        }

        long offset = recordMetadata.offset();

        int partition = recordMetadata.partition();

        String topic = recordMetadata.topic();

        String ret = "topic=" + topic + ", " + "partition=" + partition + ", " + "offset=" + offset;

        return ret;
    }
}
