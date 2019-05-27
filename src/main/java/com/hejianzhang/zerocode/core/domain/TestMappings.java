package com.hejianzhang.zerocode.core.domain;


import com.hejianzhang.zerocode.core.domain.TestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMappings {
    public TestMapping[] value() default {};
}