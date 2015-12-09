package org.jenkinsci.plugins.testresultsanalyzer.result.info;

import hudson.tasks.test.TabulatedResult;
import hudson.tasks.test.TestResult;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.ClassResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.PackageResultData;
import org.jenkinsci.plugins.testresultsanalyzer.result.data.ResultData;

import java.util.Map;
import java.util.TreeMap;

public class PackageInfo extends Info {
    protected Map<String, ClassInfo> classes = new TreeMap<String, ClassInfo>();

    public void putPackageResult(Integer buildNumber, TabulatedResult packageResult, String url) {
        PackageResultData packageResultData = new PackageResultData(packageResult, url);
        evaluateStatusses(packageResult);

        addClasses(buildNumber, packageResult, url);

        int flakyCaseCount = countFlakyFromChildren(buildNumber);
        packageResultData.setTotalFlaky(flakyCaseCount);
        if (flakyCaseCount != 0) {
            packageResultData.setFlaky(true);
        } else {
            packageResultData.setFlaky(false);
        }

        this.buildResults.put(buildNumber, packageResultData);
    }

    public ResultData getPackageResult(Integer buildNumber) {
        if (this.buildResults.containsKey(buildNumber)) {
            return this.buildResults.get(buildNumber);
        }
        return null;
    }

    public Map<String, ClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, ClassInfo> classes) {
        this.classes = classes;
    }

    public void addClasses(Integer buildNumber, TabulatedResult packageResult, String url) {
        for (TestResult classResult : packageResult.getChildren()) {
            String className = classResult.getName();
            ClassInfo classInfo;
            if (classes.containsKey(className)) {
                classInfo = classes.get(className);
            } else {
                classInfo = new ClassInfo();
                classInfo.setName(className);
            }
            classInfo.putBuildClassResult(buildNumber, (TabulatedResult) classResult, url + "/" + classResult.getName());
            classes.put(className, classInfo);
        }
    }

    protected JSONObject getChildrensJson() {
        JSONObject json = new JSONObject();
        for (String className : classes.keySet()) {
            json.put(className, classes.get(className).getJsonObject());
        }
        return json;
    }

    public int countFlakyFromChildren(Integer buildNumber) {
        int flakyCaseCount = 0;
        for (ClassInfo classInfo : classes.values()) {
            ClassResultData classResult = (ClassResultData) classInfo.getBuildPackageResults().get(buildNumber);
            if (classResult != null) {
                flakyCaseCount += classResult.getTotalFlaky();
            }
        }
        return flakyCaseCount;
    }
}
