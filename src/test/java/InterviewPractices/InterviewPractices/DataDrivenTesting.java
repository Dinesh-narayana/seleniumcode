package InterviewPractices.InterviewPractices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenTesting {

    @Test(dataProvider = "loginData")
    public void dataDriven(String mobilenumber, String password) throws InterruptedException {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        
            // Navigate to the login page
            driver.get("https://aasthatv.tv/signin");
            driver.manage().window().maximize();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[contains(text(),'Sign In with')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id(":r4:")).sendKeys(mobilenumber);
            driver.findElement(By.id(":r1:")).sendKeys(password);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // Add assertions or additional validation here as needed

        
        
    }

    @DataProvider(name = "loginData")
    public Object[][] data() throws IOException {
        // Specify the path to your Excel file
        File file = new File("C:\\Users\\USER\\Downloads\\DataDriventesting.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("sheet1");

        int noOfRows = sheet.getPhysicalNumberOfRows();
        int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[noOfRows - 1][noOfColumns];

        for (int i = 1; i < noOfRows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < noOfColumns; j++) {
                Cell cell = row.getCell(j);
                // Convert the cell value to a string properly
                switch (cell.getCellType()) {
                    case STRING:
                        data[i - 1][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i - 1][j] = String.valueOf((long) cell.getNumericCellValue());
                        break;
                    default:
                        data[i - 1][j] = ""; // Handle other cell types if needed
                }
            }
        }
        workbook.close();
        return data;
    }
}
