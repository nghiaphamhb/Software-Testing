package itmo;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This is the Selenium environment initialization clas. It contains:
 * + WebDriver
 * + WebDriverWait
 * + JavascriptExecutor
 */
public class SeleniumConfigurator {
    private static final String CHROMEDRIVER_VERSION = " 147.0.7727.102";

    private static final Duration IMPLICIT_WAIT = Duration.ZERO;
    private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(20);
    private static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration SCRIPT_TIMEOUT = Duration.ofSeconds(20);

    private static final int WINDOW_WIDTH = 1366;
    private static final int WINDOW_HEIGHT = 768;

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    /* Set up WebDriver, WebDriverWait, JavascriptExecutor */
    public SeleniumConfigurator() {
        WebDriverManager.chromedriver().driverVersion(CHROMEDRIVER_VERSION).setup();

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        this.driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT);
        driver.manage().timeouts().scriptTimeout(SCRIPT_TIMEOUT);
        driver.manage().window().setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        this.js = (JavascriptExecutor) driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public JavascriptExecutor getJs() {
        return js;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void clear() {
        if(driver != null) {
            driver.quit();
        }
    }
}
