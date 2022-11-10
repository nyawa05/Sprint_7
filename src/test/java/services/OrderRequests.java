package services;

import io.restassured.response.Response;
import testclasses.Order;
import testclasses.OrderId;

import static io.restassured.RestAssured.given;
import static services.Constants.*;

public class OrderRequests {
    public static void cancelOrder(OrderId order) {
        given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .put(BASE_URL + API_CANCEL_ORDER);
    }
    public static Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(BASE_URL + API_CREATE_ORDER);
    }
}
