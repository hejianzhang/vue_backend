package com.hejianzhang.zerocode.core.engine.assertion;

import com.hejianzhang.zerocode.core.engine.assertion.AssertionReport;
import com.hejianzhang.zerocode.core.engine.assertion.JsonAsserter;

public class FieldIsNullAsserter implements JsonAsserter {
    private final String path;

    public FieldIsNullAsserter(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public AssertionReport actualEqualsToExpected(Object result) {
        return result == null ?
                AssertionReport.createFieldMatchesReport() :
                AssertionReport.createFieldDoesNotMatchReport(path, "NULL", result);
    }
}
