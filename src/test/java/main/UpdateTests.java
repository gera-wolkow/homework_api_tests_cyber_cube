package main;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by iuriiryndin on 22.04.2020
 */
public class UpdateTests extends Setup {

    public static int userId;

    @BeforeMethod
    public static void createRandomUserToGetId () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.put(USER_NAME_KEY,generateAlphabeticalString(16));
        userDataLocal.put(USER_JOB_KEY,generateAlphabeticalString(19));
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        userId = Integer.parseInt(response.path(USER_ID_KEY));
    }

    @Test (description = "Update generated User with PUT")
    public static void updateUserPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertEquals(response.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Name (PUT) if both fields are required", enabled = false)
    public static void updateUserNamePutR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_JOB_KEY);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY, userGeneratedName);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Update generated User with only Job (PUT) if both fields are required", enabled = false)
    public static void updateUserJobPutR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_NAME_KEY);
        String userGeneratedJob = generateAlphabeticalString(16);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Update generated User with only Name (PUT)")
    public static void updateUserNamePut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_JOB_KEY);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY, userGeneratedName);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertNull(response.path(USER_JOB_KEY));
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Name (PUT)")
    public static void updateUserJobPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_NAME_KEY);
        String userGeneratedJob = generateAlphabeticalString(15);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertNull(response.path(USER_NAME_KEY));
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with empty data (PUT)")
    public static void updateUserEmptyPut () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_JOB_KEY);
        userDataLocal.remove(USER_NAME_KEY);
        Response response = updateUserPut(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertNull(response.path(USER_NAME_KEY));
        Assert.assertNull(response.path(USER_JOB_KEY));
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with PATCH")
    public static void updateUserPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertEquals(response.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Name (PATCH)")
    public static void updateUserNamePatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_JOB_KEY);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY, userGeneratedName);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertNotNull(response.path(USER_JOB_KEY));
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with only Job (PATCH)")
    public static void updateUserJobPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_NAME_KEY);
        String userGeneratedJob = generateAlphabeticalString(15);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertNotNull(response.path(USER_NAME_KEY));
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update generated User with empty data (PATCH)")
    public static void updateUserEmptyPatch () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_JOB_KEY);
        userDataLocal.remove(USER_NAME_KEY);
        Response response = updateUserPatch(userId, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertNotNull(response.path(USER_NAME_KEY));
        Assert.assertNotNull(response.path(USER_JOB_KEY));
        Assert.assertTrue(getTimestampFromDate(response.path(USER_UPDATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Update user using incorrect id format (PUT)")
    public static void updateUserIncorrectIdPut () {
        String id = "fakeId";
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = updateUserIncorrectIdPut(id, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Update user using incorrect id format (PATCH)")
    public static void updateUserIncorrectIdPatch () {
        String id = "fakeId";
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = updateUserIncorrectIdPatch(id, userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }
}
