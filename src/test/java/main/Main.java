package main;

import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

/**
 * Created by iuriiryndin on 22.04.2020
 */
public class Main {
    public static String mainUrl = "https://reqres.in/api/users/";

    public static Response getUsers () {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").when().get(mainUrl).then().extract().response();
        return result;
    }

    public static Response createUser (JSONObject postData) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").body(postData.toString()).when().post(mainUrl).then().extract().response();
        return result;
    }

    public static Response updateUserPut (int id, JSONObject postData) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").body(postData.toString()).when().put(mainUrl + id).then().extract().response();
        return result;
    }

    public static Response updateUserIncorrectIdPut (String id, JSONObject postData) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").body(postData.toString()).when().put(mainUrl + id).then().extract().response();
        return result;
    }

    public static Response updateUserPatch (int id, JSONObject postData) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").body(postData.toString()).when().patch(mainUrl + id).then().extract().response();
        return result;
    }

    public static Response updateUserIncorrectIdPatch (String id, JSONObject postData) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").body(postData.toString()).when().patch(mainUrl + id).then().extract().response();
        return result;
    }

    public static Response deleteUser (int id) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").when().delete(mainUrl + id).then().extract().response();
        return result;
    }

    public static Response deleteUserIncorrectId (String id) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").when().delete(mainUrl + id).then().extract().response();
        return result;
    }

    public static String generateAlphabeticalString (int length) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static long getTimestampFromDate (String data){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return format.parse(data).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

}
