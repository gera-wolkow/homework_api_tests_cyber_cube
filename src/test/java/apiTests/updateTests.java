package apiTests;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by iuriiryndin on 22.04.2020
 */
public class updateTests extends setup{

    public static int userId;

    @BeforeMethod
    public static void createRandomUserToGetId () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.put(userNameKey,generateAlphabeticalString(16));
        userDataLocal.put(userJobKey,generateAlphabeticalString(19));
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        userId = Integer.parseInt(response.path(userIdKey));
    }

    @Test (description = "Update generated User with PUT")
    public static void updateUserPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(userNameKey,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userJobKey, userGeneratedJob);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userNameKey), userGeneratedName);
        Assert.assertEquals(response.path(userJobKey), userGeneratedJob);
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Name (PUT) if both fields are required", enabled = false)
    public static void updateUserNamePutR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userJobKey);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(userNameKey, userGeneratedName);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Update generated User with only Job (PUT) if both fields are required", enabled = false)
    public static void updateUserJobPutR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userNameKey);
        String userGeneratedJob = generateAlphabeticalString(16);
        userDataLocal.put(userJobKey, userGeneratedJob);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Update generated User with only Name (PUT)")
    public static void updateUserNamePut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userJobKey);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(userNameKey, userGeneratedName);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userNameKey), userGeneratedName);
        Assert.assertNull(response.path(userJobKey));
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Name (PUT)")
    public static void updateUserJobPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userNameKey);
        String userGeneratedJob = generateAlphabeticalString(15);
        userDataLocal.put(userJobKey, userGeneratedJob);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userJobKey), userGeneratedJob);
        Assert.assertNull(response.path(userNameKey));
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with empty data (PUT)")
    public static void updateUserEmptyPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userJobKey);
        userDataLocal.remove(userNameKey);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertNull(response.path(userNameKey));
        Assert.assertNull(response.path(userJobKey));
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with PATCH")
    public static void updateUserPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(userNameKey,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userJobKey, userGeneratedJob);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userNameKey), userGeneratedName);
        Assert.assertEquals(response.path(userJobKey), userGeneratedJob);
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Name (PATCH)")
    public static void updateUserNamePatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userJobKey);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(userNameKey, userGeneratedName);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userNameKey), userGeneratedName);
        Assert.assertNotNull(response.path(userJobKey));
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Job (PATCH)")
    public static void updateUserJobPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userNameKey);
        String userGeneratedJob = generateAlphabeticalString(15);
        userDataLocal.put(userJobKey, userGeneratedJob);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userJobKey), userGeneratedJob);
        Assert.assertNotNull(response.path(userNameKey));
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with empty data (PATCH)")
    public static void updateUserEmptyPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userJobKey);
        userDataLocal.remove(userNameKey);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertNotNull(response.path(userNameKey));
        Assert.assertNotNull(response.path(userJobKey));
        Assert.assertTrue(getTimestampFromDate(response.path(userUpdatedAtKey)) < System.currentTimeMillis());
    }
}
