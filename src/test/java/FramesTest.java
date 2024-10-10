import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FramesTest {
    static ExtentTest test;
    ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Automation\\SomeTest\\target\\ExtentReport2.html");
    static ExtentReports report;

    int MAX_RETRY_COUNT = 5;
    @BeforeClass
    public void setup(){
        report = new ExtentReports();
        report.attachReporter(spark);
        test = report.createTest("ExtentDemo");
    }

    @AfterClass
    public void teardown(){
        report.flush();
    }

    @Test
    public void iFrame(){
//        WebDriverManager.chromedriver().setup();

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\User\\AppData\\Local\\Google\\Chrome\\User Data");
//        options.addArguments("--profile-directory=Default");
        options.addArguments("--profile-directory=Profile 1");

//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--remote-debugging-port=0");
//        options.addArguments("--disable-gpu");

        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/iframe");

        WebElement frame = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frame);
//
//        WebElement description = driver.findElement(By.cssSelector("#tinymce p "));
//        Assert.assertEquals(description.getText(), "Your content goes here.");
//
//        driver.quit();

    }

    @Test
    public void nestedFrames(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        report.attachReporter(spark);

        ExtentTest test = report.createTest("Мій перший тест", "Опис тесту");

        test.pass("Введено текст для пошуку");
        report.flush();

        driver.get("https://the-internet.herokuapp.com/");
        driver.quit();
    }
}
