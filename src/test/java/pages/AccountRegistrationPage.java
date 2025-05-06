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

    // Action Methods
}