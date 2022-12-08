package Order.tests;

import Order.models.CreateOrder;
import Order.steps.OrderSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTests {
    OrderSteps orderSteps = new OrderSteps();


    @Test
    @DisplayName("Создание заказа")
    public void CreateOrderTests() {
        ArrayList<String> color = new ArrayList<>();
        color.add("BLACK");

        CreateOrder createOrder = new CreateOrder("firstName", "lastName", "address", 4, "phone", 5, "2020-06-06", "comment", color);

        orderSteps.CreateOrder(createOrder)
                .log().all()
                 .statusCode(201)
                .body("track", notNullValue());


    }
}
