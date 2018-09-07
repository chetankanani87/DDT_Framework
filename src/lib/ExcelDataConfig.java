package lib;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	XSSFWorkbook wb;
	XSSFSheet sheet1;

	public ExcelDataConfig(String excelPath) throws Exception {
		File src = new File(excelPath);
		FileInputStream fis = new FileInputStream(src);
		/* If we are using .xlsx file then here in this script we use XSSFWorkbook class and
		 * If we are using .xls file then we will use HSSFWorkbook class, and other process will be same. */
		wb = new XSSFWorkbook(fis);
	}

	public String getData(int sheetNumber, int row, int column) {
		sheet1 = wb.getSheetAt(sheetNumber);
		String data = sheet1.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	public int getRowCount(int sheetIndex) {
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}
}
