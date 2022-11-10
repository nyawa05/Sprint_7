import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import services.CourierRequests;
import testclasses.*;

public class CreateCourierTest {
    Courier courier = new Courier("tamarka666","12345678","Tamara");

    @Test
    @DisplayName("Check status code of creating courier")
    @Description("Check correct status code (201) when create new courier. Positive test.")
    public void createCourierPositiveTestGetStatusCode () {
        CourierRequests.createCourier(courier)
                .then().statusCode(201);
    }
    @Test
    @DisplayName("Check status code of creating courier")
    @Description("Check correct status code (201) when create new courier with only required fields. Positive test.")
    public void createCourierPositiveTestAllNecessaryFieldsGetStatusCode () {
        courier.setFirstName("");
        CourierRequests.createCourier(courier)
                .then().statusCode(201);
    }
    @Test
    @DisplayName("Check message of creating courier")
    @Description("Check correct message when create new courier. Positive test.")
    public void createCourierPositiveTestGetMessage () {
        CourierOkMessage courierOkMessage = CourierRequests.createCourier(courier)
                .body()
                .as(CourierOkMessage.class);
        Assert.assertTrue(courierOkMessage.isOk());
    }
    @Test
    @DisplayName("Check status code of creating courier")
    @Description("Check correct status code (409) when create new courier with existing login")
    public void createCourierNegativeTestDoubleValuesGetStatusCode() {
        CourierRequests.createCourier(courier);
        CourierRequests.createCourier(courier)
                .then().statusCode(409);
    }
    @Test
    @DisplayName("Check message of creating courier")
    @Description("Check correct message when create new courier with existing login")
    public void createCourierNegativeTestDoubleValuesGetMessage() {
        CourierRequests.createCourier(courier);
        CourierNotOkMessage courierNotOkMessage = CourierRequests.createCourier(courier)
                .body()
                .as(CourierNotOkMessage.class);
        Assert.assertEquals("Этот логин уже используется. Попробуйте другой.", courierNotOkMessage.getMessage());
    }
    @After
    public void deleteData () {
        CourierRequests.deleteCourier(courier);
    }
}
