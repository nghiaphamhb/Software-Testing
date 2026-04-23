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
import itmo.pages.HomePage;
import itmo.pages.LandingPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("GitHub UI functional testing")
@Feature("Search")
public class SearchTest {
    private static final String KEY_WORD = Dotenv.load().get("TARGET_USERNAME");

    private static SeleniumConfigurator configurator;
    private static LandingPage landingPage;
    private static HomePage homePage;

    @BeforeAll
    public static void setUp() {
        configurator = new SeleniumConfigurator();
        landingPage = new LandingPage(configurator);
        homePage = new HomePage(configurator);
    }

    @Test
    @Order(1)
    @Story("UC-02: Find GitHub Content")
    public void searchRepositoryByKeywordTest() {
        landingPage.open();
        landingPage.search(KEY_WORD);
        assertTrue(configurator.getUrl().contains("q=" + KEY_WORD),
            "Search results URL should contain the submitted keyword query");
    }

    @Test
    @Order(2)
    @Story("UC-02: Find GitHub Content")
    public void openBestSearchResult() {
        homePage.openBestSearchResult();
        assertTrue(configurator.getUrl().startsWith("https://github.com/" + KEY_WORD),
            "Opening the best search result should navigate to the expected repository");
    }

    @AfterAll
    public static void clearTest() {
        if (configurator != null) {
            configurator.clear();
        }
    }
}
