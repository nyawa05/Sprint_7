package services;

import io.restassured.response.Response;
import testclasses.Courier;
import testclasses.CourierId;
import testclasses.Credentials;

import static io.restassured.RestAssured.given;
import static services.Constants.*;

public class CourierRequests {
    public static Response loginCourier(Credentials credentials) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(credentials)
                .when()
                .post(BASE_URL + API_LOGIN_COURIER);
    }
    public static int getCourierId(Credentials credentials) {
        CourierId courierId = loginCourier(credentials)
                .body()
                .as(CourierId.class);
        return courierId.getId();
    }
    public static void deleteCourier(Courier courier) {
        Credentials credentials = new Credentials(courier.getLogin(), courier.getPassword());
        given()
                .header("Content-type", "application/json")
                .delete(BASE_URL + API_CREATE_COURIER + "/" + getCourierId(credentials));

    }

    public static Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(BASE_URL + API_CREATE_COURIER);
    }
}
