package steps.definitions.logout;

import driver.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutDefinition {

    private WebDriver driver = DriverManager.Instance.getDriver();

    @When("[UI] User click on logout button")
    public void UserClickOnLogoutButton() {

        driver.findElement(By.cssSelector(".button.secondary.radius")).click();
    }

    @Then("\\[UI] User should be on login page (.*)$")
    public void UserShouldBeOnLoginPage(String title) {
        WebElement actualTitle = driver.findElement(By.xpath("//h2"));
        actualTitle.getText().contains(title);
    }
}
