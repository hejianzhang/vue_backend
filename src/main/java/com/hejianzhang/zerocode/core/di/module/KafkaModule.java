package com.hejianzhang.zerocode.core.di.module;//package com.hejianzhang.zerocode.core.di.module;
//
//import com.google.inject.Binder;
//import com.google.inject.Module;
//import com.hejianzhang.zerocode.core.di.provider.KafkaServicesProvider;
//import com.hejianzhang.zerocode.core.kafka.KafkaService;
//
//import javax.inject.Singleton;
//
//public class KafkaModule implements Module {
//
//    public void configure(Binder binder) {
//        binder.bind(KafkaService.class).toProvider(KafkaServicesProvider.class).in(Singleton.class);
//    }
//}