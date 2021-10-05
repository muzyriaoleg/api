package com.cruidvat.hooks;

import com.cruidvat.ui.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHooks {

    public static final String PNG_FILE_EXTENSION = "image/png";

    @After(order = 0)
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriverInstance())
                .getScreenshotAs(
                    OutputType.BYTES);
            scenario.attach(screenshot, PNG_FILE_EXTENSION, scenario.getName());
        }

    }
}
