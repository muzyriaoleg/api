package com.cruidvat.ui.pages;

import com.cruidvat.constants.Urls;
import com.cruidvat.ui.driver.DriverManager;
import com.cruidvat.ui.driver.WebDriverWaiter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public abstract class Page {

    protected WebDriver driver = DriverManager.getDriverInstance();
    private String pageUrl;
    private String pageUrlPattern;

    protected Page() {
        setPageUrl(Urls.BASE_URL);
    }
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrlPattern(String pageUrlPattern) {
        this.pageUrlPattern = pageUrlPattern;
    }

    public String getPageUrlPattern() {
        return pageUrlPattern;
    }

    public Page open() {
        driver.get(getPageUrl() + getPageUrlPattern());
        return this;
    }

    private ExpectedCondition<Boolean> jQueryIsLoaded() {
        return webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return jQuery.active").toString()
            .equals("0");
    }
}
