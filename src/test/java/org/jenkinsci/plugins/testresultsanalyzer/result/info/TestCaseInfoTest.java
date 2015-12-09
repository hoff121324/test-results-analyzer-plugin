package org.jenkinsci.plugins.testresultsanalyzer.result.info;

import hudson.tasks.junit.CaseResult;
import hudson.tasks.junit.TestAction;
import hudson.tasks.test.AbstractTestResultAction;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import org.jenkinsci.plugins.testresultsanalyzer.result.data.TestCaseResultData;

import com.google.jenkins.flakyTestHandler.plugin.JUnitFlakyAggregatedTestDataAction;
import com.google.jenkins.flakyTestHandler.plugin.JUnitFlakyTestDataAction;

import net.sf.json.JSONObject;
import com.google.jenkins.flakyTestHandler.junit.FlakyCaseResult.FlakyRunInformation;
import com.google.jenkins.flakyTestHandler.plugin.JUnitFlakyTestDataAction;

public class TestCaseInfoTest {
	private TestCaseInfo info = new TestCaseInfo();

	@Test
	public void testGetFlakyActions_emptyAction(){
		List<TestAction> testActions = new ArrayList<TestAction>();
		
		Collection<JUnitFlakyTestDataAction> flakyActions = info.getFlakyActions(testActions);
		assertTrue(flakyActions.size() == 0);
	}
	
	@Test
	public void testGetFlakyActions_filterOtherFlakedAction(){
		List<TestAction> testActions = new ArrayList<TestAction>();
		
		JUnitFlakyAggregatedTestDataAction aggreAction = new JUnitFlakyAggregatedTestDataAction(1, 1, 1 );
		testActions.add(aggreAction);
		
		Collection<JUnitFlakyTestDataAction> flakyActions = info.getFlakyActions(testActions);
		assertTrue(flakyActions.size() == 0);
	}
	
	@Test
	public void testGetFlakyActions_oneFlakyAction(){
		List<TestAction> testActions = new ArrayList<TestAction>();
		
		JUnitFlakyAggregatedTestDataAction aggreAction = new JUnitFlakyAggregatedTestDataAction(1, 1, 1 );
		testActions.add(aggreAction);
		
		JUnitFlakyTestDataAction flakyAction = createPassedFlakyAction();
		testActions.add(flakyAction);
		
		Collection<JUnitFlakyTestDataAction> flakyActions = info.getFlakyActions(testActions);
		assertTrue(flakyActions.size() == 1);
	}
	
	@Test
	public void testGetFlakyActions_mixedActions(){
		List<TestAction> testActions = new ArrayList<TestAction>();
		
		JUnitFlakyAggregatedTestDataAction aggreAction = new JUnitFlakyAggregatedTestDataAction(1, 1, 1 );
		testActions.add(aggreAction);
		
		JUnitFlakyTestDataAction flakyAction = createFailedFlakyAction();
		testActions.add(flakyAction);
		
		Collection<JUnitFlakyTestDataAction> flakyActions = info.getFlakyActions(testActions);
		assertTrue(flakyActions.size() == 1);
	}
	
	@Test
	public void testContainFlakyAction_failedAction(){
		Collection<JUnitFlakyTestDataAction> flakyActions = new ArrayList<JUnitFlakyTestDataAction>();
		
		JUnitFlakyTestDataAction flakyAction = createFailedFlakyAction();
		flakyActions.add(flakyAction);
		
		assertFalse( info.containFlakyAction(flakyActions) );
	}
	
	@Test
	public void testContainFlakyAction_passedAction(){
		Collection<JUnitFlakyTestDataAction> flakyActions = new ArrayList<JUnitFlakyTestDataAction>();
		
		JUnitFlakyTestDataAction flakyAction = createPassedFlakyAction();
		flakyActions.add(flakyAction);
		
		assertTrue( info.containFlakyAction(flakyActions) );
	}
	
	@Test
	public void testContainFlakyAction_onePassOneFail(){
		Collection<JUnitFlakyTestDataAction> flakyActions = new ArrayList<JUnitFlakyTestDataAction>();
		
		JUnitFlakyTestDataAction flakyAction = createFailedFlakyAction();
		flakyActions.add(flakyAction);
		
		flakyAction = createPassedFlakyAction();
		flakyActions.add(flakyAction);
		
		assertTrue( info.containFlakyAction(flakyActions) );
	}
	
	@Test
	public void testSetFlakyData_countOneFlaky(){
		TestCaseResultData caseResultData = new TestCaseResultData();
		
		info.setFlakyData(caseResultData, true);
		
		assertTrue( caseResultData.getTotalFlaky() == 1 );
	}
	
	@Test
	public void testSetFlakyData_countZeroFlaky(){
		TestCaseResultData caseResultData = new TestCaseResultData();
		
		info.setFlakyData(caseResultData, false);
		
		assertTrue( caseResultData.getTotalFlaky() == 0 );
	}
	
	@Test
	public void testSetFlakyData_isFlakyWithPass(){
		TestCaseResultData caseResultData = new TestCaseResultData();
		
		info.setFlakyData(caseResultData, true);
		
		assertTrue( caseResultData.isFlaky() );
	}
	
	@Test
	public void testSetFlakyData_isFlakyWithFail(){
		TestCaseResultData caseResultData = new TestCaseResultData();
		
		info.setFlakyData(caseResultData, false);
		
		assertFalse( caseResultData.isFlaky());
	}
	
	
	private JUnitFlakyTestDataAction createFailedFlakyAction(){
		List <FlakyRunInformation> flakyRunInfos = createFlakyRunInfos();
		
		JUnitFlakyTestDataAction flakyAction = new JUnitFlakyTestDataAction( flakyRunInfos, true );
		
		return flakyAction;
	}
	
	private JUnitFlakyTestDataAction createPassedFlakyAction(){
		List <FlakyRunInformation> flakyRunInfos = createFlakyRunInfos();
		JUnitFlakyTestDataAction flakyAction = new JUnitFlakyTestDataAction( flakyRunInfos, false );
		
		return flakyAction;
	}
	
	private List <FlakyRunInformation> createFlakyRunInfos(){
		FlakyRunInformation flakyRunInfo = new FlakyRunInformation("error detail", "stack trace", "stdout", "stderr");
		
		List <FlakyRunInformation> flakyRunInfos = new ArrayList<FlakyRunInformation>();
		flakyRunInfos.add(flakyRunInfo);
		
		return flakyRunInfos;
	}
	
}

