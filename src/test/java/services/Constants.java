package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Constants {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String API_CREATE_COURIER = "/api/v1/courier";
    public static final String API_LOGIN_COURIER = "/api/v1/courier/login";
    public static final String API_CANCEL_ORDER = "/api/v1/orders/cancel";
    public static final String API_CREATE_ORDER = "/api/v1/orders";
    public static final String API_GET_ORDER = "/api/v1/orders";

    public static RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .addHeader("Content-type", "application/json")
            .setBaseUri(BASE_URL)
            .build();
}
