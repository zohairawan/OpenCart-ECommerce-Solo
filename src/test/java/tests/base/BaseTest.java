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

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public Logger logger;
    public Properties propertiesFile;

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setUp(String os, String browser) {
        try (FileReader fileReader = new FileReader(System.getProperty("user.dir") + "//src//test//resources//config.properties")) {
            propertiesFile = new Properties();
            propertiesFile.load(fileReader); // Load data from config.properties into propertiesFile
            logger = LogManager.getLogger(this.getClass()); // Loads log4j2.xml file
            switch (browser.toLowerCase()) {
                case "chrome" -> driver = new ChromeDriver();
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                default -> {
                    logger.error("Invalid browser name");
                    return;
                }
            }
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.get(propertiesFile.getProperty("homepageURL"));
        } catch (IOException e) {
            logger.error("Properties file could not be located");
        }
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