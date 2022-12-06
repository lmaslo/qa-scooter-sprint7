package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import models.Courier;
import models.CreateCourier;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTests {

    private String login = "LMaslo1";
    private String password = "12345";
    private String firstName = "firstName";
    UserSteps step = new UserSteps();


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }


    @Test
    @DisplayName("Создание Курьера - позитивный тест")
    @Description("Проверка успешного создания курьера, после теста данные с id удаляются")
    public void CreateUserTests() {

        CreateCourier createCourier = new CreateCourier(login, password, firstName);
        Courier courier = new Courier(login, password);

        given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier")
                .then().log().all()
                .statusCode(201)
                .body("ok", equalTo(true));

        //удаление пользователя
        //Авторизация, для получения id
        String id = step.LoginUser(courier);
        //Удаление пользователя по id
        step.DeleteUser(id);
    }





    //нужно доделать не работает должно возвращать ощибку 400
    @Test
    @DisplayName("Создание Курьера - позитивный тест")
    @Description("Проверка успешного создания курьера, после теста данные с id удаляются")
    public void CreateUserWithoutParamTests() {

        CreateCourier courier = new CreateCourier("lena1249888888228", "1234", "234");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(201);

        // добавить удаление пользователя

    }

    @Test
    @DisplayName("Создание Курьера - позитивный тест")
    @Description("Проверка успешного создания курьера, после теста данные с id удаляются")
    public void CreateDuplicateUserTests() {

        CreateCourier courier = new CreateCourier("lena122289", "1234", "saske");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(409);

        // добавить удаление пользователя

    }
}
