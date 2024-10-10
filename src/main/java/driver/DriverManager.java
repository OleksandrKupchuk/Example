package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static DriverManager Instance = new DriverManager();

    public void createDriver(){
        driver.set(new ChromeDriver());
    }

    public static WebDriver getDriver(){
        return driver.get();
    }
}
