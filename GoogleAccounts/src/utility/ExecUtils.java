package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import config.Constants;
import driverEngine.driverScript;

public class ExecUtils {
	
	public static XSSFWorkbook ExcelWb;
	private static XSSFSheet ExcelSheet;
//	private static XSSFRow ExcelRow;
	private static XSSFCell ExcelCell;
	private static String cellData;
	
	public static void setExcelUtils(String filePath){
		try{
			FileInputStream fs = new FileInputStream(filePath);
			ExcelWb = new XSSFWorkbook(fs);
		}catch(Exception e){
			Log.info("No such file exists: " + filePath);
		}

	}
	
	public static String getCellData(int RowNum, int ColNum, String sheetName){
		
		cellData = "";
		try{
			ExcelSheet = ExcelWb.getSheet(sheetName);
			ExcelCell = ExcelSheet.getRow(RowNum).getCell(ColNum);
			cellData = ExcelCell.getStringCellValue();
		}catch(Exception e){
			Log.info("Unable to read the data from the Cell (" + RowNum + "," + ColNum + ") from " + sheetName);
		}
		return cellData;
		
	}
	
public static void setCellData(int rowNum, int colNum, String sheetName, String status){
		
		try{
			ExcelSheet = ExcelWb.getSheet(sheetName);
			//Return blank as null if there was no cell written previously
			ExcelCell = ExcelSheet.getRow(rowNum).getCell(colNum, Row.RETURN_BLANK_AS_NULL);

			if(ExcelCell==null){
				ExcelCell = ExcelSheet.getRow(rowNum).createCell(colNum);
			}
			ExcelCell.setCellValue(status);
			
			FileOutputStream fo = new FileOutputStream(Constants.filePath);
			ExcelWb.write(fo);
			fo.close();
			
			//The below statement is setting to current Excel Sheet whenever this method is called so that next time only current excel sheet should be used for status updates
			setExcelUtils(Constants.filePath); 
			
		}catch(Exception e){
			Log.info("Unable to write data in the Excel:"+e.toString());
			driverScript.tsresult = false;
		}
		
	}
	
	public static int getRowCount(String sheetName){
		
		int rowCount = 0;
		try{
			ExcelSheet = ExcelWb.getSheet(sheetName);
			rowCount = ExcelSheet.getPhysicalNumberOfRows();
		}catch(Exception e){
			Log.info("Unable to read the Excel");
		}
		return rowCount;
	}
	
	//This will return the rowNumber of Test Case ID from the Excel Sheet
	public static int getRowNum(int rowCount, String testID, String sheetName){
		int i=0;
		try{
			ExcelSheet = ExcelWb.getSheet(sheetName);
			String actTestID;
			for (i=1;i<rowCount;i++){
				actTestID = getCellData(i,Constants.col_testCaseID, sheetName);
				if (actTestID.equals(testID))
					break;
			}
		}catch(Exception e){
			Log.info(e.toString());
			Log.info("No Such Testcase "+ testID + " found in the TestSteps sheet");
		}
		return i;
	}
	
	public static int getStepsCount(String testID, String sheetName) throws Exception{
		
		int tcCount = 0;
		try{
			ExcelSheet = ExcelWb.getSheet(sheetName);
			int rowCount = getRowCount(sheetName);
			for(int i=1;i<rowCount;i++){
				if(testID.equals(getCellData(i,Constants.col_testCaseID, sheetName)))
						tcCount ++;
			}
		}catch(Exception e){
			Log.info("No Such Testcase "+ testID + " found in the TestSteps sheet");
			Log.info(e.toString());
		}
		return tcCount;
	}
	
	
	
	
}
