package com.hejianzhang.zerocode.core.engine.assertion;

import com.hejianzhang.zerocode.core.engine.assertion.AssertionReport;
import com.hejianzhang.zerocode.core.engine.assertion.JsonAsserter;

public class FieldHasExactValueAsserter implements JsonAsserter {
    private final String path;
    final Object expected;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public AssertionReport actualEqualsToExpected(Object actualResult) {
        boolean areEqual;

        /*
         * Any number
         */
        if (actualResult instanceof Number && expected instanceof Number) {
            com.hejianzhang.zerocode.core.engine.assertion.NumberComparator comparator = new com.hejianzhang.zerocode.core.engine.assertion.NumberComparator();
            areEqual = comparator.compare((Number) expected, (Number) actualResult) == 0;

        }
        /*
         * Both are null
         */
        else if (actualResult == null && expected == null) {
            areEqual = true;

        }
        /*
         * Any String
         */
        else if (actualResult != null) {
            areEqual = actualResult.equals(expected);

        }
        else {
            areEqual = false;

        }

        return areEqual ?
                AssertionReport.createFieldMatchesReport() :
                AssertionReport.createFieldDoesNotMatchReport(path, expected, actualResult);
    }

    public FieldHasExactValueAsserter(String path, Object expected) {
        this.path = path;
        this.expected = expected;
    }

}
