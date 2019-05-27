package com.hejianzhang.zerocode.core.kafka.client;

import com.google.inject.Inject;
import com.hejianzhang.zerocode.core.kafka.receive.KafkaReceiver;
import com.hejianzhang.zerocode.core.kafka.send.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicKafkaClient {
    private Logger LOGGER = LoggerFactory.getLogger(com.hejianzhang.zerocode.core.kafka.client.BasicKafkaClient.class);

    @Inject
    private KafkaSender sender;

    @Inject
    private KafkaReceiver receiver;


    public BasicKafkaClient() {
    }

    public String execute(String brokers, String topicName, String operation, String requestJson) {
        LOGGER.info("brokers:{}, topicName:{}, operation:{}, requestJson:{}", brokers, topicName, operation, requestJson);

        try {
            switch (operation) {
                case "send":
                case "load":
                case "publish":
                case "produce":
                    return sender.send(brokers, topicName, requestJson);

                case "unload":
                case "consume":
                case "receive":
                case "subscribe":
                    return receiver.receive(brokers, topicName, requestJson);

                case "poll":
                    throw new RuntimeException("poll - Not yet Implemented");

                default:
                    throw new RuntimeException("Unsupported. Framework could not assume a default Kafka operation");
            }

        } catch (Throwable exx) {

            LOGGER.error("Exception during operation:{}, topicName:{}, error:{}", operation, topicName, exx.getMessage());

            throw new RuntimeException(exx);
        }

    }
}
