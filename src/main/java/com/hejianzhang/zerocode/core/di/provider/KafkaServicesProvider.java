package com.hejianzhang.zerocode.core.di.provider;//package com.hejianzhang.zerocode.core.di.provider;
//
//import com.hejianzhang.zerocode.core.kafka.KafkaService;
//import com.hejianzhang.zerocode.core.kafka.KafkaServiceImpl;
//
//import javax.inject.Provider;
//
//public class KafkaServicesProvider implements Provider<KafkaService> {
//
//    @Override
//    public KafkaService get() {
//
//        KafkaService kafkaService = new KafkaServiceImpl();
//        // Read the properties etc
//
//        return kafkaService;
//    }
//
//}