package itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import itmo.SeleniumConfigurator;

/** https://github.com/{username}/{repo-name} */
public class RepositoryPage extends Page{
    @FindBy(xpath = "//strong[@itemprop='name']/a")
    private WebElement repoName;

    @FindBy(xpath = "//span[@data-content='Issues']")
    private WebElement issuesTab;

    @FindBy(xpath = "//span[@data-content='Pull requests']")
    private WebElement prTab;

    @FindBy(xpath = "//a[contains(@aria-label,'signed in to star a repository')]")
    private WebElement starButtonBefore;

    @FindBy(xpath = "//span[normalize-space()='Star']")
    private WebElement starButton;

    @FindBy(xpath = "//span[normalize-space()='Starred']")
    private WebElement starredButton;

    public RepositoryPage(SeleniumConfigurator configurator) {
        super(configurator);
        PageFactory.initElements(driver, this);
    }
    
    public void openRepository(String username, String repoName) {
        driver.get("https://github.com/" + username + '/' + repoName);
    }

    public void clickIssuesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(issuesTab)).click();
        wait.until(ExpectedConditions.urlContains("/issues"));
    }

    public void clickPRTab() {
        wait.until(ExpectedConditions.elementToBeClickable(prTab)).click();
        wait.until(ExpectedConditions.urlContains("/pulls"));
    }

    public void clickStar() {
        wait.until(ExpectedConditions.elementToBeClickable(starButton)).click();
    }

    public void clickStarred() {
        wait.until(ExpectedConditions.elementToBeClickable(starredButton)).click();
    }

    public void openLoginPageByClickStarButton() {
        wait.until(ExpectedConditions.elementToBeClickable(starButtonBefore)).click();
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    public String getRepoName() {
        return wait.until(ExpectedConditions.visibilityOf(repoName)).getText();
    }

    public boolean isStarredDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(starredButton)).isDisplayed();
    }

    public boolean isStarDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(starButton)).isDisplayed();
    }

    public boolean isOnRepositoryPage(String username, String repoName) {
    return wait.until(ExpectedConditions.urlContains("/" + username + "/" + repoName));
}

public boolean isLoggedInOnRepositoryPage() {
    return driver.findElements(By.xpath("//button[@aria-haspopup='menu' and .//img]")).stream().anyMatch(WebElement::isDisplayed);
}

}
