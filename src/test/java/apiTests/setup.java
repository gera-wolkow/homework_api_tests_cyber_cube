package apiTests;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by iuriiryndin on 22.04.2020
 */
public class setup extends Main{

    public static JSONObject userData;
    public static String userNameKey = "name";
    public static String userJobKey = "job";
    public static String userIdKey = "id";
    public static String userCreatedAtKey = "createdAt";
    public static String userUpdatedAtKey = "updatedAt";

    @BeforeSuite
    public static void healthCheck () throws IOException, ParseException {
        Assert.assertEquals(getUsers().getStatusCode(), 200);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(new File("./src/test/resources/user.json")));
        userData = (JSONObject) obj;
    }
}
