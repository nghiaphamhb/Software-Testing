import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

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
import itmo.pages.ProfilePage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("GitHub UI functional testing")
@Feature("Profile")
public class ProfileTest {
    private static SeleniumConfigurator configurator;
    private static LandingPage landingPage;
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static ProfilePage profilePage;


    @BeforeAll
    public static void setUp() {
        configurator = new SeleniumConfigurator();
        landingPage = new LandingPage(configurator);
        loginPage = new LoginPage(configurator);
        homePage = new HomePage(configurator);
        profilePage = new ProfilePage(configurator);
    }

    @Test
    @Order(1)
    @Story("UC-08: Open own profile")
    public void openOwnProfileFromUserMenuTest() {
        landingPage.open();
        assertTrue(configurator.getUrl().startsWith("https://github.com"),
            "Landing page URL should start with https://github.com");

        landingPage.clickSignIn();
        assertTrue(configurator.getUrl().contains("login"),
            "Clicking Sign in should navigate to the login page");

        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("GITHUB_USERNAME");
        String password = dotenv.get("GITHUB_PASSWORD");

        assertNotNull(username, "Environment variable GITHUB_USERNAME should be set");
        assertNotNull(password, "Environment variable GITHUB_PASSWORD should be set");

        loginPage.login(username, password);
        assertTrue(homePage.isUserMenuDisplayed(),"Successful login should display the user menu");
        
        homePage.openYourProfile();
        assertTrue(profilePage.isDisplayedEditProfileButton(), "Profile page should have Edit profile button");
    }

    @Test
    @Order(2)
    @Story("UC-08: Edit profile bio")
    public void editProfileBioTest() {
        assertTrue(profilePage.isDisplayedEditProfileButton(), "Profile page should have Edit profile button before editing bio");

        String originalBio = profilePage.getBioText();
        String newBio = createRandomBio();

        profilePage.updateBio(newBio);
        assertEquals(newBio, profilePage.getBioText(), "Bio should be updated");

        profilePage.updateBio(originalBio);
        assertEquals(originalBio, profilePage.getBioText(), "Bio should be restored");
    }

    @AfterAll
    public static void clearTest() {
        if (configurator != null) {
            configurator.clear();
        }
    }

    private String createRandomBio() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(1000));
    }
}
