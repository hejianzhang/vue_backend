package com.hejianzhang.zerocode.core.kafka.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import com.hejianzhang.zerocode.core.di.provider.ObjectMapperProvider;
import com.hejianzhang.zerocode.core.kafka.consume.ConsumerLocalConfigs;
import com.hejianzhang.zerocode.core.kafka.receive.ConsumerCommonConfigs;
import com.hejianzhang.zerocode.core.kafka.receive.message.ConsumerJsonRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.time.Duration.ofMillis;
import static com.hejianzhang.zerocode.core.kafka.KafkaConstants.JSON;
import static com.hejianzhang.zerocode.core.kafka.KafkaConstants.RAW;
import static com.hejianzhang.zerocode.core.kafka.helper.KafkaConsumerHelper.*;
import static com.hejianzhang.zerocode.core.kafka.helper.KafkaFileRecordHelper.handleRecordsDump;

@Singleton
public class KafkaReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.hejianzhang.zerocode.core.kafka.receive.KafkaReceiver.class);

    private final ObjectMapper objectMapper = new ObjectMapperProvider().get();

    @Inject(optional = true)
    @Named("kafka.consumer.properties")
    private String consumerPropertyFile;

    @Inject
    private ConsumerCommonConfigs consumerCommonConfigs;

    public String receive(String kafkaServers, String topicName, String requestJsonWithConfig) throws IOException {

        ConsumerLocalConfigs consumerLocalConfigs = readConsumerLocalTestProperties(requestJsonWithConfig);

        ConsumerLocalConfigs effectiveLocal = deriveEffectiveConfigs(consumerLocalConfigs, consumerCommonConfigs);

        LOGGER.info("\n### Kafka Consumer Effective configs:{}\n", effectiveLocal);

        Consumer consumer = createConsumer(kafkaServers, consumerPropertyFile, topicName);

        final List<ConsumerRecord> rawRecords = new ArrayList<>();
        final List<ConsumerJsonRecord> jsonRecords = new ArrayList<>();

        int noOfTimeOuts = 0;

        handleSeekOffset(effectiveLocal, consumer);

        while (true) {
            LOGGER.info("polling records  - noOfTimeOuts reached : " + noOfTimeOuts);

            final ConsumerRecords records = consumer.poll(ofMillis(getPollTime(effectiveLocal)));

            if (records.count() == 0) {
                noOfTimeOuts++;
                if (noOfTimeOuts > getMaxTimeOuts(effectiveLocal)) {
                    break;
                } else {
                    continue;
                }
            } else {
                LOGGER.info("Got {} records after {} timeouts\n", records.count(), noOfTimeOuts);
                // -----------------------------------
                // reset after it fetched some records
                // -----------------------------------
                noOfTimeOuts = 0;
            }

            if (records != null) {
                Iterator recordIterator = records.iterator();

                LOGGER.info("Consumer chosen recordType: " + effectiveLocal.getRecordType());

                switch (effectiveLocal.getRecordType()) {
                    case RAW:
                        readRaw(rawRecords, recordIterator);
                        break;

                    case JSON:
                        readJson(jsonRecords, recordIterator);
                        break;

                    default:
                        throw new RuntimeException("Unsupported record type - '" + effectiveLocal.getRecordType()
                                + "'. Supported values are 'JSON','RAW'");
                }

            }

            handleCommitSyncAsync(consumer, consumerCommonConfigs, effectiveLocal);
        }

        consumer.close();

        handleRecordsDump(effectiveLocal, rawRecords, jsonRecords);

        return prepareResult(effectiveLocal, jsonRecords, rawRecords);

    }

}
