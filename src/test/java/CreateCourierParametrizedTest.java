import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.CourierRequests;
import testclasses.Courier;
import testclasses.CourierNotOkMessage;

@RunWith(Parameterized.class)
public class CreateCourierParametrizedTest {
    private final String login;
    private final String password;
    private final String firstName;

    public CreateCourierParametrizedTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters
    public static Object[][] getCourierData() {
        return new Object[][] {
                { "", "12345678", "firstName"},
                { "tamarka666", "", "firstName"},
                { "", "", "firstName"}
        };
    }

    @Test
    @DisplayName("Check status code of creating courier")
    @Description("Check correct status code (400) when create new courier without one or more of required field")
    public void createCourierNegativeTestNotEnoughValuesGetStatusCode() {
        Courier courier = new Courier(login, password, firstName);
        CourierRequests.createCourier(courier)
                .then().statusCode(400);
    }
    @Test
    @DisplayName("Check message of creating courier")
    @Description("Check correct error message when create new courier without one or more of required field")
    public void createCourierNegativeTestNotEnoughValuesGetMessage() {
        Courier courier = new Courier(login, password, firstName);
        CourierNotOkMessage courierNotOkMessage = CourierRequests.createCourier(courier)
                .body()
                .as(CourierNotOkMessage.class);
        Assert.assertEquals("Недостаточно данных для создания учетной записи", courierNotOkMessage.getMessage());
    }
    @After
    public void deleteData () {
        Courier courier = new Courier(login, password, firstName);
        CourierRequests.deleteCourier(courier);
    }
}
