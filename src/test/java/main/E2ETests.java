package main;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by iuriiryndin on 23.04.2020
 */
public class E2ETests extends Setup {

    @Test (description = "E2E create-update both fields (PUT) test")
    public static void updateCreatedUserWithPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        userDataLocal.put(USER_JOB_KEY,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(USER_ID_KEY));
        String userUpdatedName = generateAlphabeticalString(11);
        String userUpdatedJob = generateAlphabeticalString(9);
        userDataLocal.put(USER_NAME_KEY,userUpdatedName);
        userDataLocal.put(USER_JOB_KEY,userUpdatedJob);
        Response responseUpdate = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertNotEquals(responseUpdate.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertEquals(responseUpdate.path(USER_NAME_KEY), userUpdatedName);
        Assert.assertEquals(responseUpdate.path(USER_JOB_KEY), userUpdatedJob);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update name field (PUT) test")
    public static void updateNameCreatedUserWithPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        userDataLocal.put(USER_JOB_KEY,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(USER_ID_KEY));
        String userUpdatedName = generateAlphabeticalString(11);
        userDataLocal.put(USER_NAME_KEY,userUpdatedName);
        userDataLocal.remove(USER_JOB_KEY);
        Response responseUpdate = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertNull(responseUpdate.path(USER_JOB_KEY));
        Assert.assertEquals(responseUpdate.path(USER_NAME_KEY), userUpdatedName);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update job field (PUT) test")
    public static void updateJobCreatedUserWithPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        userDataLocal.put(USER_JOB_KEY,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(USER_ID_KEY));
        String userUpdatedJob = generateAlphabeticalString(11);
        userDataLocal.put(USER_JOB_KEY,userUpdatedJob);
        userDataLocal.remove(USER_NAME_KEY);
        Response responseUpdate = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertNull(responseUpdate.path(USER_NAME_KEY));
        Assert.assertEquals(responseUpdate.path(USER_JOB_KEY), userUpdatedJob);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update name field (PATCH) test")
    public static void updateNameCreatedUserWithPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        userDataLocal.put(USER_JOB_KEY,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(USER_ID_KEY));
        String userUpdatedName = generateAlphabeticalString(11);
        userDataLocal.put(USER_NAME_KEY,userUpdatedName);
        userDataLocal.remove(USER_JOB_KEY);
        Response responseUpdate = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertEquals(responseUpdate.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertEquals(responseUpdate.path(USER_NAME_KEY), userUpdatedName);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update job field (PATCH) test")
    public static void updateJobCreatedUserWithPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        userDataLocal.put(USER_JOB_KEY,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(USER_ID_KEY));
        String userUpdatedJob = generateAlphabeticalString(11);
        userDataLocal.put(USER_JOB_KEY,userUpdatedJob);
        userDataLocal.remove(USER_NAME_KEY);
        Response responseUpdate = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertEquals(responseUpdate.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertEquals(responseUpdate.path(USER_JOB_KEY), userUpdatedJob);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }
}
