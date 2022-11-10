import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static services.Constants.*;

public class GetOrdersTest {
    @Test
    @DisplayName("Check status code and the list of getting orders")
    @Description("Check correct status code (200) and a list of orders when getting all orders")
    public void getAllOrdersTest () {
        String actual = given()
                .header("Content-type", "application/json")
                .when()
                .get(BASE_URL + API_GET_ORDER)
                .then().statusCode(200)
                .extract().path("orders").toString();
        Assert.assertTrue(actual != null);
    }
}
