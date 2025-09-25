package gorest.co.in.service;

import gorest.co.in.model.UserResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import gorest.co.in.model.UserRequest;
import org.apache.http.HttpStatus;
import util.ConfigReader;

import static io.restassured.RestAssured.*;

public class UserService {
    private final String BASE_PATH = ConfigReader.getProperty("baseURI") + "/public/v2/users";
    private final String token = "Bearer 26d87ea1600e215c9ff33ad00f082a4f5b4990dc709a8ae0ffe5b38b481b714f";

    public UserResponse createUser(UserRequest user) {
        return given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(user)
                .post(BASE_PATH)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response().as(UserResponse.class);
    }

    public UserResponse getUserById(int id, int status) {
        return given()
                .header("Authorization", token)
                .get(BASE_PATH + "/" + id)
                .then()
                .log().all()
                .statusCode(status)
                .extract()
                .response().as(UserResponse.class);
    }

    public UserResponse updateUser(int id, UserRequest user) {
        return given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(user)
                .put(BASE_PATH + "/" + id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response().as(UserResponse.class);
    }

    public void deleteUser(int id) {
        given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .delete(BASE_PATH + "/" + id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
