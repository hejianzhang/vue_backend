package com.hejianzhang.zerocode.core.kafka.common;

import java.util.Properties;

import static com.hejianzhang.zerocode.core.utils.TokenUtils.resolveKnownTokens;

public class KafkaCommonUtils {

    public static void resolveValuePlaceHolders(Properties properties) {
        properties.keySet().forEach(key -> {
            String value = properties.getProperty(key.toString());
            String resolvedValue = resolveKnownTokens(value);
            if(!value.equals(resolvedValue)){
                properties.put(key, resolvedValue);
            }
        });
    }
}
