package org.jenkinsci.plugins.testresultsanalyzer;

import hudson.model.*;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.*;
import org.junit.*;
import static org.junit.Assert.*;
import hudson.model.FreeStyleProject;
import java.util.Locale;
import java.io.File;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriverService.Builder;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.*;

public class AnalyzerPageTest {
	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;

	@Rule
	public JenkinsRule jenkinsRule = new JenkinsRule();

	public String multipleTests_populateTable_javascript(){
        String javaScriptCommand = "var Obj =  {" +
                                                "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                "\"results\":   [{" +
                                                                    "\"buildResults\":  [{" +
                                                                                            "\"buildNumber\":\"3\"," +
                                                                                            "\"children\":[]," +
                                                                                            "\"isPassed\":false," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                            "\"status\":\"FAILED\"," +
                                                                                            "\"totalFailed\":1," +
                                                                                            "\"totalPassed\":0," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.023" +
                                                                                        "},{" +
                                                                                            "\"buildNumber\":\"2\"," +
                                                                                            "\"children\":[]," +
                                                                                            "\"isPassed\":false," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                            "\"status\":\"FAILED\"," +
                                                                                            "\"totalFailed\":1," +
                                                                                            "\"totalPassed\":0," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.023" +
                                                                                        "},{" +
                                                                                            "\"buildNumber\":\"1\"," +
                                                                                            "\"children\":[]," +
                                                                                            "\"isPassed\":true," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                            "\"status\":\"PASSED\"," +
                                                                                            "\"totalFailed\":0," +
                                                                                            "\"totalPassed\":1," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.032" +
                                                                                        "}]," +
                                                                    "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                    "\"children\":[]," +
                                                                    "\"parentclass\":\"base\"," +
                                                                    "\"parentname\":\"base\"," +
                                                                    "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                    "\"type\":\"package\"" +
                                                                "},{" +
                                                                    "\"buildResults\":  [{" +
                                                                                            "\"buildNumber\":\"3\"," +
                                                                                            "\"children\":[]," +
                                                                                            "\"isPassed\":false," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs512.mp0\"," +
                                                                                            "\"status\":\"FAILED\"," +
                                                                                            "\"totalFailed\":1," +
                                                                                            "\"totalPassed\":0," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.253" +
                                                                                        "},{" +
                                                                                            "\"buildNumber\":\"2\"," +
                                                                                            "\"children\":[]," +
                                                                                            "\"isPassed\":true," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs512.mp0\"," +
                                                                                            "\"status\":\"PASSED\"," +
                                                                                            "\"totalFailed\":0," +
                                                                                            "\"totalPassed\":1," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.253" +
                                                                                        "},{" +
                                                                                            "\"buildNumber\":\"1\"," +
                                                                                            "\"children\":[]," +
                                                                                            "\"isPassed\":true," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs512.mp0\"," +
                                                                                            "\"status\":\"FAILED\"," +
                                                                                            "\"totalFailed\":1," +
                                                                                            "\"totalPassed\":0," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.392" +
                                                                                        "}]," +
                                                                        "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs512.mp0\"," +
                                                                        "\"type\":\"package\"" +
                                                                    "}]" + 
                                            "};" +
                                            "treeMarkup = analyzerTemplate(Obj);" +
                                            "$j(\".table\").html(treeMarkup);" +
                                            "addEvents();" +
                                            "newFailingTests();";
    return javaScriptCommand;
}

    public String singleTest_populateTable_javascript(){
        String javaScriptCommand = "var Obj =   {" +
                "\"builds\":[\"2\",\"1\"]," +
                "\"results\":   [{" +
                                    "\"buildResults\":  [{" +
                                                            "\"buildNumber\":\"2\","+
                                                            "\"children\":[],"+
                                                            "\"isPassed\":false,"+
                                                            "\"isSkipped\":false,"+
                                                            "\"name\":\"edu.illinois.cs427.mp3\","+
                                                            "\"status\":\"FAILED\","+
                                                            "\"totalFailed\":1,"+
                                                            "\"totalPassed\":0,"+
                                                            "\"totalSkipped\":0,"+
                                                            "\"totalTests\":1,"+
                                                            "\"totalTimeTaken\":0.023"+
                                                        "},{" +
                                                            "\"buildNumber\":\"1\"," +
                                                            "\"children\":[]," +
                                                            "\"isPassed\":true," +
                                                            "\"isSkipped\":false," +
                                                            "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                            "\"status\":\"PASSED\"," +
                                                            "\"totalFailed\":0," +
                                                            "\"totalPassed\":1," +
                                                            "\"totalSkipped\":0," +
                                                            "\"totalTests\":1," +
                                                            "\"totalTimeTaken\":0.032" +
                                                        "}]," +
                                    "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                    "\"children\":[]," +
                                    "\"parentclass\":\"base\"," +
                                    "\"parentname\":\"base\"," +
                                    "\"text\": \"edu.illinois.cs427.mp3\"," +
                                    "\"type\":\"package\"" +
                                "}]" + 
            "};" +
            "treeMarkup = analyzerTemplate(Obj);" +
            "$j(\".table\").html(treeMarkup);" +
            "addEvents();" +
            "newFailingTests();";
            return javaScriptCommand;
    }


