package com.hejianzhang.zerocode.core.di.provider;

import com.hejianzhang.zerocode.core.httpclient.BasicHttpClient;
import com.hejianzhang.zerocode.core.httpclient.ssl.SslTrustHttpClient;

import javax.inject.Provider;

public class DefaultGuiceHttpClientProvider implements Provider<BasicHttpClient> {

    @Override
    public BasicHttpClient get() {

        BasicHttpClient client = new SslTrustHttpClient();

        return client;
    }

}