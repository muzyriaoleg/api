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
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriverInstance()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
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
