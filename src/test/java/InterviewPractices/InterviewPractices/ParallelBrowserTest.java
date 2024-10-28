package InterviewPractices.InterviewPractices;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class ParallelBrowserTest {
    WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) throws InterruptedException {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup(); // Correctly set up Edge WebDriver
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(1000); // Optional
    }

    @Test
    public void testWebsite() {
        driver.get("https://www.facebook.com/");
        System.out.println("Title: " + driver.getTitle());
    }

   /* @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }*/
    
}
