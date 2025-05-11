package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginTest")
    public String[][] getData() throws IOException {
        ExcelUtility excelFile = new ExcelUtility(".\\testData\\Opencart_LoginData.xlsx", "Sheet1");
        int totalRows = excelFile.getRowCount()+1;
        int totalColumns = excelFile.getColumnCount(0);
        String[][] data = new String[totalRows][totalColumns];
        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                data[i][j] = excelFile.getCellValue(i,j);
            }
        }

        return data;
    }

    // DataProvider 2

    // DataProvider 3

    // DataProvider 4
}
