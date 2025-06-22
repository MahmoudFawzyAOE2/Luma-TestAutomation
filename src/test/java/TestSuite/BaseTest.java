package TestSuite;

import Listeners.CustomListener;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(CustomListener.class)

public class BaseTest {
    protected ChromeDriver driver;
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // 20 seconds
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown(ITestContext context) {
        if (CustomListener.testFailed) {
            System.out.println("Test failed. Keeping browser open for debugging.");
            return;
        }

        if (driver != null) {
            driver.quit();
        }
    }

}
