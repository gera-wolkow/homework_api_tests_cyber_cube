package apiTests;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static Response updateUserPut (int number, JSONObject postData) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").body(postData.toString()).when().put(mainUrl + number).then().extract().response();
        return result;
    }

    public static Response updateUserPatch (int number, JSONObject postData) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").body(postData.toString()).when().patch(mainUrl + number).then().extract().response();
        return result;
    }

    public static Response deleteUser (int number) {
        Response result;
        result = given().contentType("application/json; charset=UTF-8").when().delete(mainUrl + number).then().extract().response();
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
