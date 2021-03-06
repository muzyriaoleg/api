package com.cruidvat.steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import com.cruidvat.api.services.CartService;
import com.cruidvat.api.storage.SessionStorage;
import com.cruidvat.ui.driver.DriverManager;
import com.cruidvat.ui.pages.CartPage;
import com.cruidvat.utils.FileConverter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class CartSteps {

    CartService cartService = new CartService();
    CartPage cartPage = new CartPage();

    @Given("^Cart is created$")
    public void createCart() {
        SessionStorage.add("CartGuid", cartService.createCart());
    }

    @And("^Product (.+) with quantity (.+) is added to the cart$")
    public void addProductToCart(String productId, String quantity) {
        Map<String, String> product = new HashMap<>();
        product.put("productId", productId);
        product.put("quantity", quantity);
        Response response = cartService
            .addProductsToCart(SessionStorage.get("CartGuid"), product);
        response.then().assertThat()
            .body(matchesJsonSchema(
                FileConverter.fileToString("src/test/resources/json/response_product.json")));
        SoftAssertions assertions = new SoftAssertions();
        String productIdResponse = response.then().extract()
            .path("entry.product.baseOptions[0].selected.code");
        String quantityResponse = Integer.toString(response.then().extract().path("quantity"));
        assertions.assertThat(productIdResponse).isEqualTo(productId);
        assertions.assertThat(quantityResponse).isEqualTo(quantity);
        assertions.assertAll();
    }

    @When("^User open the Cart page$")
    public void openCartPage() {
        cartPage.open();
        DriverManager.clearCookies();
        DriverManager.setCookies("kvn-cart", SessionStorage.get("CartGuid"));
        DriverManager.getDriverInstance().navigate().refresh();
    }

    @Then("^Product \"(.+)\" is added with quantity (.+)$")
    public void checkProductInCart(String productName, String productQuantity) {
        SoftAssertions assertions = new SoftAssertions();
        Map<String, String> products = cartPage.getProductsWirthQuantity();
        assertions.assertThat(products.size()).isEqualTo(1);
        assertions.assertThat(products.containsKey(productName)).as("Product name doesn't match")
            .isTrue();
        assertions.assertThat(products.containsValue(productQuantity))
            .as("Product quantity doesn't  match").isTrue();
        assertions.assertAll();
    }

    @And("^Product (.+) with quantity (.+) is added to the cart using pojo$")
    public void addProductToCartPojo(String productId, int quantity) {

        Response response = cartService
            .addProductsToCartWithPojo(SessionStorage.get("CartGuid"), productId, quantity);
        response.then().assertThat()
            .body(matchesJsonSchema(
                FileConverter.fileToString("src/test/resources/json/response_product.json")));
        SoftAssertions assertions = new SoftAssertions();
        String productIdResponse = response.then().extract()
            .path("entry.product.baseOptions[0].selected.code");
        int quantityResponse = response.then().extract().path("quantity");
        assertions.assertThat(productIdResponse).isEqualTo(productId);
        assertions.assertThat(quantityResponse).isEqualTo(quantity);
        assertions.assertAll();
    }
}
