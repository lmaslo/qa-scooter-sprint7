package Courier.steps;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Courier.models.Courier;
import Courier.models.CreateCourier;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    public final String ROOT = "/api/v1/courier";


    public ValidatableResponse CreateCourier(CreateCourier user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(ROOT)
                .then();
    }

    public ValidatableResponse LoginUser(Courier user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(ROOT + "/login")
                .then();
    }

    public String LoginUserGetID(Courier user) {
        Response response = LoginUser(user).extract().response();
        JsonPath jsonPath = response.jsonPath();
        return Integer.toString(jsonPath.get("id"));
    }

    public void DeleteUser(String id) {
        given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .delete(ROOT + "/" + id)
                .then();
    }


}
