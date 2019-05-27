package com.hejianzhang.zerocode.core.engine.assertion;

public class AssertionReport {

    private final String path;
    private final Object expected;
    private final Object actual;

    private AssertionReport(String path, Object expected, Object actual) {
        this.path = path;
        this.expected = expected;
        this.actual = actual;

    }

    public boolean matches() {
        /*
         * For SUCCESS no need of sending the path. Framework is only concerned about failures i.e. un-matching values.
         */
        return path == null;
    }


    public static com.hejianzhang.zerocode.core.engine.assertion.AssertionReport createFieldMatchesReport() {
        return new com.hejianzhang.zerocode.core.engine.assertion.AssertionReport(null,null,null);
    }

    public static com.hejianzhang.zerocode.core.engine.assertion.AssertionReport createFieldDoesNotMatchReport(String path, Object expected, Object actual) {
        return new com.hejianzhang.zerocode.core.engine.assertion.AssertionReport(path, expected, actual);
    }

    @Override
    public String toString() {
        return String.format("Assertion path '%s' with actual value '%s' did not match the expected value '%s'", path, actual, expected);
    }
}
