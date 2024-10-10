import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitElements {
        private WebDriver driver;
        private WebDriverWait wait;

        public WaitElements(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(2));
        }

        public boolean waitFotPageTitle(String title){
            return wait.until(ExpectedConditions.titleIs(title));
        }

        public WebElement waitVisibilityOfElement(By element){
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }

        public WebElement waitClickableOfElement(By element){
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }

    }
