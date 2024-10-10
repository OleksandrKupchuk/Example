package steps.definitions;

import driver.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginDefinition {
    private WebDriver driver = DriverManager.Instance.getDriver();

    @When("\\[UI] User login with username (.*) and (.*)$")
    public void LoginWithUsernameAndPassword(String userName, String password) {
        System.out.println(userName + " " + password);
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("radius")).click();
    }

    @Then("\\[UI] User should be on main page (.*)$")
    public void UserShouldBeOnMainPage(String title) {
        WebElement actualTitle = driver.findElement(By.xpath("//h2"));
        actualTitle.getText().contains(title);
    }

}
