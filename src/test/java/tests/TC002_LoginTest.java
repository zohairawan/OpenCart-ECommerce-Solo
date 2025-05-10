package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import tests.base.BaseTest;

public class TC002_LoginTest extends BaseTest {

    @Test
    public void testValidCredLogin() {
        logger.info("***** Started testValidCredLogin *****");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccountDropdown();
            logger.info("Clicked on 'Account' dropdown");
            homePage.clickLoginLink();
            logger.info("Clicked on 'Register' link");

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Providing valid login credentials");
            loginPage.typeEmailAddress(propertiesFile.getProperty("email"));
            loginPage.typePassword(propertiesFile.getProperty("password"));
//        loginPage.typeEmailAddress(email);
//        loginPage.typePassword(password);
            loginPage.clickLoginButton();
            logger.info("Clicked on Login button");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            logger.info("Validating actual header");
            Assert.assertTrue(myAccountPage.myAccountHeaderIsDisplayed());
        } catch (Exception e) {
            logger.error("'TEST FAILED': " + e.getMessage());
            Assert.fail("'TEST FAILED': " + e.getMessage());
        } finally {
            logger.info("***** Finished testValidCredLogin *****");
        }
    }
}