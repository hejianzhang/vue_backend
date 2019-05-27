package com.hejianzhang.zerocode.core.domain.builders;

import com.hejianzhang.zerocode.core.domain.reports.chart.ZeroCodeChartKeyValue;

public class ZeroCodeChartKeyValueBuilder {

    String key;
    Double value;
    String result;

    public static com.hejianzhang.zerocode.core.domain.builders.ZeroCodeChartKeyValueBuilder newInstance() {
        return new com.hejianzhang.zerocode.core.domain.builders.ZeroCodeChartKeyValueBuilder();
    }

    public ZeroCodeChartKeyValue build() {
        ZeroCodeChartKeyValue built = new ZeroCodeChartKeyValue(key, value, result);
        return built;
    }


    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeChartKeyValueBuilder key(String key) {
        this.key = key;
        return  this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeChartKeyValueBuilder value(Double value) {
        this.value = value;
        return  this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.ZeroCodeChartKeyValueBuilder result(String result) {
        this.result = result;
        return  this;
    }
}
