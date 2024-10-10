import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Initialization {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public WebDriver getDriver(){return driver.get();}

    @BeforeMethod
    public void setup(){
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\chromedriver.exe");
        driver.set(new ChromeDriver());
    }

    @AfterMethod
    public void teardown(){
        driver.get().quit();
    }

    @AfterClass
    public void terminate(){
        driver.remove();
    }
}
