package com.hejianzhang.zerocode.core.domain.builders;

import com.hejianzhang.zerocode.core.domain.reports.chart.HighChartColumnHtml;

public class HighChartColumnHtmlBuilder {
    String pageTitle;
    String testResult;
    String chartTitleTop;
    String textYaxis;
    String chartSeriesName;
    String chartTitleTopInABox;

    public static com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder newInstance() {
        return new com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder();
    }

    public HighChartColumnHtml build() {
        HighChartColumnHtml built = new HighChartColumnHtml(pageTitle, testResult, chartTitleTop,
                textYaxis, chartSeriesName, chartTitleTopInABox);

        return built;
    }

    public com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder pageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder testResult(String testResult) {
        this.testResult = testResult;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder chartTitleTop(String chartTitleTop) {
        this.chartTitleTop = chartTitleTop;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder textYaxis(String textYaxis) {
        this.textYaxis = textYaxis;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder chartSeriesName(String chartSeriesName) {
        this.chartSeriesName = chartSeriesName;
        return this;
    }

    public com.hejianzhang.zerocode.core.domain.builders.HighChartColumnHtmlBuilder chartTitleTopInABox(String chartTitleTopInABox) {
        this.chartTitleTopInABox = chartTitleTopInABox;
        return this;
    }
}
