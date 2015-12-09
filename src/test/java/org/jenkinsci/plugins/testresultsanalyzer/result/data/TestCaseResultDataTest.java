package org.jenkinsci.plugins.testresultsanalyzer.result.data;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestCaseResultDataTest {
    @Test
    public void testSetFlaky_nonFlaky() {
        TestCaseResultData resultData = new TestCaseResultData();
        resultData.setFlaky(false);
        assertFalse(resultData.isFlaky());
    }

    @Test
    public void testSetFlaky_flaky() {
        TestCaseResultData resultData = new TestCaseResultData();
        resultData.setFlaky(true);
        assertTrue(resultData.isFlaky());
    }

    @Test
    public void testSetTotalFlaky() {
        TestCaseResultData resultData = new TestCaseResultData();
        resultData.setTotalFlaky(10);
        assertTrue(resultData.getTotalFlaky() == 10);
    }

}
