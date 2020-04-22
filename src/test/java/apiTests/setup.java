package apiTests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

/**
 * Created by iuriiryndin on 22.04.2020
 */
public class setup extends Main{
    @BeforeSuite
    public static void healthCheck () {
        Assert.assertEquals(getUsers().getStatusCode(), 200);
    }
}
