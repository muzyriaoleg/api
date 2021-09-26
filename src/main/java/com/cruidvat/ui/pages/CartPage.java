package com.cruidvat.ui.pages;

import com.cruidvat.constants.Urls;
import com.cruidvat.ui.fragments.ProductSummaryCard;
import java.util.Map;

public class CartPage extends Page{

    ProductSummaryCard productSummaryCard = new ProductSummaryCard();

    public CartPage() {
        super();
        setPageUrlPattern(Urls.CART_PAGE_PATTERN);
    }

    public Map<String, String> getProductsWirthQuantity() {
        return productSummaryCard.getProductsWithQuantity();
    }
}
