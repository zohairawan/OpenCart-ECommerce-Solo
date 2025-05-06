package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AccountRegistrationPage extends BasePage {

    // Constructor
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//input[@id='input-firstname']")
    public WebElement firstNameField;
    @FindBy(xpath = "//input[@id='input-lastname']")
    public WebElement lastNameField;
    @FindBy(xpath = "//input[@id='input-email']")
    public WebElement emailField;
    @FindBy(xpath = "//input[@id='input-telephone']")
    public WebElement phoneNumField;
    @FindBy(xpath = "//input[@id='input-password']")
    public WebElement passwordField;
    @FindBy(xpath = "//input[@id='input-confirm']")
    public WebElement passwordConfirmField;
    @FindBy(xpath = "//input[@value='Continue']")
    public WebElement continueButton;
    @FindBy(xpath = "//input[@name='agree']")
    public WebElement privacyPolicyCheckbox;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    public WebElement confirmationMsg;

    // Action Methods
    public void setFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumField.sendKeys(phoneNumber);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void setPasswordConfirm(String passwordConfirm) {
        passwordConfirmField.sendKeys(passwordConfirm);
    }

    public void clickPrivacyPolicyCheckbox() {
        privacyPolicyCheckbox.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public String getConfirmationMessage() {
        try {
            return confirmationMsg.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}