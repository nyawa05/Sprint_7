import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.CourierGenerator;
import services.CourierRequests;
import pojo.Courier;

@RunWith(Parameterized.class)
public class CreateCourierParametrizedTest {
    private final String login;
    private final String password;
    private final String firstName;
    Courier courier;
    CourierRequests courierRequests = new CourierRequests();

    public CreateCourierParametrizedTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters
    public static Object[][] getCourierData() {
        return new Object[][] {
                { "", CourierGenerator.randomPassword(), CourierGenerator.randomFirstName()},
                {CourierGenerator.randomLogin(), "", CourierGenerator.randomFirstName()},
                { "", "", CourierGenerator.randomFirstName()},
                {"", "", ""}
        };
    }

    @Test
    @DisplayName("Negative check when creating new courier")
    @Description("Check correct status code (400) and error message when create new courier without one or more of required field")
    public void createCourierNegativeTestNotEnoughValues() {
        courier = new Courier(login, password, firstName);
        String actual = courierRequests.createCourier(courier)
                .then().statusCode(400).extract().path("message").toString();
        Assert.assertEquals("Недостаточно данных для создания учетной записи", actual);
    }
    @After
    public void deleteData () {
        courierRequests.deleteCourier(courier);
    }
}
