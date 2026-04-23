package itmo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import itmo.SeleniumConfigurator;

public abstract class Page {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor js;

    public Page(SeleniumConfigurator configurator) {
        this.driver = configurator.getDriver();
        this.wait = configurator.getWait();
        this.js = configurator.getJs();
    }
}