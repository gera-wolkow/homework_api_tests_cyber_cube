package apiTests;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by iuriiryndin on 23.04.2020
 */
public class e2eTests extends setup{

    @Test (description = "E2E create-update both fields (PUT) test")
    public static void updateCreatedUserWithPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userNameKey,userGeneratedName);
        userDataLocal.put(userJobKey,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(userIdKey));
        String userUpdatedName = generateAlphabeticalString(11);
        String userUpdatedJob = generateAlphabeticalString(9);
        userDataLocal.put(userNameKey,userUpdatedName);
        userDataLocal.put(userJobKey,userUpdatedJob);
        Response responseUpdate = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(userNameKey), userGeneratedName);
        Assert.assertNotEquals(responseUpdate.path(userJobKey), userGeneratedJob);
        Assert.assertEquals(responseUpdate.path(userNameKey), userUpdatedName);
        Assert.assertEquals(responseUpdate.path(userJobKey), userUpdatedJob);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update name field (PUT) test")
    public static void updateNameCreatedUserWithPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userNameKey,userGeneratedName);
        userDataLocal.put(userJobKey,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(userIdKey));
        String userUpdatedName = generateAlphabeticalString(11);
        userDataLocal.put(userNameKey,userUpdatedName);
        userDataLocal.remove(userJobKey);
        Response responseUpdate = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(userNameKey), userGeneratedName);
        Assert.assertNull(responseUpdate.path(userJobKey));
        Assert.assertEquals(responseUpdate.path(userNameKey), userUpdatedName);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update job field (PUT) test")
    public static void updateJobCreatedUserWithPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userNameKey,userGeneratedName);
        userDataLocal.put(userJobKey,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(userIdKey));
        String userUpdatedJob = generateAlphabeticalString(11);
        userDataLocal.put(userJobKey,userUpdatedJob);
        userDataLocal.remove(userNameKey);
        Response responseUpdate = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(userJobKey), userGeneratedJob);
        Assert.assertNull(responseUpdate.path(userNameKey));
        Assert.assertEquals(responseUpdate.path(userJobKey), userUpdatedJob);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update name field (PATCH) test")
    public static void updateNameCreatedUserWithPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userNameKey,userGeneratedName);
        userDataLocal.put(userJobKey,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(userIdKey));
        String userUpdatedName = generateAlphabeticalString(11);
        userDataLocal.put(userNameKey,userUpdatedName);
        userDataLocal.remove(userJobKey);
        Response responseUpdate = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(userNameKey), userGeneratedName);
        Assert.assertEquals(responseUpdate.path(userJobKey), userGeneratedJob);
        Assert.assertEquals(responseUpdate.path(userNameKey), userUpdatedName);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "E2E create-update job field (PATCH) test")
    public static void updateJobCreatedUserWithPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userNameKey,userGeneratedName);
        userDataLocal.put(userJobKey,userGeneratedJob);
        Response responseCreate = createUser(userDataLocal);
        Assert.assertEquals(responseCreate.getStatusCode(), 201);
        Assert.assertNotEquals(responseCreate.getBody().asString(), "");
        int userId = Integer.parseInt(responseCreate.path(userIdKey));
        String userUpdatedJob = generateAlphabeticalString(11);
        userDataLocal.put(userJobKey,userUpdatedJob);
        userDataLocal.remove(userNameKey);
        Response responseUpdate = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(responseUpdate.getStatusCode(), 200);
        Assert.assertNotEquals(responseUpdate.getBody().asString(), "");
        Assert.assertNotEquals(responseUpdate.path(userJobKey), userGeneratedJob);
        Assert.assertEquals(responseUpdate.path(userNameKey), userGeneratedName);
        Assert.assertEquals(responseUpdate.path(userJobKey), userUpdatedJob);
        Assert.assertTrue(getTimestampFromDate(responseUpdate.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }
}
