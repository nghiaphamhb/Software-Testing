package itmo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import itmo.SeleniumConfigurator;

/** https://github.com/login */
public class LoginPage extends Page{
    @FindBy(xpath = "//input[@name='login']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name='commit']")
    private WebElement signInSubmitButton;

    @FindBy(xpath = "//div[contains(@class,'js-flash-alert')]")
    private WebElement loginErrorMessage;

    public LoginPage(SeleniumConfigurator configurator) {
        super(configurator);
        PageFactory.initElements(driver, this);
    }
    
    /* Simple actions */
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).clear();
        passwordInput.sendKeys(password);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.
            elementToBeClickable(signInSubmitButton)).click();
    }

    /* Complex actions */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }

    public String getErrorMessageText(){
        return wait.until(ExpectedConditions.
            visibilityOf(loginErrorMessage)).getText();
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }
}
