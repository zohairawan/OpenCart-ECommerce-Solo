package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    // Constructor
    public LoginPage(WebDriver driver) {super(driver);}

    // Locators
    @FindBy(xpath = "")
    public WebElement emailAddressField;
    @FindBy(xpath = "")
    public WebElement passwordField;
    @FindBy(xpath = "")
    public WebElement loginButton;

    // Action methods
}
