import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;

public class SomeTest extends Initialization {

//    @Test
//    public void one() throws InterruptedException {
//        WebDriverManager.chromedriver().driverVersion("85.0.4183.38").setup();
//        WebDriver driver = getDriver();
//
//        driver.get("https://www.google.com");
//        Thread.sleep(1000);
//
//        driver.quit();
//        System.out.println("ONE TEST");
//    }

    @Test
    public void two() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("https://7f3d-91-233-99-229.ngrok-free.app/wd/hub"), chromeOptions);
        driver.get("https://the-internet.herokuapp.com/");
        driver.quit();
    }
}
