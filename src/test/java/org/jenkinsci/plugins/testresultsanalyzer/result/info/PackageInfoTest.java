package org.jenkinsci.plugins.testresultsanalyzer.result.info;

import org.junit.Test;

import hudson.tasks.junit.ClassResult;
import hudson.tasks.junit.PackageResult;
import net.sf.json.JSONObject;

import org.jenkinsci.plugins.testresultsanalyzer.result.data.ClassResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.PackageResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.ResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.TestCaseResultData;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PackageInfoTest {	

	@Test
	public void testSetTotalFlakySixFlaky(){
		Map<String, ClassInfo> classes = new TreeMap<String, ClassInfo>();
		int buildNum = 1;
		
		classes.put("1", createClassInfo(buildNum, 1) );
		classes.put("2", createClassInfo(buildNum, 2));
		classes.put("3", createClassInfo(buildNum, 3));
		
		PackageInfo packageInfo = createPackageInfo(classes);
		
		assertTrue( packageInfo.countFlakyFromChildren( buildNum ) ==  6);
	}
	
	@Test
	public void testSetTotalFlakyBuildNum(){
		Map<String, ClassInfo> classes = new TreeMap<String, ClassInfo>();
		int buildNum = 1;
		
		classes.put("1", createClassInfo(buildNum, 1));
		classes.put("2", createClassInfo(buildNum, 2));
		classes.put("3", createClassInfo(buildNum, 3));
		
		PackageInfo packageInfo = createPackageInfo(classes);
		
		assertTrue( packageInfo.countFlakyFromChildren( buildNum + 1 ) ==  0);
	}
	
	@Test
	public void testSetTotalFlakyEmptyClass(){
		Map<String, ClassInfo> classes = new TreeMap<String, ClassInfo>();
		int buildNum = 1;
		
		PackageInfo packageInfo = createPackageInfo(classes);
		
		assertTrue( packageInfo.countFlakyFromChildren( buildNum ) ==  0);
	}
	
	private Map<Integer, ResultData> createBuildPackageResults(int buildNumber, int numFlaky){
		Map<Integer, ResultData> buildPackageResults = new HashMap<Integer, ResultData>();
		ClassResultData classResult = new ClassResultData();
		
		classResult.setTotalFlaky(numFlaky);
		if(numFlaky > 0)
			classResult.setFlaky(true);
		else
			classResult.setFlaky(false);
		
		buildPackageResults.put(buildNumber, classResult);
			
		return buildPackageResults;
	}
	
	private PackageInfo createPackageInfo( Map<String, ClassInfo> classes ){
		PackageInfo packageInfo = new PackageInfo();
		packageInfo.setClasses(classes);
		return packageInfo;
	}

	private ClassInfo createClassInfo(int buildNumber, int numFlaky){
		ClassInfo classInfo = new ClassInfo();
		Map<Integer, ResultData> buildResults = createBuildPackageResults(buildNumber, numFlaky);
		classInfo.setBuildPackageResults(buildResults);		
		return classInfo;
	}
}
