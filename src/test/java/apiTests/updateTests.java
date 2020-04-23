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
        JSONObject userDataLocal = new JSONObject(userData);
        userDataLocal.put(userNameKey,generateAlphabeticalString(16));
        userDataLocal.put(userJobKey,generateAlphabeticalString(19));
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        userId = Integer.parseInt(response.path(userIdKey));
    }

    @Test (description = "Update generated User with PUT")
    public static void updateUserPut () {
        JSONObject userDataLocal = new JSONObject(userData);
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

    @Test (description = "Update generated User with PATCH")
    public static void updateUserPatch () {
        JSONObject userDataLocal = new JSONObject(userData);
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
}
