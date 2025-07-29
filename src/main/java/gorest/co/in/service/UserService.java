package gorest.co.in.service;

import io.restassured.response.Response;
import gorest.co.in.model.UserRequest;
import static io.restassured.RestAssured.*;

public class UserService {
    private final String BASE_PATH = "https://gorest.co.in/public/v2/users";
       private final String token = "Bearer 26d87ea1600e215c9ff33ad00f082a4f5b4990dc709a8ae0ffe5b38b481b714f";

    public Response createUser(UserRequest user) {
        return given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(user)
                .post(BASE_PATH);
    }

    public Response getUserById(int id) {
        return given()
                .header("Authorization", token)
                .get(BASE_PATH + "/" + id);
    }
    public Response updateUser(int id, UserRequest user) {
        return given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(user)
                .put(BASE_PATH + "/" + id);
    }

    public Response deleteUser(int id) {
        return given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .delete(BASE_PATH + "/" + id);
    }
}
