package itmo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import itmo.SeleniumConfigurator;

public class LandingPage extends Page {
    @FindBy(xpath = "//div[contains(@class,'HeaderMenu-link-wrap')]//a[@href='/login' and normalize-space()='Sign in']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[contains(@class,'color-fg-muted')]/*[local-name()='svg' and contains(@class,'octicon-search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='query-builder-test']")
    private WebElement searchInput;

    public LandingPage(SeleniumConfigurator configurator) {
        super(configurator);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://github.com/");
        wait.until(ExpectedConditions.urlContains("github.com"));
    }

    public void clickSignIn() {
        getSignInButton().click();
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    public void search(String keyword) {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys(keyword, Keys.ENTER);
        wait.until(ExpectedConditions.urlContains("search"));
    }

    public WebElement getSignInButton() {
        return signInButton;
    }
}
