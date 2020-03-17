package Utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	//constructor
	public ExcelFileUtil (String excelpath) throws Throwable {
		FileInputStream f=new FileInputStream(excelpath);
		wb=WorkbookFactory.create(f);
	}
public int rowcount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
public int colcount(String sheetname)
{
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
public String getCelldata(String sheetname,int row,int col) {
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC)
			{
		int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
		data=String.valueOf(celldata);
			}
	else {
		data=wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
	}
	return data;
}
public void setcelldata(String sheetname,int row,int col,String status,String writexcel) throws Throwable {
	Sheet sheet=wb.getSheet(sheetname);
	Row rownum=sheet.getRow(row);
	Cell cell=rownum.createCell(col);
	cell.setCellValue(status);
	if (status.equalsIgnoreCase("pass")) {
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(col).setCellStyle(style);
	}
	else if (status.equalsIgnoreCase("Fail")) {
    CellStyle style=wb.createCellStyle();
    Font font=wb.createFont();
    font.setColor(IndexedColors.RED.getIndex());
    font.setBoldweight(font.BOLDWEIGHT_BOLD);
    style.setFont(font);
    rownum.getCell(col).setCellStyle(style);
	}
	FileOutputStream fo=new FileOutputStream(writexcel);
	wb.write(fo);
	
}
}


