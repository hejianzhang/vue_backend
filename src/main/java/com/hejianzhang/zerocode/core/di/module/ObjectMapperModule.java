package com.hejianzhang.zerocode.core.di.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.hejianzhang.zerocode.core.di.provider.ObjectMapperProvider;

import javax.inject.Singleton;


public class ObjectMapperModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class).in(Singleton.class);
    }
}

