package itmo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import itmo.SeleniumConfigurator;

/** Other user's profile page 
 * https://github.com/{username}
*/
public class UserPage extends Page {
    @FindBy(xpath = "//span[@itemprop='name']")
    private WebElement profileName;

    @FindBy(xpath = "(//span[contains(@class,'user-following-container')]//a[normalize-space()='Follow'])[3]")
    private WebElement beforeLoginFollowButton;

    @FindBy(xpath = "//input[@type='submit' and @name='commit' and @value='Follow']")
    private WebElement followButton;

    @FindBy(xpath = "//input[@type='submit' and @name='commit' and @value='Unfollow']")
    private WebElement unfollowButton;

    @FindBy(xpath = "//button[@aria-haspopup='menu' and .//img[@data-testid='github-avatar']]")
    private WebElement userMenuButton;

    public UserPage(SeleniumConfigurator configurator) {
        super(configurator);
        PageFactory.initElements(driver, this);
    }

    public void openUserProfile(String url) {
        driver.get("https://github.com/" + url);
    }

    public void clickFollow() {
        wait.until(ExpectedConditions.elementToBeClickable(followButton)).click();
    }

    public void clickUnfollow() {
        wait.until(ExpectedConditions.elementToBeClickable(unfollowButton)).click();
    }

    public void openLoginPageByClickFollowButton() {
        wait.until(ExpectedConditions.elementToBeClickable(beforeLoginFollowButton)).click();
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    public boolean isProfileNameDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(profileName)).isDisplayed();
    }

    public boolean isFollowDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(followButton)).isDisplayed();
    }

    public boolean isUnfollowDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(unfollowButton)).isDisplayed();
    }

    public boolean isUserMenuDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(userMenuButton)).isDisplayed();
    } 
}
