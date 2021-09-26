package com.cruidvat.hooks;

import com.cruidvat.api.specification.Specification;
import com.cruidvat.ui.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHooks {

    @Before
    public void setupDriver() {
        DriverManager.getDriverInstance();
        Specification.setRequestSpecification();
    }

    @After
    public void quitDriver() {
        DriverManager.quitDriver();
    }
}
