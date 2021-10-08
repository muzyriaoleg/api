package com.cruidvat.hooks;

import com.cruidvat.api.specification.Specification;
import com.cruidvat.ui.driver.DriverManager;
import com.cruidvat.ui.driver.WebDriverWaiter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Slf4j
public class DriverHooks {

    @Before(order = 0)
    public void setupDriver() {
        DriverManager.getDriverInstance();

    }

    @Before(order = 1)
    public void prepareSpecification() {
        Specification.setRequestSpecification();
    }

    @After(order = 10)
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) DriverManager.getDriverInstance()).getScreenshotAs(
                OutputType.FILE);
            try {
                FileUtils
                    .copyFile(screenshot, new File("src/test/resources/screenshots/sr1s7ht.png"));
            } catch (IOException e) {
                log.error("Failed to create screenshot file", e);
            }
            log.info("RP_MESSAGE#FILE#{}#{}", screenshot.getAbsolutePath(),
                "Screenshot taken");
        }

    }

    @After(order = 1)
    public void quitDriver() {
        DriverManager.quitDriver();
    }

    @After(order = 2)
    public void clearWater() {
        WebDriverWaiter.clearWaiter();
    }
}
