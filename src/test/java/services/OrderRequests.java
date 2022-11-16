package services;

import io.restassured.response.Response;
import pojo.Order;
import pojo.OrderId;

import static io.restassured.RestAssured.given;
import static services.Constants.*;

public class OrderRequests {
    public static void cancelOrder(OrderId order) {
        given()
                .spec(REQUEST_SPECIFICATION)
                .and()
                .body(order)
                .when()
                .put(API_CANCEL_ORDER);
    }
    public static Response createOrder(Order order) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .and()
                .body(order)
                .when()
                .post(API_CREATE_ORDER);
    }
}
