package tests.base;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    public Logger logger;
    public Properties propertiesFile;

    @BeforeClass(groups = {"sanity", "regression", "datadriven", "master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String browser) {
        try (FileReader fileReader = new FileReader(System.getProperty("user.dir") + "//src//test//resources//config.properties")) {
            propertiesFile = new Properties();
            propertiesFile.load(fileReader); // Load data from config.properties into propertiesFile
            logger = LogManager.getLogger(this.getClass()); // Loads log4j2.xml file

            if (propertiesFile.getProperty("executionEnv").equalsIgnoreCase("local")) {
                switch (browser.toLowerCase()) {
                    case "chrome" -> driver = new ChromeDriver();
                    case "firefox" -> driver = new FirefoxDriver();
                    case "edge" -> driver = new EdgeDriver();
                    default -> {
                        logger.error("Invalid browser name");
                        return;
                    }
                }
            }

            // Run in Grid Environment
            if (propertiesFile.getProperty("executionEnv").equalsIgnoreCase("remote")) {
                String hubURL = "http://localhost:4444/wd/hub";
                DesiredCapabilities capabilities = new DesiredCapabilities();

                switch (os.toLowerCase()) {
                    case "windows" -> capabilities.setPlatform(Platform.WIN11);
                    case "mac" -> capabilities.setPlatform(Platform.MAC);
                    default -> {
                        logger.error("No matching OS");
                        return;
                    }
                }
                switch (browser.toLowerCase()) {
                    case "chrome" -> capabilities.setBrowserName("chrome");
                    case "firefox" -> capabilities.setBrowserName("firefox");
                    case "edge" -> capabilities.setBrowserName("MicrosoftEdge");
                    default -> {
                        logger.error("No matching browser");
                        return;
                    }
                }
                driver = new RemoteWebDriver(new URL(hubURL), capabilities);
            }

            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.get(propertiesFile.getProperty("homepageURL"));
        } catch (IOException e) {
            logger.error("Properties file could not be located");
        }
    }

    @AfterClass(groups = {"sanity", "regression", "datadriven", "master"})
    public void tearDown() {
        driver.quit();
    }

    public String takeScreenshot(String testMethodName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testMethodName + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        System.out.println("Target File Path: " + targetFilePath);
//        sourceFile.renameTo(targetFile);
        FileUtils.copyFile(sourceFile, targetFile);
        return targetFilePath;
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