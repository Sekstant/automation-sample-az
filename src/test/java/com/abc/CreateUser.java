package com.abc;

import gorest.co.in.model.UserRequest;
import gorest.co.in.model.UserResponse;
import gorest.co.in.service.UserService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static util.PersonalDataGenerator.*;


public class CreateUser {

    static UserService userService;
    static int userId;

    Logger logger = LoggerFactory.getLogger(CreateUser.class);

    @BeforeClass
    static void setup() {
        userService = new UserService();
    }

    @Test(
            description = "Create user POST https://gorest.co.in/public/v2/users" +
                          "- create a new user" +
                          "- save ID" +
                          "- verify response success status"
    )

    public void testCreateUser() {
        UserRequest user = new UserRequest(generateName(), generateEmail(), "female", "active");
        Response response = userService.createUser(user);
        Assert.assertEquals(response.then().extract().statusCode(), 201, "Invalid http status code");
        userId = response.then().extract().path("id");
    }

    @Test(
            dependsOnMethods = "testCreateUser",
            description = "GET  https://gorest.co.in/public/v2/users/ID" +
                          "- get user by ID" +
                          "- verify response success status" +
                          "- verify that response user is expected"
    )

    public void testGetUserById() {
        Response response = userService.getUserById(userId);
        Assert.assertEquals(response.then().extract().statusCode(), 200, "Invalid http status code");
        UserResponse user = response.then().extract().as(UserResponse.class);
        Assert.assertEquals(user.getId(), userId, "IDs are not equals");
    }

    @Test(
            dependsOnMethods = "testGetUserById",
            description = "PATCH/PUT: " +
                          "- replace or update user" +
                          "- verify response success status" +
                          "- verify user is updated by sending GET request"
    )

    public void testUpdateUser() {
        UserRequest updatedUser = new UserRequest(generateName(), generateEmail(), "male", "inactive");
        Response response = userService.updateUser(userId, updatedUser);
        Assert.assertEquals(response.then().extract().statusCode(), 200, "Invalid http status code");
        Response responseUpdated = userService.getUserById(userId);
        Assert.assertEquals(response.then().extract().statusCode(), responseUpdated.then().extract().statusCode(), "Updated data are not returned by GET");
    }

    @Test(
            dependsOnMethods = "testUpdateUser",
            description = "DELETE: " +
                          "- delete user" +
                          "- verify response success status" +
                          "- verify user removed"
    )

    public void testDeleteUser() {
        Response response = userService.deleteUser(userId);
        Assert.assertEquals(response.then().extract().statusCode(), 204, "User is not deleted");
        Assert.assertEquals(userService.getUserById(userId).then().extract().statusCode(), 404, "User is returned by GET, user is not deleted");
    }

}
