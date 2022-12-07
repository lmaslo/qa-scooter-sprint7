package Courier.tests;

import Courier.models.Courier;
import Courier.models.CreateCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import Courier.models.CourierGeneration;
import org.junit.Test;
import Courier.steps.UserSteps;

import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTests {

    UserSteps step = new UserSteps();
    CourierGeneration generation = new CourierGeneration();

    @Test
    @DisplayName("Создание Курьера - позитивный тест")
    @Description("Проверка успешного создания курьера, после теста данные с id удаляются")
    public void CreateUserTests() {
        CreateCourier createCourier = generation.newCourier();
        Courier courierCredentials = new Courier(createCourier.getLogin(), createCourier.getPassword());

        step.CreateCourier(createCourier)
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));

        String id = step.LoginUserGetID(courierCredentials);
        step.DeleteUser(id);
    }


   //поменять текст ошибка как в документации, уточнить в чате
    @Test
    @DisplayName("Попытка создать дубликат курьера")
    @Description("Проверка, что в ответе возвращается ошибка при попытке создать курьера с дубилатом login")
    public void CreateDuplicateUserTests() {

        CreateCourier createCourier = generation.newCourier();
        Courier courierCredentials = new Courier(createCourier.getLogin(), createCourier.getPassword());

        step.CreateCourier(createCourier);

        step.CreateCourier(createCourier)
                .log().all()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        String id = step.LoginUserGetID(courierCredentials);
        step.DeleteUser(id);
    }

  /*  //доделать параметризацию
    @Test
    @DisplayName("Создание Курьера - без обязательных параметров")
    @Description("Проверка, что возвращается ошибка если при создании курьера не заполнен один из обязательных параметров")
    public void CreateUserWithoutParamTests() {


        CreateCourier createCourier = new CreateCourier(login, "", firstName);

        given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier")
                .then().log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));;

    }*/
}
