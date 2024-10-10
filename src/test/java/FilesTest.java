import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesTest {

    @Test
    public void crateFile(){
        String filePath = "target/some.txt";
        Path path = Paths.get(filePath);
        File file = new File(filePath);

        try {
            Files.createFile(path);
           Files.writeString(path, "another line", StandardOpenOption.APPEND);//StandardOpenOption.APPEND означає добавити новий запис у файл
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readFile(){
        String filePath = "target/some.txt";
        try(FileReader fileReader = new FileReader(filePath)) {
            BufferedReader bufferedReader  = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
            List<String> lines = bufferedReader.lines().toList();
//            System.out.println(line);
            System.out.println(lines);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void downloadFile(){
        WebDriverManager.chromedriver().setup();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", "C:\\Automation\\SomeTest\\target\\files");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/download");

        WebElement fileElement = driver.findElement(By.linkText("some-file.txt"));

        fileElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> {
            File file = new File("/path/to/download/directory/назва_файлу");
            return file.exists();
        });

        driver.quit();
    }

    @Test
    public void uploadFile() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String data = "Це тестовий текст.";
        File file = new File("target/uploadfile/hillel.txt");
        try {
//            FileWriter writer = new FileWriter(file);
//            writer.write(data);
//            writer.close();
            FileUtils.writeStringToFile(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement fileElement = driver.findElement(By.id("file-upload"));

        fileElement.sendKeys("C:\\Automation\\SomeTest\\target\\uploadfile\\hillel.txt");

        Thread.sleep(2000);

        driver.findElement(By.id("file-submit")).click();
        driver.quit();
    }
}
