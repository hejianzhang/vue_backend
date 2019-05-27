package com.hejianzhang.zerocode.core.engine.assertion;

import com.hejianzhang.zerocode.core.engine.assertion.AssertionReport;
import com.hejianzhang.zerocode.core.engine.assertion.JsonAsserter;

public class FieldHasEqualNumberValueAsserter implements JsonAsserter {
    private final String path;
    private final Number expected;

    public FieldHasEqualNumberValueAsserter(String path, Number expected) {
        this.path = path;
        this.expected = expected;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public AssertionReport actualEqualsToExpected(Object result) {
        boolean areEqual;

        if (result instanceof Number && expected instanceof Number) {
            com.hejianzhang.zerocode.core.engine.assertion.NumberComparator comparator = new com.hejianzhang.zerocode.core.engine.assertion.NumberComparator();
            areEqual = comparator.compare((Number) result, (Number) expected) == 0;

        } else if (result == null && expected == null) {
            areEqual = true;

        } else if (result == null) {
            areEqual = false;

        } else {
            areEqual = false;

        }

        return areEqual ?
                AssertionReport.createFieldMatchesReport() :
                AssertionReport.createFieldDoesNotMatchReport(path, expected, result);
    }
}

