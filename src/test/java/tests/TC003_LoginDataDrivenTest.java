package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import tests.base.BaseTest;
import utils.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseTest {

    @Test(dataProvider = "LoginTest", dataProviderClass = DataProviders.class, groups = {"datadriven", "master"})
    public void testLogin(String email, String pwd, String expectedResult) {
        logger.info("***** Started testLogin *****");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccountDropdown();
            logger.info("Clicked on 'Account' dropdown");
            homePage.clickLoginLink();
            logger.info("Clicked on 'Register' link");

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Providing login credentials");
            loginPage.typeEmailAddress(email);
            loginPage.typePassword(pwd);
            loginPage.clickLoginButton();
            logger.info("Clicked on Login button");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean loginIsSuccessful = myAccountPage.myAccountHeaderIsDisplayed();

            if (expectedResult.equalsIgnoreCase("Valid")) {
                logger.info("Valid credentials provided");
                // Data is Valid -> Login success -> Test pass - Logout
                if (loginIsSuccessful) {
                    myAccountPage.clickLogoutButton();
                    logger.info("Login Passed");
                    Assert.assertTrue(true);
                } else {
                    // Data is Valid -> Login fail -> Test fail
                    logger.info("Login Failed");
                    Assert.fail();
                }
            }

            if (expectedResult.equalsIgnoreCase("Invalid")) {
                logger.info("Invalid credentials provided");
                // Data is Invalid -> Login fail -> Test pass
                if (!loginIsSuccessful) {
                    logger.info("Login Failed");
                    Assert.assertTrue(true);
                } else {
                    // Data is Invalid -> Login success -> Test fail - Logout
                    myAccountPage.clickLogoutButton();
                    logger.info("Login Passed");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Assert.fail();
        } finally {
            logger.info("***** Finished testLogin *****");
        }
    }
}