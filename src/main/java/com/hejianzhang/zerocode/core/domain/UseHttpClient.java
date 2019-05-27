package com.hejianzhang.zerocode.core.domain;

import com.hejianzhang.zerocode.core.httpclient.BasicHttpClient;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface UseHttpClient {
    /**
     * @return a Http Client implementation class which will override the default implementation of RestEasy client
     */
    Class<? extends BasicHttpClient> value();
}