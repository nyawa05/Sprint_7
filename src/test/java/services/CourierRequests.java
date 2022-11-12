package services;

import io.restassured.response.Response;
import pojo.Courier;
import pojo.CourierId;
import pojo.Credentials;

import static io.restassured.RestAssured.given;
import static services.Constants.*;

public class CourierRequests {
    public Response loginCourier(Credentials credentials) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .and()
                .body(credentials)
                .when()
                .post(API_LOGIN_COURIER);
    }
    public int getCourierId(Credentials credentials) {
        CourierId courierId = loginCourier(credentials)
                .body()
                .as(CourierId.class);
        return courierId.getId();
    }
    public void deleteCourier(Courier courier) {
        Credentials credentials = new Credentials(courier.getLogin(), courier.getPassword());
        given()
                .spec(REQUEST_SPECIFICATION)
                .delete(API_CREATE_COURIER + "/" + getCourierId(credentials));

    }

    public Response createCourier(Courier courier) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .and()
                .body(courier)
                .when()
                .post(API_CREATE_COURIER);
    }
}
