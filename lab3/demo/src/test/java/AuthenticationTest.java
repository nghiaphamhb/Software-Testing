

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.github.cdimascio.dotenv.Dotenv;
import itmo.SeleniumConfigurator;
import itmo.pages.HomePage;
import itmo.pages.LandingPage;
import itmo.pages.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("GitHub UI functional testing")
@Feature("Authentication")
public class AuthenticationTest {
    private static SeleniumConfigurator configurator;
    private static LandingPage landingPage;
    private static LoginPage loginPage;
    private static HomePage homePage;

    @BeforeAll
    public static void setUp(){
        configurator = new SeleniumConfigurator();
        landingPage = new LandingPage(configurator);
        loginPage = new LoginPage(configurator);
        homePage = new HomePage(configurator);
    }

    @Test
    @Order(1)
    @Story("UC-01: Authentication")
    public void openLoginPageFromLandingTest() {
        landingPage.open();
        assertTrue(configurator.getUrl().startsWith("https://github.com"),
            "Landing page URL should start with https://github.com");
        assertTrue(landingPage.getSignInButton().isDisplayed(),
            "Sign in button should be displayed on the landing page");

        landingPage.clickSignIn();
        assertTrue(configurator.getUrl().contains("login"),
            "Clicking Sign in should navigate to the login page");
        assertTrue(loginPage.getUsernameInput().isDisplayed(),
            "Username input should be displayed on the login page");
        assertTrue(loginPage.getPasswordInput().isDisplayed(),
            "Password input should be displayed on the login page");
    }

    @Test
    @Order(2)
    @Story("UC-01: Authentication")
    public void invalidLoginTest() {
        loginPage.login("fakeUsername", "fakePassword");

        assertTrue(loginPage.getErrorMessageText().contains("Incorrect username or password."),
            "Invalid login should display an incorrect username or password error");
        assertTrue(configurator.getUrl().contains("session"),
            "Invalid login should stay on the session page");
    }

    @Test
    @Order(3)
    @Story("UC-01: Authentication")
    public void validLoginTest() {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("GITHUB_USERNAME");
        String password = dotenv.get("GITHUB_PASSWORD");

        assertNotNull(username, "Environment variable GITHUB_USERNAME should be set");
        assertNotNull(password, "Environment variable GITHUB_PASSWORD should be set");

        loginPage.login(username, password);

        assertTrue(homePage.isUserMenuDisplayed(),
            "Successful login should display the user menu");
    }

    @Test
    @Order(4)
    @Story("UC-01: Authentication")
    public void logOut() {
        homePage.signOut();    
        assertTrue(landingPage.getSignInButton().isDisplayed(),
            "Sign out should return the page to the signed-out state");
    }

    @AfterAll
    public static void clearTest() {
        if (configurator != null) {
            configurator.clear();
        }
    }
}
