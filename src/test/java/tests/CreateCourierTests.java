package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import models.Couriers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CreateCourierTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание Курьера - позитивный тест")
    @Description("Проверка успешного создания курьера, после теста данные с id удаляются")
    public void deleteUserTest() {

        Couriers couriers= new Couriers("lena1231232", "1234", "saske");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(couriers)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(201);

    }
}
