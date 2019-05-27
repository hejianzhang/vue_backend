package com.hejianzhang.zerocode.core.di.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.hejianzhang.zerocode.core.kafka.client.BasicKafkaClient;

public class RuntimeKafkaClientModule implements Module {

    private final Class<? extends BasicKafkaClient> customKafkaClientClazz;

    public RuntimeKafkaClientModule(Class<? extends BasicKafkaClient> customKafkaClientClazz) {
        this.customKafkaClientClazz = customKafkaClientClazz;
    }

    public void configure(Binder binder) {
        binder.bind(BasicKafkaClient.class).to(customKafkaClientClazz);
    }
}