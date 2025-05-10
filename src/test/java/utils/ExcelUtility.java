package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtility implements AutoCloseable {

    private final XSSFWorkbook workbook;
    private final XSSFSheet sheet;

    public ExcelUtility(String filePath, String sheetName) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fileInputStream); // Create workbook only if file path is valid
        } catch (IOException e) {
            throw new IOException("Error loading Excel file: " + filePath, e);
        }

        sheet = workbook.getSheet(sheetName);
        if (sheet == null) { // Verify sheetName is a valid sheet name
            throw new IllegalArgumentException("Sheet '" + sheetName + "' does not exist in file: " + filePath);
        }
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public int getColumnCount(int rowNum) {
        XSSFRow row = sheet.getRow(rowNum);
        return row != null ? row.getLastCellNum() : 0;
    }

    public String getCellValue(int rowNum, int colNum) {
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell cell = (row != null) ? row.getCell(colNum) : null;
        return (cell != null) ? new DataFormatter().formatCellValue(cell) : "";
    }

    @Override
    public void close() throws Exception {
        workbook.close();
    }
}