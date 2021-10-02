package com.cruidvat.hooks;

import com.cruidvat.api.specification.Specification;
import com.cruidvat.ui.driver.DriverManager;
import com.cruidvat.ui.driver.WebDriverWaiter;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHooks {

    @Before(order = 0)
    public void setupDriver() {
        DriverManager.getDriverInstance();

    }

    @Before(order = 1)
    public void prepareSpecification() {
        Specification.setRequestSpecification();
    }

    @After
    public void quitDriver() {
        DriverManager.quitDriver();
    }

    @After
    public void clearWater() {
        WebDriverWaiter.clearWaiter();
    }
}
