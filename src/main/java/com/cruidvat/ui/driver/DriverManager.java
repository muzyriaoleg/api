package com.cruidvat.ui.driver;

import static java.time.temporal.ChronoUnit.YEARS;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriverInstance() {
        if (driver == null) {
            createDriver();
            setupDriver();
        }
        return driver;
    }

    private void WebDriver() {
    }

    private static void createDriver() {
        String browser = System.getProperty("browser");
        switch (browser.toUpperCase()) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver",
                    "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver",
                    "src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("This driver is not supported");
        }
    }

    private static void setupDriver() {
        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }

    public static void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    public static void setCookies(String name, String value) {
        Cookie cookie = new Cookie.Builder(name, value)
            .domain("www.kruidvat.nl")
            .expiresOn(Date.from(ZonedDateTime.now().plus(1, YEARS).toInstant()))
            .path("/")
            .isHttpOnly(false)
            .isSecure(true)
            .build();
        driver.manage().addCookie(cookie);
    }
}
