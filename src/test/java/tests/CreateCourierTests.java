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

    private String login = "LMaslo3";
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


    //поменять текст ошибка как в документации, уточнить в чате
    @Test
    @DisplayName("Попытка создать дубликат курьера")
    @Description("Проверка, что в ответе возвращается ошибка при попытке создать курьера с дубилатом login")
    public void CreateDuplicateUserTests() {

        CreateCourier createCourier = new CreateCourier(login, password, firstName);
        step.CreateCourier(createCourier);
        Courier courier = new Courier(login, password);


        given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier")
                .then().log().all()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        //удаление пользователя
        //Авторизация, для получения id
        String id = step.LoginUser(courier);
        //Удаление пользователя по id
        step.DeleteUser(id);
//"message": "Этот логин уже используется"
    }

    //доделать параметризацию
    @Test
    @DisplayName("Создание Курьера - без обязательных параметров")
    @Description("Проверка, что возвращается ошибка если при создании курьера не заполнен один из обязательных параметров")
    public void CreateUserWithoutParamTests() {


        Courier courier = new Courier(login, "");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));;

    }
}
