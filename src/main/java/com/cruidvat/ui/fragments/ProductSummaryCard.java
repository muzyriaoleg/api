package com.cruidvat.ui.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductSummaryCard extends Fragment {

    @FindBy(css = ".product-summary__description-name")
    List<WebElement> productNames;

    @FindBy(css = ".select__selected-option")
    List<WebElement> productQuantities;

    public Map<String, String> getProductsWithQuantity() {
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
