package com.hejianzhang.zerocode.core.domain;


import com.hejianzhang.zerocode.core.domain.TestMappings;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable( value = TestMappings.class )
public @interface TestMapping {
    Class<?> testClass();
    String testMethod();
}