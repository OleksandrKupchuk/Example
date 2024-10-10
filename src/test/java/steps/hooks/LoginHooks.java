package steps.hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class LoginHooks {

    @Before
    public static void setup(){
        DriverManager.Instance.createDriver();
    }

    @After
    public static void tearDown(){
        DriverManager.Instance.getDriver().close();
    }
}
