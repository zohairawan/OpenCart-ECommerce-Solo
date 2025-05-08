package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class HomePage extends BasePage {

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//a[@title='My Account']")
    public WebElement myAccountDropdown;
    @FindBy(xpath = "//a[normalize-space()='Register']")
    public WebElement registerLink;
    @FindBy(xpath = "//a[normalize-space()='Login']")
    public WebElement loginLink;

    // Action Methods
    public void clickMyAccountDropdown() {
        myAccountDropdown.click();
    }
    public void clickRegisterLink() {registerLink.click();}
    public void clickLoginLink(){loginLink.click();}
}