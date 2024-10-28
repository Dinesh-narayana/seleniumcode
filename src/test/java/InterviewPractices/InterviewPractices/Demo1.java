package InterviewPractices.InterviewPractices;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Demo1 {

    @Test
    public void testMethod() throws IOException, InterruptedException {
        // Uncomment if using WebDriverManager
        // WebDriverManager.chromedriver().setup();

        // Set the path for ChromeDriver if not using WebDriverManager
        // System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Take a screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\USER\\Pictures\\Saved Pictures.png"));

        Thread.sleep(3000);

        // Scroll down using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        // Close the driver
        //driver.quit();
    }
}

	/*@Test(groups = {"smoke"})	
	public void test1() {
		int i = 10;
		System.out.println(i);
	}
	@Test(priority=3)
	public void test() {
		int i = 12;
		System.out.println(i);
	}
	@Test(groups = {"smoke"})
	public void test4() {
		int i = 15;
		System.out.println(i);
	}

@Test(groups= {"sanity"})
public void test7() {
    int i = 20;
    System.out.println(i);*/



