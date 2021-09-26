package com.cruidvat.api.specification;

import static com.cruidvat.constants.Urls.BASE_URL;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class Specification {

    public static void setRequestSpecification() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .build();
    }
}
