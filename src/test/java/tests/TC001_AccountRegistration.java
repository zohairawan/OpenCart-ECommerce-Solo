package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountRegistrationPage;
import pages.HomePage;

import java.util.Random;

public class TC001_AccountRegistration {

    public WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyAccountRegistration() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccountDropdown();
        homePage.clickRegisterLink();

        AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);
        accountRegistrationPage.setFirstName(randomString(5).toUpperCase());
        accountRegistrationPage.setLastName(randomString(4).toUpperCase());
        accountRegistrationPage.setEmail(randomString(5) + "@gmail.com");
        accountRegistrationPage.setPhoneNumber(randomNumber(10));
        String password = randomAlphaNumeric(5,3);
        accountRegistrationPage.setPassword(password);
        accountRegistrationPage.setPasswordConfirm(password);
        accountRegistrationPage.clickPrivacyPolicyCheckbox();
        accountRegistrationPage.clickContinueButton();

        Assert.assertEquals(accountRegistrationPage.getConfirmationMessage(), "Your Account Has Been Created!");
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