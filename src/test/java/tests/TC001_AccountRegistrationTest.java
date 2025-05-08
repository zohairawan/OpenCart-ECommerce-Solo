package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountRegistrationPage;
import pages.HomePage;
import tests.base.BaseTest;

public class TC001_AccountRegistrationTest extends BaseTest {

    @Test
    public void verifyAccountRegistrationTest() {
        logger.info("***** Started verifyAccountRegistrationTest *****");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccountDropdown();
            logger.info("Clicked on 'Account' dropdown");
            homePage.clickRegisterLink();
            logger.info("Clicked on 'Register' link");

            AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);
            logger.info("Providing new account registration details...");
            accountRegistrationPage.setFirstName(randomString(5).toUpperCase());
            accountRegistrationPage.setLastName(randomString(4).toUpperCase());
            accountRegistrationPage.setEmail(randomString(5) + "@gmail.com");
            accountRegistrationPage.setPhoneNumber(randomNumber(10));
            String password = randomAlphaNumeric(5, 3);
            accountRegistrationPage.setPassword(password);
            accountRegistrationPage.setPasswordConfirm(password);
            accountRegistrationPage.clickPrivacyPolicyCheckbox();
            accountRegistrationPage.clickContinueButton();

            String actualConfirmationMessage = accountRegistrationPage.getConfirmationMessage();
            String expectedConfirmationMessage = "Your Account Has Been Created!";
            logger.info("Validating actual message");
            if (!actualConfirmationMessage.equals(expectedConfirmationMessage)) {
                logger.error("'TEST FAILED': Expected '" + expectedConfirmationMessage + "' but got '" + actualConfirmationMessage + "'");
                logger.debug("Debugging info: Check if the registration flow has changed");
            }
            Assert.assertEquals(actualConfirmationMessage, expectedConfirmationMessage);
            logger.info("Test Passed!");
        } catch (Exception e) {
            logger.error("'TEST FAILED': " + e.getMessage());
            Assert.fail("'TEST FAILED': " + e.getMessage());

        } finally {
            logger.info("***** Finished verifyAccountRegistrationTest *****");
        }
    }
}