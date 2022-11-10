import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.OrderRequests;
import testclasses.Order;
import testclasses.OrderId;

@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Number rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    OrderId orderId = new OrderId();

    public CreateOrderParametrizedTest(String firstName, String lastName, String address, String metroStation, String phone, Number rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                { "Имя", "Фамилия", "Адрес", "Черкизовская", "+12345678901", 5, "2022-11-30", "Комментарий", new String[] {}},
                { "Имя", "Фамилия", "Адрес", "Комсомольская", "+12345678901", 5, "2022-11-30", "Комментарий", new String[] {"GREY"}},
                { "Имя", "Фамилия", "Адрес", "Чистые пруды", "+12345678901", 5, "2022-11-30", "Комментарий", new String[] {"BLACK"}},
                { "Имя", "Фамилия", "Адрес", "Кропоткинская", "+12345678901", 5, "2022-11-30", "Комментарий", new String[] {"GREY","BLACK"}}
        };
    }

    @Test
    @DisplayName("Check status code of creating order")
    @Description("Check correct status code (201) when create new order")
    public void createOrderTest() {
        Order order = new Order(firstName,lastName, address, metroStation, phone,rentTime, deliveryDate, comment, color);
        String track = OrderRequests.createOrder(order)
                .then().statusCode(201)
                .extract().path("track").toString();
        Assert.assertTrue(track != null);
        orderId.setTrack(track);
    }
    @After
    public void deleteData () {
        OrderRequests.cancelOrder(orderId);
    }
}
