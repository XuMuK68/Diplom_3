package tests;

import config.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createDriver(browserName);
        driver.get("https://qa-stellarburgers.education-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}