import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.CourierRequests;
import testclasses.Courier;
import testclasses.Credentials;

public class LoginCourierTest {
    Courier courier = new Courier("tamarka666","12345678","Tamara");

    @Before
    public void getData() {
        CourierRequests.createCourier(courier);
    }

    @Test
    @DisplayName("Check status code of logging courier")
    @Description("Check correct status code(200) when logging courier. Positive test.")
    public void loginCourierPositiveTestGetStatusCode() {
        Credentials credentials = new Credentials("tamarka666","12345678");
        CourierRequests.loginCourier(credentials)
                .then().statusCode(200);
    }

    @Test
    @DisplayName("Check courier id after logging courier")
    @Description("Check that after logging courier message have correct not null courier id. Positive test.")
    public void loginCourierPositiveTestGetCourierId() {
        Credentials credentials = new Credentials("tamarka666","12345678");
        Assert.assertTrue(CourierRequests.getCourierId(credentials) != 0);
    }

    @After
    public void deleteData () {
        CourierRequests.deleteCourier(courier);
    }
}
