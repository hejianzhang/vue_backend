package com.hejianzhang.zerocode.core.di.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.hejianzhang.zerocode.core.httpclient.BasicHttpClient;

public class RuntimeHttpClientModule implements Module {

    private final Class<? extends BasicHttpClient> customerHttpClientClazz;

    public RuntimeHttpClientModule(Class<? extends BasicHttpClient> customerHttpClientClazz) {
        this.customerHttpClientClazz = customerHttpClientClazz;
    }

    public void configure(Binder binder) {
        binder.bind(BasicHttpClient.class).to(customerHttpClientClazz);
    }
}