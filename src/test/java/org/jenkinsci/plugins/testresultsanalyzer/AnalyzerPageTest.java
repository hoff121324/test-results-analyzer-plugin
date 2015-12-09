package org.jenkinsci.plugins.testresultsanalyzer;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Result;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.jvnet.hudson.test.FailureBuilder;
import org.jvnet.hudson.test.JenkinsRule;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class AnalyzerPageTest {
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private static WebDriverWait wait;

    @Rule
    public JenkinsRule jenkinsRule = new JenkinsRule();

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

    public String multipleTestsPopulateTableJavascript() {
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

    public String singleTestPopulateTableJavascript() {
        String javaScriptCommand = "var Obj =   {" +
                "\"builds\":[\"2\",\"1\"]," +
                "\"results\":   [{" +
                "\"buildResults\":  [{" +
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
                "addEvents();" +
                "newFailingTests();";
        return javaScriptCommand;
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

    private void BuildTestHelper(String javaScriptCommand, String contained) throws Exception {
        js.executeScript(javaScriptCommand);
        WebElement exclamation_mark = driver.findElement(By.xpath("//*[contains(concat(' ', @class, ' '), ' icon-exclamation-sign ')]"));
        assertTrue(exclamation_mark.getAttribute("style").contains(contained));
    }

    @Test
    public void newFailuresTestNoBuild() throws Exception {
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
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void newFailuresTestTwoBuildsTrueCase() throws Exception {
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
    public void newFailuresTestTwoBuildsFalseCase() throws Exception {
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
    public void newFailuresTestOneBuildBuildFailure() throws Exception {
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
    public void newFailuresTestOneBuildBuildSuccess() throws Exception {
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
    public void newFailuresTestMultipleBuildsTrueCase() throws Exception {
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
        assert (exclamation_mark.getAttribute("style").contains("inline-block"));
    }

    @Test
    public void newFailuresTestMultipleBuildsFalseCase() throws Exception {
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
        assert (exclamation_mark.getAttribute("style").contains("none"));
    }

    @Test
    public void newFailuresTestMultipleTestsMultipleBuilds() throws Exception {
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
    public void searchTestMatchExists() throws Exception {
        String javaScriptCommand = singleTestPopulateTableJavascript() + "$j(\"#filter\").val(\"illinois\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void searchTestNoMatch() throws Exception {
        String javaScriptCommand = singleTestPopulateTableJavascript() + "$j(\"#filter\").val(\"was\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

    @Test
    public void searchTestEmptyFilter() throws Exception {
        String javaScriptCommand = singleTestPopulateTableJavascript() + "$j(\"#filter\").val(\"\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void searchTestMultipleTestsMatchExists() throws Exception {
        String javaScriptCommand = multipleTestsPopulateTableJavascript() + "$j(\"#filter\").val(\"512\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs512')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void searchTestMultipleTestsNoMatch() throws Exception {
        String javaScriptCommand = multipleTestsPopulateTableJavascript() + "$j(\"#filter\").val(\"meaning of life\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs512')]"));
        assertTrue(row.getAttribute("style").contains("none"));
    }

    @Test
    public void searchMultipleTestsEmptyFilter() throws Exception {
        String javaScriptCommand = multipleTestsPopulateTableJavascript() + "$j(\"#filter\").val(\"\");searchTests();";
        js.executeScript(javaScriptCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs512')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));
    }

    @Test
    public void testFilterPassedSatisfied() throws Exception {
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
    public void testFilterPassedNotSatisfied() throws Exception {
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
    public void testFilterFailSatisfied() throws Exception {
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
    public void testFilterFailedNotSatisfied() throws Exception {
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
    public void testFilterSkippedSatisfied() throws Exception {
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
    public void testFilterSkippedNotSatisfied() throws Exception {
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
    public void testFilterFlakySatisfied() throws Exception {
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
    public void testFilterFlakyNotSatisfied() throws Exception {
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
    public void testRuntimeFilterLessThan() throws Exception {
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
        String jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"3\");" +
                "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.1');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"2\");" +
                "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.1');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"2\");" +
                "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.5');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"1\");" +
                "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.5');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"1\");" +
                "$j('#RuntimeFilterOptions').val('LessThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('5');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

    }

    @Test
    public void testRuntimeFilterGreaterThan() throws Exception {
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
        String jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"3\");" +
                "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.01');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        WebElement row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"3\");" +
                "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.2');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"2\");" +
                "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.2');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"2\");" +
                "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('0.5');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("none"));

        jsCommand = "$j('#FilterSelectBy').val('RUNTIME');" +
                "$j('#BuildNumber').val(\"1\");" +
                "$j('#RuntimeFilterOptions').val('GreaterThanEqualTo');" +
                "$j('#RuntimeFilterVal').val('2');" +
                "applyFilter();";
        js.executeScript(jsCommand);
        row = driver.findElement(By.xpath("//*[contains(@class, 'cs427')]"));
        assertTrue(row.getAttribute("style").contains("table-row"));

    }
}

