package org.jenkinsci.plugins.testresultsanalyzer.result.data;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClassResultDataTest {
    @Test
    public void testSetFlakyNonFlaky() {
        ClassResultData classResultData = new ClassResultData();
        classResultData.setFlaky(false);
        assertFalse(classResultData.isFlaky());
    }

    @Test
    public void testSetFlakyFlaky() {
        ClassResultData classResultData = new ClassResultData();
        classResultData.setFlaky(true);
        assertTrue(classResultData.isFlaky());
    }

    @Test
    public void testSetTotalFlaky() {
        ClassResultData classResultData = new ClassResultData();
        classResultData.setTotalFlaky(10);
        assertTrue(classResultData.getTotalFlaky() == 10);
    }
}
