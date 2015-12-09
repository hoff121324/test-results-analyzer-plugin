package org.jenkinsci.plugins.testresultsanalyzer;

import hudson.maven.MavenModuleSet;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CodeCoverageTest {
    @Rule
    public JenkinsRule jenkinsRule = new JenkinsRule();

    private static WebDriver driver;
    private static JavascriptExecutor js;
    private static WebDriverWait wait;

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
        MavenModuleSet project = (MavenModuleSet) jenkinsRule.getInstance().getItem("test");
        String url = jenkinsRule.getURL() + project.getUrl();
        String query = url + "/edu.illinois.cs427$mp3/test_results_analyzer";

        driver.get(query);
    }

    public void setWindowWidth(int w) {
        Dimension dim = driver.manage().window().getSize();
        int height = dim.getHeight();
        driver.manage().window().setSize(new Dimension(w, height));
    }

    public void setChartsShown(boolean line) {
        WebElement linegraph = driver.findElement(By.id("linegraph"));

        if(linegraph.isSelected() != line && linegraph.isEnabled()) {
            linegraph.click();
        }
  
        driver.findElement(By.id("getbuildreport")).click();
    }

    public void setCoverageCharts() {
        setChartsHelper("Code Coverage");
    }

    private void setChartsHelper(String chartVisibleText) {
        Select select = new Select(driver.findElement(By.id("chartDataType")));
        select.selectByVisibleText(chartVisibleText);
        driver.findElement(By.id("getbuildreport")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#tree > .table"), "Chart"));
    }

    @Test
    @LocalData
    public void testCodeCoverageIsLoaded()
    {
        //arrange
        setCoverageCharts();
        WebElement lineChart = driver.findElement(By.id("linechart"));
        WebElement barChart = driver.findElement(By.id("barchart"));
        WebElement pieChart = driver.findElement(By.id("piechart"));

        //assert
        assertNotEquals(null, lineChart.getAttribute("data-highcharts-chart"));
        assertNotEquals("", lineChart.getAttribute("innerHTML"));
        assertNotEquals(0, js.executeScript("return jQuery('#linechart:contains(\"Runtime\")').size();"));
        //verify that other graphs are not generated
        assertEquals("", barChart.getAttribute("innerHTML"));
        assertEquals("", pieChart.getAttribute("innerHTML"));
    }

    @Test
    @LocalData
    public void testCodeCoverageResults()
    {
        //arrange
        String expectedClasses = "100 %";
        String expectedConditionals = "100 %";
        String expectedFiles = "100 %";
        String expectedLines = "100 %";
        String expectedMethods = "100 %";
        String expectedPackages = "100 %";

        //act
        setCoverageCharts();
        WebElement svgElement = driver.findElement(By.className("highcharts-tooltip"));
        List<WebElement> codeResult = svgElement.findElement(By.name("text")).findElements(By.name("tspan"));

        //assert
        assertEquals(codeResult.get(1), expectedClasses);
        assertEquals(codeResult.get(2), expectedConditionals);
        assertEquals(codeResult.get(3), expectedFiles);
        assertEquals(codeResult.get(4), expectedLines);
        assertEquals(codeResult.get(5), expectedMethods);
        assertEquals(codeResult.get(6), expectedPackages);
    }
}