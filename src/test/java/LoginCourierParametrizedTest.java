import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.CourierRequests;
import testclasses.Courier;
import testclasses.CourierNotOkMessage;
import testclasses.Credentials;

@RunWith(Parameterized.class)
public class LoginCourierParametrizedTest {
    private final String login;
    private final String password;
    private final int expectedCode;
    private final String expectedMessage;
    Courier courier = new Courier("tamarka666","12345678","Tamara");


    public LoginCourierParametrizedTest(String login, String password, int expectedCode, String expectedMessage) {
        this.login = login;
        this.password = password;
        this.expectedCode = expectedCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getCourierData() {
        return new Object[][] {
                { "", "12345678", 400, "Недостаточно данных для входа"},
                { "tamarka666", "", 400, "Недостаточно данных для входа"},
                { "tamarka66", "12345678", 404, "Учетная запись не найдена"},
                { "tamarka666", "1234567", 404, "Учетная запись не найдена"},
        };
    }

    @Before
    public void getData() {
        CourierRequests.createCourier(courier);
    }

    @Test
    @DisplayName("Check status code of logging courier")
    @Description("Check correct status code when logging courier. Negative test.")
    public void loginCourierNegativeTestGetStatusCode() {
        Credentials credentials = new Credentials(login, password);
        CourierRequests.loginCourier(credentials)
                .then().statusCode(expectedCode);
    }
    @Test
    @DisplayName("Check message of logging courier")
    @Description("Check correct message when logging courier. Negative test.")
    public void loginCourierNegativeTestGetMessage() {
        Credentials credentials = new Credentials(login, password);
        CourierNotOkMessage courierNotOkMessage = CourierRequests.loginCourier(credentials)
                .body()
                .as(CourierNotOkMessage.class);
        Assert.assertEquals(expectedMessage, courierNotOkMessage.getMessage());
    }
    @After
    public void deleteData () {
        CourierRequests.deleteCourier(courier);
    }
}
