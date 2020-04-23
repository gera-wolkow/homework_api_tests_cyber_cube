package apiTests;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by iuriiryndin on 22.04.2020
 */
public class createTests extends setup{

    @Test
    public static void createDefaultUser () {
        String userName = userData.get(userNameKey).toString();
        String userJob = userData.get(userJobKey).toString();
        Response response = createUser(userData);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(response.path(userNameKey), userName);
        Assert.assertEquals(response.path(userJobKey), userJob);
        Assert.assertTrue(Integer.parseInt( response.path(userIdKey)) > 0 );
//      Assert.assertTrue((Date)response.path(userCreatedAtKey) < System.currentTimeMillis());
    }

    @Test (description = "Check User create in case Name element is required", enabled = false)
    public static void createDefaultUserWithoutName () {
        JSONObject userDataLocal = new JSONObject(userData);
        userDataLocal.remove(userNameKey);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertNull(response.getBody());
    }

    @Test (description = "Check User create in case Job element is required", enabled = false)
    public static void createDefaultUserWithoutJob () {
        JSONObject userDataLocal = new JSONObject(userData);
        userDataLocal.remove(userJobKey);
        Response response = createUser(userDataLocal);
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertNull(response.getBody());
    }




}
