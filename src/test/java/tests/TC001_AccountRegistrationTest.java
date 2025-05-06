package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountRegistrationPage;
import pages.HomePage;
import tests.base.BaseTest;

public class TC001_AccountRegistrationTest extends BaseTest {

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
        String password = randomAlphaNumeric(5, 3);
        accountRegistrationPage.setPassword(password);
        accountRegistrationPage.setPasswordConfirm(password);
        accountRegistrationPage.clickPrivacyPolicyCheckbox();
        accountRegistrationPage.clickContinueButton();

        Assert.assertEquals(accountRegistrationPage.getConfirmationMessage(), "Your Account Has Been Created!");
    }
}