	@BeforeClass
	public static void startDriver() throws Exception {
		//Web Driver Manager handling the driver binaries for us
		ChromeDriverManager.getInstance().setup();

		//Selenium chrome driver
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 10);
	}

	@AfterClass
	public static void stopDriver() throws Exception {
		driver.quit();
	}

	@Before
	public void refreshDriver() throws Exception {
		FreeStyleProject project = jenkinsRule.createFreeStyleProject();
		project.getBuildersList().add(new FailureBuilder());
		FreeStyleBuild build = project.scheduleBuild2(0).get();
		jenkinsRule.assertBuildStatus(Result.FAILURE, build);

		String url = jenkinsRule.getURL() + project.getUrl();
		String query = url + "test_results_analyzer";

		driver.get(query);
	}

	@Test
    public void newFailuresTest_noBuild() throws Exception {
        String javaScriptCommand = "var Obj = {\"" + 
                                                "builds\":[]," + 
                                                "\"results\":[]" + 
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" +
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();" +
                                                "newFailingTests();";
        js.executeScript(javaScriptCommand);
        try { 
            WebElement exclamation_marks = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
            assertTrue(false);
        }
        catch (NoSuchElementException e){
            assertTrue(true);
        }
    }

    @Test
    public void newFailuresTest_twoBuilds_trueCase() throws Exception {
        String javaScriptCommand = "var Obj = {\"" + 
                                                "builds\":[\"2\",\"1\"]," + 
                                                "\"results\":[{" + 
                                                                "\"buildResults\":[{" +
                                                                                    "\"buildNumber\":\"2\"," +
                                                                                    "\"children\":[]," +
                                                                                    "\"isPassed\":false," + 
                                                                                    "\"isSkipped\":false," + 
                                                                                    "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                    "\"status\":\"FAILED\"," +
                                                                                    "\"totalFailed\":1," + 
                                                                                    "\"totalPassed\":0," + 
                                                                                    "\"totalSkipped\":0," +
                                                                                    "\"totalTests\":1," +
                                                                                    "\"totalTimeTaken\":0.023" + 
                                                                                "},{" +
                                                                                    "\"buildNumber\":\"1\"," +
                                                                                    "\"children\":[]," +
                                                                                    "\"isPassed\":true," + 
                                                                                    "\"isSkipped\":false," +
                                                                                    "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                    "\"status\":\"PASSED\"," + 
                                                                                    "\"totalFailed\":0," +
                                                                                    "\"totalPassed\":1," +
                                                                                    "\"totalSkipped\":0," +
                                                                                    "\"totalTests\":1," +
                                                                                    "\"totalTimeTaken\":0.032" +
                                                                                "}]," + 
                                                                "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," + 
                                                                "\"children\":[]," +
                                                                "\"parentclass\":\"base\"," +
                                                                "\"parentname\":\"base\"," +
                                                                "\"text\": \"edu.illinois.cs427.mp3\"," +
                                                                "\"type\":\"package\"" +
                                                            "}]" + 
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" +
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();";
        js.executeScript(javaScriptCommand);
        WebElement exclamation_mark = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assertTrue(exclamation_mark.getAttribute("style").contains("inline-block"));
    }

    @Test
    public void newFailuresTest_twoBuilds_falseCase() throws Exception {
        String javaScriptCommand = "var Obj =   {\"" +
                                                    "builds\":[\"2\",\"1\"]," +
                                                    "\"results\": [{" + 
                                                                    "\"buildResults\":  [{" +
                                                                                            "\"buildNumber\":\"2\"," +
                                                                                            "\"children\":[]," + 
                                                                                            "\"isPassed\":true," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                            "\"status\":\"PASSED\"," +
                                                                                            "\"totalFailed\":0," +
                                                                                            "\"totalPassed\":1," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.023" +
                                                                                        "},{" +
                                                                                            "\"buildNumber\":\"1\"," +
                                                                                            "\"children\":[]," +
                                                                                            "\"isPassed\":false," +
                                                                                            "\"isSkipped\":false," +
                                                                                            "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                            "\"status\":\"FAILED\"," +
                                                                                            "\"totalFailed\":1," +
                                                                                            "\"totalPassed\":0," +
                                                                                            "\"totalSkipped\":0," +
                                                                                            "\"totalTests\":1," +
                                                                                            "\"totalTimeTaken\":0.032" +
                                                                                        "}]," +
                                                                    "\"buildStatuses\": [\"FAILED\",\"PASSED\"]," + 
                                                                    "\"children\":[]," +
                                                                    "\"parentclass\":\"base\"," +
                                                                    "\"parentname\":\"base\"," +
                                                                    "\"text\": \"edu.illinois.cs427.mp3\"," +
                                                                    "\"type\":\"package\"" + 
                                                                "}]" + 
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" +
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();";
        js.executeScript(javaScriptCommand);
        WebElement exclamation_mark = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assertTrue(exclamation_mark.getAttribute("style").contains("none"));
    }

    @Test
    public void newFailuresTest_oneBuild_buildFailure() throws Exception {
        String javaScriptCommand = "var Obj =   {\"" +
                                                    "builds\":[\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" + 
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," + 
                                                                        "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\": \"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" + 
                                                                    "}]" + 
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" + 
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();";
        js.executeScript(javaScriptCommand);
        WebElement exclamation_mark = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assertTrue(exclamation_mark.getAttribute("style").contains("none"));
    }

    @Test
    public void newFailuresTest_oneBuild_buildSuccess() throws Exception {
        String javaScriptCommand = "var Obj =   {\"" +
                                                    "builds\":[\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\": \"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" + 
                                                                    "}]" + 
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" +
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();";
        js.executeScript(javaScriptCommand);
        WebElement exclamation_mark = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assertTrue(exclamation_mark.getAttribute("style").contains("none"));   
    }

    @Test
    public void newFailuresTest_multipleBuilds_trueCase() throws Exception {
        String javaScriptCommand = "var Obj =   {\"" + 
                                                    "builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.0432" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\": \"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" + 
                                                                    "}]" +
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" +
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();";
        js.executeScript(javaScriptCommand);
        WebElement exclamation_mark = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assert(exclamation_mark.getAttribute("style").contains("inline-block"));
    }

    @Test
    public void newFailuresTest_multipleBuilds_falseCase() throws Exception {
        String javaScriptCommand = "var Obj =   {" + 
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.0432" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\": \"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" + 
                                                                    "}]" +
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" +
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();";
        js.executeScript(javaScriptCommand);
        WebElement exclamation_mark = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assert(exclamation_mark.getAttribute("style").contains("none"));
    }

    @Test
    public void newFailuresTest_multipleTests_multipleBuilds() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                    "},{" +
                                                                        "\"buildResults\":  [{" +
                                                                        "                       \"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs512.mp0\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.253" +
                                                                                            "},{" + 
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs512.mp0\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.253" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs512.mp0\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalFailed\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.392" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"FAILED\",\"PASSED\"]," +
                                                                        "\"children\":[]," + 
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs512.mp0\"," +
                                                                        "\"type\":\"package\"" +
                                                                    "}]" + 
                                                "};" +
                                                "treeMarkup = analyzerTemplate(Obj);" +
                                                "$j(\".table\").html(treeMarkup);" +
                                                "addEvents();";
        js.executeScript(javaScriptCommand);
        WebElement cs427_exclamation_mark = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assertTrue(cs427_exclamation_mark.getAttribute("style").contains("none"));
        WebElement cs512_exclamation_mark = driver.findElement(By.xpath("//*[contains(@class, 'cs512')]//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assertTrue(cs512_exclamation_mark.getAttribute("style").contains("inline-block"));
    }


	// TESTS for filterTests()
	
	@Test
    public void searchTest_matchExists() throws Exception {
        String javaScriptCommand = singleTest_populateTable_javascript() + "$j(\"#filter\").val(\"illinois\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void searchTest_noMatch() throws Exception {
        String javaScriptCommand = singleTest_populateTable_javascript() + "$j(\"#filter\").val(\"was\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

    @Test
    public void searchTest_emptyFilter() throws Exception {
        String javaScriptCommand = singleTest_populateTable_javascript() + "$j(\"#filter\").val(\"\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void searchTest_multipleTests_matchExists() throws Exception {
        String javaScriptCommand = multipleTests_populateTable_javascript() + "$j(\"#filter\").val(\"512\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs512')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void searchTest_multipleTests_noMatch() throws Exception {
        String javaScriptCommand = multipleTests_populateTable_javascript() + "$j(\"#filter\").val(\"meaning of life\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs512')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

    @Test
    public void search_multipleTests_emptyFilter() throws Exception {
        String javaScriptCommand = multipleTests_populateTable_javascript() + "$j(\"#filter\").val(\"\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs512')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

	 @Test
    public void testFilter_PassedSatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", true);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void testFilter_PassedNotSatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", true);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

    @Test
    public void testFilter_FailSatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", true);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void testFilter_FailedNotSatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", true);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

    @Test
    public void testFilter_SkippedSatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":true," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":1," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", true);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void testFilter_SkippedNotSatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", true);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

        @Test
    public void testFilter_FlakySatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":true," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFlaked\").prop(\"checked\", true);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void testFilter_FlakyNotSatisfied() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\",\"totalPASSED\":1," +
                                                                                                "\"totalPassed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();" +
                                                    "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                                                    "$j(\"#statusFilterFlaked\").prop(\"checked\", true);" +
                                                    "applyFilter();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

    @Test
    public void testFilterBuildNo() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":true," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.032" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();";
        js.executeScript(javaScriptCommand);
        String jsCommand = "$j('#BuildNumber').val(\"3\");" + 
                            "$j(\"#statusFilterPassed\").prop(\"checked\", true);" +
                            "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFlaked\").prop(\"checked\", false);" + 
                            "applyFilter();";
        js.executeScript(jsCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
        
        jsCommand = "$j('#BuildNumber').val(3);" + 
                            "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFlaked\").prop(\"checked\", true);" + 
                            "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
        

        jsCommand = "$j('#BuildNumber').val(2);" + 
                            "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFlaked\").prop(\"checked\", true);" + 
                            "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
        
        jsCommand = "$j('#BuildNumber').val(1);" + 
                            "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFailed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFlaked\").prop(\"checked\", true);" + 
                            "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));

        jsCommand = "$j('#BuildNumber').val(1);" + 
                            "$j(\"#statusFilterPassed\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFailed\").prop(\"checked\", true);" +
                            "$j(\"#statusFilterSkipped\").prop(\"checked\", false);" +
                            "$j(\"#statusFilterFlaked\").prop(\"checked\", false);" + 
                            "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));




    }

    @Test
    public void testRuntimeFilter_lessThan() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":true," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.23" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":3.2" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();";
        js.executeScript(javaScriptCommand);
        String jsCommand =  "$j('#FilterSelectBy').val('RUNTIME');" +
                            "$j('#BuildNumber').val(\"3\");" + 
                            "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                            "$j('#RuntimeFilterVal').val('0.1');" +
                            "applyFilter();";
        js.executeScript(jsCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
        
        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"2\");" + 
                        "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('0.1');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
        
        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"2\");" + 
                        "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('0.5');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"1\");" + 
                        "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('0.5');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));

        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"1\");" + 
                        "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('5');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
        
    }
    
    @Test
    public void testRuntimeFilter_greaterThan() throws Exception {
        String javaScriptCommand = "var Obj =   {" +
                                                    "\"builds\":[\"3\",\"2\",\"1\"]," +
                                                    "\"results\":   [{" +
                                                                        "\"buildResults\":  [{" +
                                                                                                "\"buildNumber\":\"3\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalFailed\":0," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.023" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"2\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":true," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":true," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"PASSED\"," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":0.23" +
                                                                                            "},{" +
                                                                                                "\"buildNumber\":\"1\"," +
                                                                                                "\"children\":[]," +
                                                                                                "\"isPassed\":false," +
                                                                                                "\"isSkipped\":false," +
                                                                                                "\"isFlaky\":false," +
                                                                                                "\"name\":\"edu.illinois.cs427.mp3\"," +
                                                                                                "\"status\":\"FAILED\"," +
                                                                                                "\"totalPASSED\":0," +
                                                                                                "\"totalPassed\":1," +
                                                                                                "\"totalSkipped\":0," +
                                                                                                "\"totalTests\":1," +
                                                                                                "\"totalTimeTaken\":3.2" +
                                                                                            "}]," +
                                                                        "\"buildStatuses\":[\"PASSED\",\"FAILED\"]," +
                                                                        "\"children\":[]," +
                                                                        "\"parentclass\":\"base\"," +
                                                                        "\"parentname\":\"base\"," +
                                                                        "\"text\":\"edu.illinois.cs427.mp3\"," +
                                                                        "\"type\":\"package\"" +
                                                                "}]" +
                                                    "};" +
                                                    "treeMarkup = analyzerTemplate(Obj);" +
                                                    "$j(\".table\").html(treeMarkup);" +
                                                    "addEvents();newFailingTests();" +
                                                    "addBuildNumbers();";
        js.executeScript(javaScriptCommand);
        String jsCommand =  "$j('#FilterSelectBy').val('RUNTIME');" +
                            "$j('#BuildNumber').val(\"3\");" + 
                            "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                            "$j('#RuntimeFilterVal').val('0.01');" +
                            "applyFilter();";
        js.executeScript(jsCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
        
        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"3\");" + 
                        "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('0.2');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
        
        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"2\");" + 
                        "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('0.2');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"2\");" + 
                        "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('0.5');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));

        jsCommand =     "$j('#FilterSelectBy').val('RUNTIME');" +
                        "$j('#BuildNumber').val(\"1\");" + 
                        "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                        "$j('#RuntimeFilterVal').val('2');" +
                        "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
        
    }
}

