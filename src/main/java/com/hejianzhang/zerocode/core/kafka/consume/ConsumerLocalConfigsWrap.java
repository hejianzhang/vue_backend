package com.hejianzhang.zerocode.core.kafka.consume;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hejianzhang.zerocode.core.kafka.consume.ConsumerLocalConfigs;

import java.util.Objects;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerLocalConfigsWrap {
    private final ConsumerLocalConfigs consumerLocalConfigs;

    @JsonCreator
    public ConsumerLocalConfigsWrap(@JsonProperty("consumerLocalConfigs") ConsumerLocalConfigs consumerLocalConfigs) {
        this.consumerLocalConfigs = consumerLocalConfigs;
    }

    public ConsumerLocalConfigs getConsumerLocalConfigs() {
        return consumerLocalConfigs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.hejianzhang.zerocode.core.kafka.consume.ConsumerLocalConfigsWrap that = (com.hejianzhang.zerocode.core.kafka.consume.ConsumerLocalConfigsWrap) o;
        return Objects.equals(consumerLocalConfigs, that.consumerLocalConfigs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerLocalConfigs);
    }

    @Override
    public String toString() {
        return "ConsumerLocalConfigsWrap{" +
                "consumerLocalConfigs=" + consumerLocalConfigs +
                '}';
    }
}
