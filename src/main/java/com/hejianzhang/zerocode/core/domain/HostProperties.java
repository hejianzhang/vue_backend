package com.hejianzhang.zerocode.core.domain;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface HostProperties {

    String host() default "http://localhost";
    int port() default 8080;
    String context() default "";

}