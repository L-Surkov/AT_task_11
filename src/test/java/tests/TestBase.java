package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {


    @BeforeAll
    static void setupEnvironment() {
        Configuration.browser = System.getenv("BROWSER_NAME");
        Configuration.browserVersion = System.getenv("BROWSER_VERSION");
        String browserSize = System.getenv("BROWSER_SIZE");

        if (browserSize != null && !browserSize.isEmpty()) {
            Configuration.browserSize = browserSize;
        } else {
            Configuration.browserSize = "1920x1080";
        }

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getenv("SELENOID_WD_URL");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

}
