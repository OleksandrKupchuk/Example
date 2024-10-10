import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShadowRoomTest {

    @Test
    public void iFrame() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://selectorshub.com/xpath-practice-page/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement userName = driver.findElement(By.id("userName"));
        WebElement userName = driver.findElement(By.cssSelector("#userName"));
        SearchContext userNameShadowRoot = (SearchContext) js.executeScript("return arguments[0].shadowRoot", userName);
        Thread.sleep(1000);
//        WebElement nameField = userName.getShadowRoot().findElement(By.cssSelector("#kils"));
        WebElement nameField = userNameShadowRoot.findElement(By.id("kils"));

        nameField.sendKeys("Oleg");

        Thread.sleep(2000);

        driver.quit();
    }
}

