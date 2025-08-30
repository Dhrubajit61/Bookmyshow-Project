package Utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelUtils {
    public static Object[][] getTestData(String path, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                data[i-1][j] = row.getCell(j).toString();
            }
        }
        workbook.close();
        return data;
    }
}
