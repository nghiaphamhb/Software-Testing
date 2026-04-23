import static org.junit.jupiter.api.Assertions.assertEquals;
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
import itmo.pages.RepositoryPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("GitHub UI functional testing")
@Feature("Repository")
public class RepositoryTest {
    Dotenv dotenv = Dotenv.load();
    private final String USERNAME = dotenv.get("TARGET_USERNAME");
    private final String REPO_NAME = dotenv.get("TARGET_REPO");

    private static SeleniumConfigurator configurator;
    private static RepositoryPage repositoryPage;
    private static LoginPage loginPage;

    @BeforeAll
    public static void setUp() {
        configurator = new SeleniumConfigurator();
        repositoryPage = new RepositoryPage(configurator);
        loginPage = new LoginPage(configurator);
    }

    @Test
    @Order(1)
    @Story("UC-03: Checkout Public Repository")
    public void openPublicRepositoryTest() {
        repositoryPage.openRepository(USERNAME, REPO_NAME);
        assertTrue(configurator.getUrl().contains(REPO_NAME),
            "Repository URL should contain the repository name");
        assertEquals(REPO_NAME, repositoryPage.getRepoName(),
            "Repository page should display the expected repository name");
    }

    @Test
    @Order(2)
    @Story("UC-03: Checkout Public Repository")
    public void openRepositoryIssuesTabTest() {
        repositoryPage.clickIssuesTab();
        assertTrue(configurator.getUrl().contains("issues"),
            "Clicking the Issues tab should navigate to the issues page");
    }

    @Test
    @Order(3)
    @Story("UC-03: Checkout Public Repository")
    public void openRepositoryPullRequestsTabTest() {
        repositoryPage.clickPRTab();
        assertTrue(configurator.getUrl().contains("pulls"),
            "Clicking the Pull requests tab should navigate to the pulls page");
    }

    @Test
    @Order(4)
    @Story("UC-04: Star/Unstar Public Repository")
    public void starRepositoryTest() {
        repositoryPage.openRepository(USERNAME, REPO_NAME);
        repositoryPage.openLoginPageByClickStarButton();
        assertTrue(configurator.getUrl().contains("login"),
            "Clicking Star while signed out should navigate to the login page");

        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("GITHUB_USERNAME");
        String password = dotenv.get("GITHUB_PASSWORD");
        assertNotNull(username, "Environment variable GITHUB_USERNAME should be set");
        assertNotNull(password, "Environment variable GITHUB_PASSWORD should be set");

        loginPage.login(username, password);
        assertTrue(repositoryPage.isOnRepositoryPage(USERNAME, REPO_NAME),
            "After login, the browser should return to the target repository page");
        assertTrue(repositoryPage.isStarDisplayed(),
            "Repository should be in the unstarred state before star action");

        repositoryPage.clickStar();
        assertTrue(repositoryPage.isStarredDisplayed(),
            "Repository should show the Starred button after unstar action");
    }

    @Test
    @Order(5)
    @Story("UC-04:Star/Unstar Public Repository")
    public void unstarRepositoryTest() {
        assertTrue(repositoryPage.isOnRepositoryPage(USERNAME, REPO_NAME),
            "Browser should be on the target repository page before star action");
        assertTrue(repositoryPage.isLoggedInOnRepositoryPage(),
            "User should be logged in before starring a repository");
        assertTrue(repositoryPage.isStarredDisplayed(),
            "Repository should be in the starred state before unstar action");

        repositoryPage.clickStarred();
        assertTrue(repositoryPage.isStarDisplayed(),
            "Repository should show the Star button after unstar action");
    }

    @AfterAll
    public static void clearTest() {
        if (configurator != null) {
            configurator.clear();
        }
    }
}
