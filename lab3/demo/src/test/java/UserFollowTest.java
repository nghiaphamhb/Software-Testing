import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import itmo.pages.LoginPage;
import itmo.pages.UserPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("GitHub UI functional testing")
@Feature("User follow")
public class UserFollowTest {
    private static final String TARGET_USERNAME = Dotenv.load().get("TARGET_USERNAME");

    private static SeleniumConfigurator configurator;
    private static LoginPage loginPage;
    private static UserPage userPage;

    @BeforeAll
    public static void setUp() {
        configurator = new SeleniumConfigurator();
        loginPage = new LoginPage(configurator);
        userPage = new UserPage(configurator);
    }

    @Test
    @Order(1)
    @Story("UC-09: Open public user profile")
    public void openPublicUserProfileTest() {
        userPage.openUserProfile(TARGET_USERNAME);

        assertTrue(configurator.getUrl().contains(TARGET_USERNAME), "Browser should open the target public user profile");
        assertTrue(userPage.isProfileNameDisplayed(), "Should see the profile's name");
    }

    @Test
    @Order(2)
    @Story("UC-09: Follow and unfollow user")
    public void followAndUnfollowUserTest() {
        userPage.openLoginPageByClickFollowButton();
        assertTrue(configurator.getUrl().contains("login"));

        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("GITHUB_USERNAME");
        String password = dotenv.get("GITHUB_PASSWORD");

        assertNotNull(username, "Environment variable GITHUB_USERNAME should be set");
        assertNotNull(password, "Environment variable GITHUB_PASSWORD should be set");

        loginPage.login(username, password);
        assertTrue(userPage.isUserMenuDisplayed(), "Successful login should display the user menu");
        assertTrue(configurator.getUrl().contains(TARGET_USERNAME), "Browser should open the target public user profile");

        userPage.clickFollow();
        assertTrue(userPage.isUnfollowDisplayed(), "Successful follow should display the unfollow button");

        userPage.clickUnfollow();
        assertTrue(userPage.isFollowDisplayed(), "Successful unfollow should display the follow button");
    }

    @AfterAll
    public static void clearTest() {
        if (configurator != null) {
            configurator.clear();
        }
    }
}
