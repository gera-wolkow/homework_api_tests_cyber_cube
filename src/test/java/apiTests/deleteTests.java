package apiTests;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by iuriiryndin on 22.04.2020
 */
public class deleteTests extends setup{

    public static int userId;

    @BeforeMethod
    public static void createRandomUserToGetId () {
        JSONObject userDataLocal = new JSONObject(defaultUserData);
        userDataLocal.put(userNameKey,generateAlphabeticalString(17));
        userDataLocal.put(userJobKey,generateAlphabeticalString(13));
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotEquals(response.getBody().asString(), "");
        userId = Integer.parseInt(response.path(userIdKey));
    }

    @Test (description = "Delete generated user")
    public static void deleteUser () {
        Response response = deleteUser(userId);
        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Delete unavailable user")
    public static void deleteZeroUser () {
        Response response = deleteUser(0);
        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test (description = "Delete user using incorrect id format")
    public static void deleteUserIncorrectId () {
        String id = "fakeId";
        Response response = deleteUserIncorrectId(id);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getBody().asString(), "");
    }



}
