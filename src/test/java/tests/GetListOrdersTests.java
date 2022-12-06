package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import models.CreateCourier;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetListOrdersTests {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("1")
    @Description("1")
    public void CreateUserTests() {



        given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders")
                .then().log().all()
                .statusCode(200);


    }
}
