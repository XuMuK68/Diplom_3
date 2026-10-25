package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver createDriver(String browserName) {
        ChromeOptions options = new ChromeOptions();

        if ("chrome".equalsIgnoreCase(browserName)) {
            WebDriverManager.chromedriver().setup();

        } else if ("yandex".equalsIgnoreCase(browserName)) {
            options.setBinary(
                    "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe"
            );
            WebDriverManager.chromedriver()
                    .browserVersion("152")
                    .setup();

        } else {
            throw new IllegalArgumentException(
                    "Неизвестный браузер: " + browserName
            );
        }

        return new ChromeDriver(options);
    }
}