package com.cruidvat.ui.fragments;

import com.cruidvat.ui.driver.WebDriverWaiter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductSummaryCard extends Fragment {

    @FindBy(css = ".product-summary__description-name")
    List<WebElement> productNames;

    @FindBy(css = ".select__selected-option")
    List<WebElement> productQuantities;

    public Map<String, String> getProductsWithQuantity() {
        WebDriverWaiter.driverWait().until(ExpectedConditions
            .presenceOfElementLocated(By.cssSelector(".product-summary__description-name")));
        Map<String, String> map = new HashMap<>();
        for (WebElement productName : productNames) {
            for (WebElement productQuantity : productQuantities) {
                jsScrollIntoView(productName);
                jsScrollIntoView(productQuantity);
                map.put(productName.getText(), productQuantity.getText());
            }
        }
        return map;
    }
}
