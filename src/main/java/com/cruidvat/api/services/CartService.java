package com.cruidvat.api.services;

import static com.cruidvat.constants.Urls.ADD_PRODUCT_TO_CART;
import static com.cruidvat.constants.Urls.NEW_CART;
import static io.restassured.RestAssured.given;

import com.cruidvat.api.models.ProductPayload;
import com.cruidvat.utils.TemplateResolver;
import io.restassured.response.Response;
import java.io.File;
import java.util.Map;

public class CartService {

    private String postProductJsonFilePath = "src/test/resources/templates/post_product.txt";

    public String createCart() {
        return given()
            .basePath(NEW_CART)
            .post()
            .then()
            .extract()
            .path("guid");
    }

    public Response addProductsToCart(String cartGuid, Map<String, String> product) {
        String body = TemplateResolver.set(new File(postProductJsonFilePath), product);
        return given()
            .basePath(ADD_PRODUCT_TO_CART.replaceAll("\\{\\{.*}}", cartGuid))
            .body(body)
            .post();
    }

    public Response addProductsToCartWithPojo(String cartGuid, String productCode, int productQuantity) {
        ProductPayload body = ProductPayload.newBuilder()
            .setProductCode(productCode)
            .setQuantity(productQuantity)
            .build();
        return given()
            .basePath(ADD_PRODUCT_TO_CART.replaceAll("\\{\\{.*}}", cartGuid))
            .body(body)
            .post();
    }

}
