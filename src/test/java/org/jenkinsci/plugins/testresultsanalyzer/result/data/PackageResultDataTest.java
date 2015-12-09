package org.jenkinsci.plugins.testresultsanalyzer.result.data;

import org.junit.Test;
import hudson.tasks.junit.ClassResult;
import static org.junit.Assert.*;
import java.util.*;

public class PackageResultDataTest {
	@Test
	public void testSetFlaky_nonFlaky(){
		PackageResultData resultData = new PackageResultData();
		resultData.setFlaky(false);
		assertFalse( resultData.isFlaky() );
	}
	
	@Test
	public void testSetFlaky_flaky(){
		PackageResultData resultData = new PackageResultData();
		resultData.setFlaky(true);
		assertTrue( resultData.isFlaky() );
	}
	
	@Test
	public void testSetTotalFlaky(){
		PackageResultData resultData = new PackageResultData();
		resultData.setTotalFlaky(10);
		assertTrue( resultData.getTotalFlaky() == 10 );
	}
}
