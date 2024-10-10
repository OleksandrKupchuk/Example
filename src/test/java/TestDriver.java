import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xbill.DNS.Header;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.security.Key;
import java.time.Duration;
import java.util.List;

public class TestDriver {
    @Test
    public void one() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");
        Thread.sleep(1000);

        driver.quit();
        System.out.println("ONE TEST");
    }

    @Test
    public void two() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        String signUpBackgroundColor = driver.findElement(
                        By.cssSelector(".hero-descriptor_btn.btn.btn-primary"))
                .getCssValue("background-color");
        System.out.println(Color.fromString(signUpBackgroundColor).asHex());
    }

    @Test
    public void dragAndDrop() throws InterruptedException, AWTException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
//        driver.get("https://the-internet.herokuapp.com/");

        Actions actions = new Actions(driver);
//
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        actions.dragAndDrop(source, target).build().perform();
//        actions.clickAndHold(source).moveToElement(target).release().build().perform();
//        WebElement menu = driver.findElement(By.linkText("A/B Testing"));
//        actions.moveToElement(menu);
//        actions.contextClick(menu).build().perform();
//        actions.sendKeys(Keys.ARROW_DOWN).perform();
//        driver.getWindowHandles();
//        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

//        Robot robot = new Robot();
//
//        robot.keyPress(KeyEvent.VK_DOWN); // Симулюємо натискання стрілки вниз
//        robot.keyPress(KeyEvent.VK_DOWN); // Симулюємо натискання стрілки вниз
//        robot.keyRelease(KeyEvent.VK_DOWN);

//        robot.keyPress(KeyEvent.VK_ENTER); // Симулюємо натискання Enter для вибору
//        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void assertStatusCode() throws InterruptedException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.setProxy(seleniumProxy);
        WebDriver driver = new ChromeDriver(options);

        proxy.newHar("example");

        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();

        Har har = proxy.getHar();
        har.getLog().getEntries().forEach(entry -> {
            if (entry.getRequest().getUrl().contains("/authenticate")) {
                Assert.assertEquals(entry.getResponse().getStatus(), 303);
            }
        });

        driver.quit();
        proxy.stop();
    }

    @Test
    public void addRequestFilter() throws InterruptedException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        proxy.addRequestFilter((request, contents, messageInfo) -> {
            if (request.getUri().contains("/authenticate")){
                String newUri = request.getUri() + "&newParam=newValue";
                request.setUri(newUri);
            }
            return null;
        });

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.setProxy(seleniumProxy);
        WebDriver driver = new ChromeDriver(options);

        proxy.newHar("example");

        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();

        Har har = proxy.getHar();
        har.getLog().getEntries().forEach(entry -> {
            if (entry.getRequest().getUrl().contains("/authenticate")) {
                entry.getResponse().setStatus(500);
                entry.getResponse().setError("Its url not correct");

                Assert.assertEquals(entry.getResponse().getStatus(), 500);
            }
        });

        driver.quit();
        proxy.stop();
    }
}
