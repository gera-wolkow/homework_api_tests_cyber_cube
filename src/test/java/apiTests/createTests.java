package apiTests;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by iuriiryndin on 22.04.2020
 */
public class createTests extends setup{

    @Test (description = "Check User create with predefined data")
    public static void createDefaultUser () {
        String userName = defaultUserData.get(userNameKey).toString();
        String userJob = defaultUserData.get(userJobKey).toString();
        Response response = createUser(defaultUserData);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userNameKey), userName);
        Assert.assertEquals(response.path(userJobKey), userJob);
        Assert.assertTrue(Integer.parseInt( response.path(userIdKey)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(userCreatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Check User create in case both fields are required", enabled = false)
    public static void createDefaultUserWithoutNameR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userNameKey);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Check User create in case both fields are required", enabled = false)
    public static void createDefaultUserWithoutJobR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userJobKey);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Check User create with empty data in case both fields are required", enabled = false)
    public static void createUserWithEmptyDataR () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userNameKey);
        userDataLocal.remove(userJobKey);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Check User create with empty data")
    public static void createUserWithEmptyData () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.remove(userNameKey);
        userDataLocal.remove(userJobKey);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertNull(response.path(userNameKey));
        Assert.assertNull(response.path(userJobKey));
        Assert.assertTrue(Integer.parseInt( response.path(userIdKey)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(userCreatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Check User create with generated data")
    public static void createUserWithRandomData () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        String userGeneratedName = generateAlphabeticalString(15);
        userDataLocal.put(userNameKey,userGeneratedName);
        String userGeneratedJob = generateAlphabeticalString(17);
        userDataLocal.put(userJobKey, userGeneratedJob);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userNameKey), userGeneratedName);
        Assert.assertEquals(response.path(userJobKey), userGeneratedJob);
        Assert.assertTrue(Integer.parseInt( response.path(userIdKey)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(userCreatedAtKey)) < System.currentTimeMillis());
    }

    @Test (description = "Check User create with custom data and structure")
    public static void createCustomUser () {
        JSONObject userDataLocal = new JSONObject(customUserData);
        String position = userDataLocal.get(userCustomKeyPosition).toString();
        String company = userDataLocal.get(userCustomKeyCompany).toString();
        Integer salary = Integer.parseInt(userDataLocal.get(userCustomKeySalary).toString());
        String startDate = userDataLocal.get(userCustomKeyStartDate).toString();
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        Assert.assertEquals(response.path(userCustomKeyPosition), position);
        Assert.assertEquals(response.path(userCustomKeyCompany), company);
        Assert.assertEquals(response.path(userCustomKeySalary), salary);
        Assert.assertEquals(response.path(userCustomKeyStartDate), startDate);
        Assert.assertTrue(Integer.parseInt( response.path(userIdKey)) > 0 );
        Assert.assertTrue(getTimestampFromDate(response.path(userCreatedAtKey)) < System.currentTimeMillis());
    }
}
