package org.jenkinsci.plugins.testresultsanalyzer.result.info;

import org.junit.Test;

import hudson.tasks.junit.CaseResult;
import hudson.tasks.junit.ClassResult;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.ClassResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.ResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.TestCaseResultData;

import static org.junit.Assert.*;

import java.util.*;

public class ClassInfoTest{
	@Test
	public void testSetTotalFlakyOneFlaky(){
		int buildNum = 1;
		int numNonFlaky = 1;
		int numFlaky = 1;
		ClassInfo classInfo = createClassInfo( buildNum, numNonFlaky, numFlaky );
		
		assertTrue( classInfo.countFlakyFromChildren(buildNum) ==  1);
	}
	
	@Test
	public void testSetTotalFlakyNoTestCase(){
		int buildNum = 1;
		int numNonFlaky = 0;
		int numFlaky = 0;
		ClassInfo classInfo = createClassInfo( buildNum, numNonFlaky, numFlaky );
		
		assertTrue( classInfo.countFlakyFromChildren(buildNum) ==  0);
	}
	
	@Test
	public void testSetTotalFlakyBuildNumber(){
		int buildNum = 1;
		int numNonFlaky = 1;
		int numFlaky = 1;
		ClassInfo classInfo = createClassInfo( buildNum, numNonFlaky, numFlaky );

		assertTrue( classInfo.countFlakyFromChildren( buildNum + 1 ) ==  0);
	}

	private ClassInfo createClassInfo( int buildNumber, int numNonFlaky, int numFlaky ){
		Map<String,TestCaseInfo> caseInfos = new TreeMap<String,TestCaseInfo>();
		
		for ( Integer i=0; i < numNonFlaky; i++ ) {
			TestCaseInfo nonFlakyCase = createCaseInfo(buildNumber, 1, 0);
			caseInfos.put("case"+i.toString(), nonFlakyCase);	
		}
		
		for ( Integer i=0; i < numNonFlaky; i++ ) {
			TestCaseInfo FlakyCase = createCaseInfo(buildNumber, 0, 1);
			caseInfos.put("case"+i.toString(), FlakyCase);	
		}
		
		
		ClassInfo classInfo = new ClassInfo();
		classInfo.setTests(caseInfos);

		return classInfo;
	}
	
	private Map<Integer, ResultData> createBuildPackageResults(int buildNumber, int numNonFlaky, int numFlaky){
		Map<Integer, ResultData> buildPackageResults = new HashMap<Integer, ResultData>();
		TestCaseResultData caseResult = new TestCaseResultData();
		
		for(int i=0; i < numNonFlaky; i++){
			caseResult.setFlaky(false);
			caseResult.setTotalFlaky(0);
			buildPackageResults.put(buildNumber, caseResult);
		}
		
		for(int i=0; i < numFlaky; i++){
			caseResult.setFlaky(true);
			caseResult.setTotalFlaky(1);
			buildPackageResults.put(buildNumber, caseResult);
		}
			
		return buildPackageResults;
	}
	
	private TestCaseInfo createCaseInfo(int buildNumber, int numNonFlaky, int numFlaky){
		TestCaseInfo caseInfo = new TestCaseInfo();
		Map<Integer, ResultData> buildResults = createBuildPackageResults(buildNumber, numNonFlaky, numFlaky);
		caseInfo.setBuildPackageResults(buildResults);		
		return caseInfo;
	}
	
}
