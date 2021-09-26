package com.cruidvat.ui.fragments;

import com.cruidvat.ui.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Fragment {
    private WebElement rootElement;
    protected WebDriver driver = DriverManager.getDriverInstance();

    public void setRootElement(WebElement element) {
        this.rootElement = element;
    }

    public WebElement getRootElement() {
        return rootElement;
    }

    protected Fragment() {
        PageFactory.initElements(driver, this);
    }

    protected Fragment(WebElement element) {
        this.rootElement = element;
        PageFactory.initElements(driver, this);
    }

    public void jsScrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}
