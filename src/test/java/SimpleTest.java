import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class SimpleTest {
    public RemoteWebDriver driver;
    private static final String SELENIUM_URL = "http://127.0.0.1:4444/wd/hub";

    @BeforeTest
    public void start() throws Exception {
        this.driver = new RemoteWebDriver(
                new URL(SELENIUM_URL),
                new ChromeOptions()
        );
    }

    @Test
    public void simpleTest() throws Exception {
        this.driver.get("https://learnqa.ru/");
        this.takeScreenshot();
    }

    private void takeScreenshot() throws Exception {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./screenshot.png"));
        System.out.println("The Screenshot is taken...");

    }

    @AfterTest
    public void closeSeleniumSession() {
        this.driver.close();
        this.driver.quit();
    }
}
