package com.hejianzhang.zerocode.core.domain;

import com.hejianzhang.zerocode.core.kafka.client.BasicKafkaClient;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface UseKafkaClient {
    /**
     * @return a Http Client implementation class which will override the default implementation of RestEasy client
     */
    Class<? extends BasicKafkaClient> value();
}