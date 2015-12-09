package org.jenkinsci.plugins.testresultsanalyzer;

import org.jenkinsci.plugins.testresultsanalyzer.result.data.ResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.info.ClassInfo;
import org.jenkinsci.plugins.testresultsanalyzer.result.info.PackageInfo;
import org.jenkinsci.plugins.testresultsanalyzer.result.info.TestCaseInfo;
import org.junit.Test;
import org.jvnet.hudson.test.HudsonTestCase;
import org.jvnet.hudson.test.WithoutJenkins;

import java.util.*;

public class TestResultAnalyzerActionTest extends HudsonTestCase {
    TestResultsAnalyzerAction defaultAction = createDefaultActionThirtyBuilds();

    @Test
    public void testGetBuildListShowCompileFailedBuild() {
        List<Integer> buildList = defaultAction.getBuildList(10, "show");
        assertTrue(buildList.contains(30));
    }

    @Test
    public void testGetBuildListShowLatestBuilds() {
        List<Integer> buildList = defaultAction.getBuildList(10, "show");
        assertTrue(buildList.contains(29));
    }

    @Test
    public void testGetBuildListHideCompileFailedBuild() {
        List<Integer> buildList = defaultAction.getBuildList(10, "no");
        assertTrue(buildList.contains(30) == false);
    }

    @Test
    public void testGetBuildListShowCompilePassedBuild() {
        List<Integer> buildList = defaultAction.getBuildList(10, "no");
        assertTrue(buildList.contains(29));
    }

    @Test
    public void testGetBuildListShowLimitedBuild() {
        List<Integer> buildList = defaultAction.getBuildList(10, "show");
        assertFalse(buildList.contains(19));
    }

    @Test
    public void testGetBuildListRightOrder() {
        List<Integer> buildList = defaultAction.getBuildList(10, "show");
        assertTrue(buildList.get(0) == 30);
    }

    @Test
    public void testGetBuildListRightOrderWithCompFail() {
        List<Integer> buildList = defaultAction.getBuildList(10, "no");
        assertTrue(buildList.get(0) == 29);
    }

    @Test
    public void testGetUserList() {
        TestResultsAnalyzerAction newaction = createDefaultActionUser();
        List<Integer> buildList = newaction.getBuildList(2, "no");
        List<String> usernames = newaction.getUsersList(buildList);
        assertTrue(usernames.size() != 0);
    }

    @Test
    @WithoutJenkins
    public void testDownloadCSV() {
        //arrange
        String expectedCsvHeader = "\"Package\",\"Class\",\"Test\",\"30\",\"29\",\"28\",\"27\",\"26\",\"25\",\"24\",\"23\",\"22\",\"21\",\"20\",\"19\",\"18\",\"17\",\"16\",\"15\",\"14\",\"13\",\"12\",\"11\",\"10\",\"9\",\"8\",\"7\",\"6\",\"5\",\"4\",\"3\",\"2\",\"1\"";
        String expectedCsvData = "\"TestPackage\",\"TestLibrary\",\"FakeTest\",\"PASSED\"";

        TestResultsAnalyzerAction analyzerAction = createDefaultActionThirtyBuilds();

        PackageInfo fakePackage = new PackageInfo();
        fakePackage.setName("TestPackage");
        ClassInfo fakeClass = new ClassInfo();
        fakeClass.setName("TestLibrary");
        TestCaseInfo fakeTest = new TestCaseInfo();
        fakeTest.setName("FakeTest");
        ResultData fakeResultData = new ResultData() {
        };
        fakeResultData.setName("FakeResult");
        fakeResultData.setStatus("PASSED");

        Map<String, PackageInfo> fakePackages = new TreeMap<String, PackageInfo>();
        Map<String, ClassInfo> fakeClasses = new TreeMap<String, ClassInfo>();
        Map<String, TestCaseInfo> fakeTests = new TreeMap<String, TestCaseInfo>();
        Map<Integer, ResultData> fakeResults = new TreeMap<Integer, ResultData>();

        fakeResults.put(0, fakeResultData);
        fakeTest.setBuildPackageResults(fakeResults);

        fakeTests.put("", fakeTest);
        fakeClass.setTests(fakeTests);

        fakeClasses.put("", fakeClass);
        fakePackage.setClasses(fakeClasses);

        fakePackages.put("", fakePackage);

        //act
        String actualCsv = analyzerAction.exportCSV(false, fakePackages);

        //assert

        //There are issues with the line endings on different platforms, this is a more general check
        assertTrue(actualCsv.contains(expectedCsvHeader));
        assertTrue(actualCsv.contains(expectedCsvData));
    }


    private TestResultsAnalyzerAction createDefaultActionThirtyBuilds() {
        List<Integer> builds = new ArrayList<Integer>();
        Vector<Integer> compileFailedBuilds = new Vector<Integer>();
        for (int i = 30; i > 0; i--) {
            builds.add(i);
            if (i % 5 == 0) {
                compileFailedBuilds.add(i);
            }
        }

        TestResultsAnalyzerAction action =
                new TestResultsAnalyzerAction(builds, compileFailedBuilds);

        return action;
    }

    private TestResultsAnalyzerAction createDefaultActionUser() {
        List<Integer> builds = new ArrayList<Integer>();
        Vector<Integer> compileFailedBuilds = new Vector<Integer>();
        for (int i = 2; i > 0; i--) {
            builds.add(i);
        }
        List<String> userinBuildChange = new ArrayList<String>();
        userinBuildChange.add("Xuewei");
        userinBuildChange.add("Xinnan");
        TestResultsAnalyzerAction action =
                new TestResultsAnalyzerAction(builds, compileFailedBuilds, userinBuildChange);

        return action;

    }
}
