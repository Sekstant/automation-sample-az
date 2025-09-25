package com.abc;

import gorest.co.in.model.UserRequest;
import gorest.co.in.model.UserResponse;
import gorest.co.in.service.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.testdata.PersonalDataGenerator.*;


public class CreateUser {

    private UserService userService;
    private int userId;

    @BeforeClass
    void setup() {
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
        UserResponse userResponse = userService.createUser(user);
        userId = userResponse.getId();
    }

    @Test(
            dependsOnMethods = "testCreateUser",
            description = "GET  https://gorest.co.in/public/v2/users/ID" +
                          "- get user by ID" +
                          "- verify response success status" +
                          "- verify that response user is expected"
    )

    public void testGetUserById() {
        UserResponse userResponse = userService.getUserById(userId, 200);
        Assert.assertEquals(userResponse.getId(), userId, "IDs are not equals");
    }

    @Test(
            dependsOnMethods = "testGetUserById",
            description = "PATCH/PUT: " +
                          "- replace or update user" +
                          "- verify response success status" +
                          "- verify user is updated by sending GET request"
    )

    public void testUpdateUser() {
        String updatedName = generateName();
        String updatedEmail = generateEmail();
        String updatedGender = "male";
        String updatedStatus = "inactive";
        UserRequest updatedUser = new UserRequest(updatedName,updatedEmail, updatedGender, updatedStatus);
        userService.updateUser(userId, updatedUser);
        Assert.assertEquals(userService.getUserById(userId,200).getName(), updatedName, "Name is not updated");
        Assert.assertEquals(userService.getUserById(userId,200).getEmail(), updatedEmail, "Email is not updated");

    }

    @Test(
            dependsOnMethods = "testUpdateUser",
            description = "DELETE: " +
                          "- delete user" +
                          "- verify response success status" +
                          "- verify user removed"
    )

    public void testDeleteUser() {
        userService.deleteUser(userId);
        Assert.assertEquals(userService.getUserById(userId, 404).getMessage(), "Resource not found", "User is returned by GET, user is not deleted");
    }

}
