package InterviewPractices.InterviewPractices;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static void main(String[] args) {
        String outputPath = "C:\\Users\\USER\\Downloads\\DataDriventesting.xlsx"; // Ensure this path is correct

        try (FileInputStream fis = new FileInputStream(outputPath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            var sheet = workbook.getSheetAt(0);
            System.out.println("mobilenumber\tpassword"); // Print headers

            // Iterate through rows
            for (Row row : sheet) {
                String mobileNumber = "";
                String password = "";

                // Iterate through cells
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell cell = row.getCell(i);
                    switch (cell.getCellType()) {
                        case STRING:
                            if (i == 0) {
                                mobileNumber = cell.getStringCellValue();
                            } else {
                                password = cell.getStringCellValue();
                            }
                            break;
                        case NUMERIC:
                            // Check if the cell value represents a mobile number
                            if (i == 0) {
                                mobileNumber = String.valueOf((long) cell.getNumericCellValue());
                            } else {
                                password = String.valueOf((long) cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            // Handle boolean values if necessary (not in your case)
                            break;
                        default:
                            System.out.print("Unknown Type\t");
                    }
                }

                // Print the extracted values
                System.out.println(mobileNumber + "\t" + password);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
