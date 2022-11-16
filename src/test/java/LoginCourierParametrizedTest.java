import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.CourierGenerator;
import services.CourierRequests;
import pojo.Courier;
import pojo.Credentials;

@RunWith(Parameterized.class)
public class LoginCourierParametrizedTest {
    private final String login;
    private final String password;
    private final int expectedCode;
    private final String expectedMessage;
    static Courier courier = CourierGenerator.randomCourier();
    static String courierLogin = courier.getLogin();
    static String courierPassword = courier.getPassword();
    CourierRequests courierRequests = new CourierRequests();


    public LoginCourierParametrizedTest(String login, String password, int expectedCode, String expectedMessage) {
        this.login = login;
        this.password = password;
        this.expectedCode = expectedCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getCourierData() {
        return new Object[][] {
                { "",courierPassword, 400, "Недостаточно данных для входа"},
                { courierLogin, "", 400, "Недостаточно данных для входа"},
                { "", "", 400, "Недостаточно данных для входа"},
                { CourierGenerator.randomLogin(), courierPassword, 404, "Учетная запись не найдена"},
                { courierLogin, CourierGenerator.randomPassword(), 404, "Учетная запись не найдена"},
        };
    }

    @Before
    public void getData() {
        courierRequests.createCourier(courier);
    }

    @Test
    @DisplayName("Negative check when logging courier")
    @Description("Check correct status code and error message when logging courier without one or more of required field or when entering incorrect values")
    public void loginCourierNegativeTest() {
        Credentials credentials = new Credentials(login, password);
        String actual = courierRequests.loginCourier(credentials)
                .then().statusCode(expectedCode).extract().path("message").toString();
        Assert.assertEquals(expectedMessage, actual);
    }
    @After
    public void deleteData () {
        courierRequests.deleteCourier(courier);
    }
}
