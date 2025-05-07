package tests.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    @Parameters({"os","browser"})
    public void setup(String os, String browser) {
        logger = LogManager.getLogger(this.getClass()); // Loads log4j2.xml file
        switch (browser.toLowerCase()) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
            default -> {logger.error("Invalid browser name");return;}
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public String randomString(int lengthOfString) {
        return RandomStringUtils.randomAlphabetic(lengthOfString);
    }

    public String randomNumber(int lengthOfNumber) {
        return RandomStringUtils.randomNumeric(lengthOfNumber);
    }

    public String randomAlphaNumeric(int lengthOfString, int lengthOfNumber) {
        return RandomStringUtils.randomAlphabetic(lengthOfString) + RandomStringUtils.randomNumeric(lengthOfNumber);
    }
}