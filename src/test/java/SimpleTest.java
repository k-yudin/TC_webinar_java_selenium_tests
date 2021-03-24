import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Epic("This is a simple test")
public class SimpleTest {
    public RemoteWebDriver driver;
    private static final String SELENIUM_URL = "http://selenium:4444/wd/hub";

    @BeforeTest
    public void start() throws Exception {
        this.driver = new RemoteWebDriver(
                new URL(SELENIUM_URL),
                new ChromeOptions()
        );
    }

    @Test
    @Description("We open some page and making a screenshot")
    public void simpleTest() throws Exception {
        this.driver.get("https://learnqa.ru/");
        this.attachScreenshot(
                this.takeScreenshot("learnqa_main")
        );
    }

    @Step("Taking a screenshot")
    private String takeScreenshot(String name) throws Exception {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The Screenshot is taken: " + path);
        } catch (Exception e) {
            System.out.println("Cannot take a screenshot. Error: " + e.getMessage());
        }

        return path;
    }

    @Attachment
    private byte[] attachScreenshot(String path)
    {
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }

        return bytes;
    }

    @AfterTest
    public void closeSeleniumSession() {
        this.driver.close();
        this.driver.quit();
    }
}
