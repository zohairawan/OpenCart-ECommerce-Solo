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

    // Action methods
    public boolean myAccountHeaderIsDisplayed() {
        try {
            return myAccountHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
