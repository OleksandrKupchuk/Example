import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowsTest {

    @Test
    public void switchWindows() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");

        driver.switchTo().newWindow(WindowType.WINDOW); //newWindow() є у 4 версії selenium
        driver.switchTo().newWindow(WindowType.TAB); //newWindow() є у 4 версії selenium
        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://the-internet.herokuapp.com/hovers");
        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("window.open('https://the-internet.herokuapp.com/hovers', '_blank');");// або window.open()
        ((JavascriptExecutor) driver).executeScript("window.open()");// або window.open()
        driver.close();

        driver.quit();
    }

    @Test
    public void widgets() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");

        driver.manage().window().minimize();
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.manage().window().setPosition(new Point(500, 500));
        Thread.sleep(2000);

        driver.quit();
    }


    @Test
    public void screenshot() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/challenging_dom");

        WebElement colum = driver.findElement(By.cssSelector(".large-2.columns"));
        File page = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        File screenshotColum = colum.getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(screenshotColum, new File("target/screenshot.png"));
            FileHandler.copy(page, new File("target/page.png"));
        }catch (IOException exception){
            exception.printStackTrace();
        }

        driver.quit();
    }
}
