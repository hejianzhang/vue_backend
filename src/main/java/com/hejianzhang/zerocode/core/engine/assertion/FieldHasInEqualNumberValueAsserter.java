package com.hejianzhang.zerocode.core.engine.assertion;

import com.hejianzhang.zerocode.core.engine.assertion.AssertionReport;
import com.hejianzhang.zerocode.core.engine.assertion.JsonAsserter;

public class FieldHasInEqualNumberValueAsserter implements JsonAsserter {
    private final String path;
    private final Number expected;

    public FieldHasInEqualNumberValueAsserter(String path, Number expected) {
        this.path = path;
        this.expected = expected;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public AssertionReport actualEqualsToExpected(Object result) {
        boolean areNotEqual;

        if (result instanceof Number && expected instanceof Number) {
            com.hejianzhang.zerocode.core.engine.assertion.NumberComparator comparator = new com.hejianzhang.zerocode.core.engine.assertion.NumberComparator();
            areNotEqual = comparator.compare((Number) result, (Number) expected) != 0;

        } else if (result == null && expected == null) {
            areNotEqual = false;

        } else if (result == null) {
            areNotEqual = true;

        } else {
            areNotEqual = true;

        }

        return areNotEqual ?
                AssertionReport.createFieldMatchesReport() :
                AssertionReport.createFieldDoesNotMatchReport(path, "not equals to " + expected, result);
    }
}

