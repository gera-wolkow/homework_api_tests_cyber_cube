package main;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by iuriiryndin on 22.04.2020
 */
public class CreateTests extends Setup {

    @Test (description = "Check User create with predefined data")
    public static void createDefaultUser () {
        String userName = defaultUserData.get(USER_NAME_KEY).toString();
        String userJob = defaultUserData.get(USER_JOB_KEY).toString();
        Response response = createUser(defaultUserData);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_NAME_KEY), userName);
        Assert.assertEquals(response.path(USER_JOB_KEY), userJob);
        Assert.assertTrue(Integer.parseInt( response.path(USER_ID_KEY)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(USER_CREATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Check User create in case both fields are required", enabled = false)
    public static void createDefaultUserWithoutNameR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_NAME_KEY);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Check User create in case both fields are required", enabled = false)
    public static void createDefaultUserWithoutJobR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_JOB_KEY);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Check User create with empty data in case both fields are required", enabled = false)
    public static void createUserWithEmptyDataR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_NAME_KEY);
        userDataLocal.remove(USER_JOB_KEY);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Check User create with empty data")
    public static void createUserWithEmptyData () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(USER_NAME_KEY);
        userDataLocal.remove(USER_JOB_KEY);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertNull(response.path(USER_NAME_KEY));
        Assert.assertNull(response.path(USER_JOB_KEY));
        Assert.assertTrue(Integer.parseInt( response.path(USER_ID_KEY)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(USER_CREATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Check User create with generated data")
    public static void createUserWithRandomData () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(USER_NAME_KEY,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(USER_JOB_KEY, userGeneratedJob);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_NAME_KEY), userGeneratedName);
        Assert.assertEquals(response.path(USER_JOB_KEY), userGeneratedJob);
        Assert.assertTrue(Integer.parseInt( response.path(USER_ID_KEY)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(USER_CREATED_AT_KEY)) < System.currentTimeMillis());
    }

    @Test (description = "Check User create with custom data and structure")
    public static void createCustomUser () {
        JSONObject userDataLocal = new JSONObject(customUserData);
        String position = userDataLocal.get(USER_CUSTOM_KEY_POSITION).toString();
        String company = userDataLocal.get(USER_CUSTOM_KEY_COMPANY).toString();
        Integer salary = Integer.parseInt(userDataLocal.get(USER_CUSTOM_KEY_SALARY).toString());
        String startDate = userDataLocal.get(USER_CUSTOM_KEY_START_DATE).toString();
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(USER_CUSTOM_KEY_POSITION), position);
        Assert.assertEquals(response.path(USER_CUSTOM_KEY_COMPANY), company);
        Assert.assertEquals(response.path(USER_CUSTOM_KEY_SALARY), salary);
        Assert.assertEquals(response.path(USER_CUSTOM_KEY_START_DATE), startDate);
        Assert.assertTrue(Integer.parseInt( response.path(USER_ID_KEY)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(USER_CREATED_AT_KEY)) < System.currentTimeMillis());
    }
}
