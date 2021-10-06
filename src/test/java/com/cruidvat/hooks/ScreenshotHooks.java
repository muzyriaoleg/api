package com.cruidvat.hooks;

import com.cruidvat.ui.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHooks {

    public static final String PNG_FILE_EXTENSION = "image/png";
    static Logger log = LogManager.getLogger(ScreenshotHooks.class);

    @After(order = 0)
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriverInstance())
                .getScreenshotAs(
                    OutputType.BYTES);
            scenario.attach(screenshot, PNG_FILE_EXTENSION, scenario.getName());
            log.info("Screenshot taken");
        }

    }
}
