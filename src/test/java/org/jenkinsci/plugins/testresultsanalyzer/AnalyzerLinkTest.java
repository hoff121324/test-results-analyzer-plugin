package org.jenkinsci.plugins.testresultsanalyzer;

import hudson.maven.MavenModuleSet;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class AnalyzerLinkTest {
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

    @Before
    public void refreshDriver() throws Exception {
        MavenModuleSet project = (MavenModuleSet) jenkinsRule.getInstance().getItem("test");
        String url = jenkinsRule.getURL() + project.getUrl();
        String query = url + "edu.illinois.cs427$mp3/test_results_analyzer";

        driver.get(query);
    }

    @Test
    @LocalData
    public void testLinkPackage() {
        //arrange
        String expectedLink = "/job/test/edu.illinois.cs427$mp3/3/testReport/edu.illinois.cs427.mp3";

        //act
        WebElement packageLink = driver.findElement(By.xpath("//div[@class='table-row base-edu_illinois_cs427_mp3']//div[@class='table-cell build-result passed']//a"));
        String actualLink = packageLink.getAttribute("href");

        //assert
        assertTrue(actualLink.contains(expectedLink));
    }

    @Test
    @LocalData
    public void testLinkClass() {
        //arrange
        String expectedLink = "/job/test/edu.illinois.cs427$mp3/3/testReport/edu.illinois.cs427.mp3/BookTest";

        //act
        WebElement packageLink = driver.findElement(By.xpath("//div[@class='table-row base-edu_illinois_cs427_mp3-BookTest']//div[@class='table-cell build-result passed']//a"));
        String actualLink = packageLink.getAttribute("href");

        //assert
        assertTrue(actualLink.contains(expectedLink));
    }

    @Test
    @LocalData
    public void testLinkTestCase() {
        //arrange
        String expectedLink = "/job/test/edu.illinois.cs427$mp3/3/testReport/edu.illinois.cs427.mp3/BookTest/testBookConstructor1";

        //act
        WebElement packageLink = driver.findElement(By.xpath("//div[@class='table-row base-edu_illinois_cs427_mp3-BookTest-testBookConstructor1']//div[@class='table-cell build-result passed']//a"));
        String actualLink = packageLink.getAttribute("href");

        //assert
        assertTrue(actualLink.contains(expectedLink));
    }
}
