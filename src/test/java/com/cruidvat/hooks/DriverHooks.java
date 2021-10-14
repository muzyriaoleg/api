package com.cruidvat.hooks;

import com.cruidvat.api.specification.Specification;
import com.cruidvat.ui.driver.DriverManager;
import com.cruidvat.ui.driver.WebDriverWaiter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class DriverHooks {

    static Logger log = LogManager.getLogger(DriverHooks.class);

    @Before(order = 0)
    public void setupDriver() {
        DriverManager.getDriverInstance();
        log.info("Driver created");

    }

    @Before(order = 1)
    public void prepareSpecification() {
        Specification.setRequestSpecification();
        log.info("RestAssured specification created");
    }


    @After(order = 10)
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriverInstance())
                .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            log.info("Screenshot taken");
        }
    }

    @After(order = 0)
    public void quitDriver() {
        DriverManager.quitDriver();
        log.info("Driver closed");
    }

    @After(order = 1)
    public void clearWater() {
        WebDriverWaiter.clearWaiter();
        log.info("Waiter cleaned");
    }
}
