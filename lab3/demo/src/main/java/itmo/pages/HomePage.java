package itmo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import itmo.SeleniumConfigurator;

/** https://github.com/dashboard */
public class HomePage extends Page {
    @FindBy(xpath = "//button[@aria-haspopup='menu' and .//img[@data-testid='github-avatar']]")
    private WebElement userMenuButton;
    
    @FindBy(xpath = "//span[text()='Profile']")
    private WebElement profileButton;

    @FindBy(xpath = "//span[text()='Sign out']")
    private WebElement signOutButton;

    @FindBy(xpath = "//input[@type='submit' and @name='commit' and @value='Sign out']")
    private WebElement logOutButton;

    @FindBy(xpath = "(//div[contains(@class,'search-title')]//a)[1]")
    private WebElement bestSearchResult;

    public HomePage(SeleniumConfigurator configurator) {
        super(configurator);
        PageFactory.initElements(driver, this);
    }
    
    public void openUserMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(userMenuButton)).click();
    }

    public void openYourProfile() {
        openUserMenu();
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void signOut() {
        openUserMenu();
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
    }

    public void openBestSearchResult(){
        wait.until(ExpectedConditions.elementToBeClickable(bestSearchResult)).click();
    }

    public boolean isUserMenuDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(userMenuButton)).isDisplayed();
    } 
}
