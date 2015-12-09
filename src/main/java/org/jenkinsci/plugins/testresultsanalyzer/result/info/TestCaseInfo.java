package org.jenkinsci.plugins.testresultsanalyzer.result.info;

import com.google.jenkins.flakyTestHandler.plugin.JUnitFlakyTestDataAction;
import hudson.tasks.junit.TestAction;
import hudson.tasks.test.TestResult;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.TestCaseResultData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestCaseInfo extends Info {

    public void putTestCaseResult(Integer buildNumber, TestResult testCaseResult, String url) {
        TestCaseResultData testCaseResultData = new TestCaseResultData(testCaseResult, url);
        evaluateStatusses(testCaseResult);

        boolean isFlaky = isTestCaseFlaky(testCaseResult);
        setFlakyData(testCaseResultData, isFlaky);

        this.buildResults.put(buildNumber, testCaseResultData);
    }

    @Override
    protected JSONObject getChildrensJson() {

        return new JSONObject();
    }

    public boolean isTestCaseFlaky(TestResult caseResult) {
        List<TestAction> caseActions = caseResult.getTestActions();
        Collection<JUnitFlakyTestDataAction> flakyActions = getFlakyActions(caseActions);

        boolean isCaseFlaky = containFlakyAction(flakyActions);
        return isCaseFlaky;
    }

    public Collection<JUnitFlakyTestDataAction> getFlakyActions(List<TestAction> caseActions) {
        Collection<JUnitFlakyTestDataAction> flakyActions = new ArrayList<JUnitFlakyTestDataAction>();

        for (int i = 0; i < caseActions.size(); i++) {
            //find JUnitFlakyTestDataAction
            if (caseActions.get(i).getClass() == JUnitFlakyTestDataAction.class) {
                JUnitFlakyTestDataAction flakyAction = (JUnitFlakyTestDataAction) caseActions.get(i);
                flakyActions.add(flakyAction);
            }
        }

        return flakyActions;
    }

    public boolean containFlakyAction(Collection<JUnitFlakyTestDataAction> flakyActions) {
        boolean isFlaky = false;
        for (JUnitFlakyTestDataAction flakyAction : flakyActions) {
            if (flakyAction.getIsFlaked()) {
                isFlaky = true;
            }
        }
        return isFlaky;
    }

    public void setFlakyData(TestCaseResultData testCaseResultData, boolean isCaseFlaky) {
        testCaseResultData.setFlaky(isCaseFlaky);
        if (isCaseFlaky) {
            testCaseResultData.setTotalFlaky(1);
        } else {
            testCaseResultData.setTotalFlaky(0);
        }
    }
}
