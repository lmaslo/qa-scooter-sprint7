package steps;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Courier;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public void DeleteUser(String id){
        given()
                .header("Content-type", "application/json")
                .when()
                .delete("http://qa-scooter.praktikum-services.ru/api/v1/courier/{id}", id)
                .then().log().all()
                .statusCode(200);
    }

    public String LoginUser(Courier user){
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier/login")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        return Integer. toString(jsonPath.get("id"));
    }

}
