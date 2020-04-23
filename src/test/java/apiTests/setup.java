package apiTests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
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

    public static JSONObject defaultUserData;
    public static JSONObject customUserData;
    public static String userNameKey = "name";
    public static String userJobKey = "job";
    public static String userIdKey = "id";
    public static String userCreatedAtKey = "createdAt";
    public static String userUpdatedAtKey = "updatedAt";

    public static String userCustomKeyPosition = "position";
    public static String userCustomKeyCompany = "company";
    public static String userCustomKeySalary = "salary";
    public static String userCustomKeyStartDate = "startDate";

    @BeforeSuite
    public static void healthCheck () throws IOException, ParseException {
        RestAssured.defaultParser = Parser.JSON;
        Assert.assertEquals(getUsers().getStatusCode(), 200);
        JSONParser parser = new JSONParser();
        Object defObj = parser.parse(new FileReader(new File("./src/test/resources/defaultUser.json")));
        defaultUserData = (JSONObject) defObj;
        Object cusObj = parser.parse(new FileReader(new File("./src/test/resources/customUser.json")));
        customUserData = (JSONObject) cusObj;
    }
}
