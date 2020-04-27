package main;

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
public class Setup extends Main{

    public static JSONObject defaultUserData;
    public static JSONObject customUserData;
    public static final String USER_NAME_KEY = "name";
    public static final String USER_JOB_KEY = "job";
    public static final String USER_ID_KEY = "id";
    public static final String USER_CREATED_AT_KEY = "createdAt";
    public static final String USER_UPDATED_AT_KEY = "updatedAt";

    public static final String USER_CUSTOM_KEY_POSITION = "position";
    public static final String USER_CUSTOM_KEY_COMPANY = "company";
    public static final String USER_CUSTOM_KEY_SALARY = "salary";
    public static final String USER_CUSTOM_KEY_START_DATE = "startDate";

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
