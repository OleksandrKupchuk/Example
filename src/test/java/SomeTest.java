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
import org.testng.annotations.Test;

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
    public void two() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
    }
}
