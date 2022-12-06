package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import models.CreateCourier;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CreateCourierTests {

    //вынести отдельно методы сделать степы у алюра нвйти похожий рпимер в куагуру
    
    //Нужные проверки по заданию для проекта
    //запрос возвращает правильный код ответа;
    //успешный запрос возвращает ok: true;
    //если одного из полей нет, запрос возвращает ошибку;
    //если создать пользователя с логином, который уже есть, возвращается ошибка.


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание Курьера - позитивный тест")
    @Description("Проверка успешного создания курьера, после теста данные с id удаляются")
    public void CreateUserTests() {

        CreateCourier courier= new CreateCourier("lena12228", "1234", "saske");

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

    //нужно доделать не работает должно возвращать ощибку 400
    @Test
    @DisplayName("Создание Курьера - позитивный тест")
    @Description("Проверка успешного создания курьера, после теста данные с id удаляются")
    public void CreateUserWithoutParamTests() {

        CreateCourier courier= new CreateCourier("lena1249888888228", "1234");

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

        CreateCourier courier= new CreateCourier("lena122289", "1234", "saske");

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
