package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class MyAccountPage extends BasePage {

    // Constructor
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    public WebElement myAccountHeader;

    @FindBy(xpath = "(//a[normalize-space()='Logout'])[2]")
    public WebElement logoutButton;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    public WebElement continueAfterLogoutButton;

    // Action methods
    public boolean myAccountHeaderIsDisplayed() {
        try {
            return myAccountHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void clickContinueAfterLogoutButton() {
        continueAfterLogoutButton.click();
    }
}