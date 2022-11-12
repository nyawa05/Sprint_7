import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import services.CourierGenerator;
import services.CourierRequests;
import pojo.*;

public class CreateCourierTest {
    Courier courier = CourierGenerator.randomCourier();
    CourierRequests courierRequests = new CourierRequests();

    @Test
    @DisplayName("Positive check of creating courier")
    @Description("Check correct status code (201) and OK message when create new courier. Positive test.")
    public void createCourierPositiveTest () {
        String actual = courierRequests.createCourier(courier)
                .then().statusCode(201).extract().path("ok").toString();
        Assert.assertEquals("true", actual);
    }
    @Test
    @DisplayName("Positive check of creating courier")
    @Description("Check correct status code (201) and OK message when create new courier with only required fields. Positive test.")
    public void createCourierPositiveTestAllNecessaryFields() {
        courier.setFirstName("");
        String actual = courierRequests.createCourier(courier)
                .then().statusCode(201).extract().path("ok").toString();
        Assert.assertEquals("true", actual);;
    }
    @Test
    @DisplayName("Negative check of creating courier")
    @Description("Check correct status code (409) and error message when create new courier with existing login")
    public void createCourierNegativeTestDoubleValues() {
        courierRequests.createCourier(courier);
        String actual = courierRequests.createCourier(courier)
                .then().statusCode(409).extract().path("message").toString();
        Assert.assertEquals("Этот логин уже используется. Попробуйте другой.", actual);;
    }
    @After
    public void deleteData () {
        courierRequests.deleteCourier(courier);
    }
}
