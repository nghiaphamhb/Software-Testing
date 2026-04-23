package itmo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import itmo.SeleniumConfigurator;

/** https://github.com/{username} */
public class ProfilePage extends Page{
    @FindBy(xpath = "//div[contains(@class,'js-user-profile-bio')]")
    private WebElement bioText;

    @FindBy(xpath = "//button[@type='button' and normalize-space()='Edit profile']")
    private WebElement editProfileButton;

    @FindBy(xpath = "//textarea[@name='user[profile_bio]']")
    private WebElement bioInput;

    @FindBy(xpath = "//span[contains(@class,'Button-label') and normalize-space()='Save']")
    private WebElement saveButton;

    public ProfilePage(SeleniumConfigurator configurator) {
        super(configurator);
        PageFactory.initElements(driver, this);
    }

    public void openEditProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(editProfileButton)).click();
    }

    public void updateBio(String newBio) {
        openEditProfile();
        wait.until(ExpectedConditions.elementToBeClickable(bioInput)).clear();
        bioInput.sendKeys(newBio);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.attributeToBe(bioText, "data-bio-text", newBio));
    }

    public String getBioText() {
        return wait.until(ExpectedConditions.visibilityOf(bioText)).getText();
    }

    public boolean isDisplayedEditProfileButton(){
        return wait.until(ExpectedConditions.visibilityOf(editProfileButton)).isDisplayed();
    }
}
