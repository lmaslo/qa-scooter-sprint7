package Courier.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import Courier.models.Courier;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LoginCourierTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("1")
    @Description("11")
    public void rtytyTests() {

        Courier courier= new Courier("lena1222228", "1234");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then().log().all()
                .statusCode(200);


    }
}
