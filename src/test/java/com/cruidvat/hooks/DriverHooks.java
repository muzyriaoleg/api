package com.cruidvat.hooks;

import com.cruidvat.api.specification.Specification;
import com.cruidvat.ui.driver.DriverManager;
import com.cruidvat.ui.driver.WebDriverWaiter;
import com.epam.reportportal.message.ReportPortalMessage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class DriverHooks {

    private final String SCREENSHOT_PATH_TEMPLATE ="src/test/resources/screenshots/%s.png";
    static Logger log = LogManager.getLogger(DriverHooks.class);

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
            File screenshot = createScreenshotFile();
            log.info(createReportPortalMessage(screenshot));
        }
    }

    @After(order = 0)
    public void quitDriver() {
        DriverManager.quitDriver();
    }

    @After(order = 1)
    public void clearWater() {
        WebDriverWaiter.clearWaiter();
    }

    private File createScreenshotFile() {
        File screenshot = ((TakesScreenshot) DriverManager.getDriverInstance())
            .getScreenshotAs(
                OutputType.FILE);
        try {
            String path = String.format(SCREENSHOT_PATH_TEMPLATE, generateScreenshotFileName());
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            log.error("Failed to create screenshot file", e);
        }
        return screenshot;
    }

    private ReportPortalMessage createReportPortalMessage(File attachment) {
        ReportPortalMessage message = new ReportPortalMessage("Corrupted message");
        try {
            message = new ReportPortalMessage(attachment, "Screenshot taken");
        } catch (IOException e) {
            log.error("Failed to create Report portal message", e);
        }
        return message;
    }

    private String generateScreenshotFileName() {
        LocalDateTime time = LocalDateTime.now();
        String formattedDateTime = time.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
        return "screenshot_" + formattedDateTime;
    }
}
