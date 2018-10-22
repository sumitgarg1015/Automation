package driverEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.ActKeywords;
import config.Constants;
import utility.ExecUtils;
import utility.Log;


public class driverScript {

//	Variables which are set as private were used only in this class member methods
//Variables which are public were used in more than one class member methods
//Variables were defined globally so that the relevant value could be accessed- 
	//-across multiple methods of this class and another class if it is public
	
	private static String keyWord, pageObj, testID, runMode, txtVal;
	private static Method[] method;
	public static Properties OR;
	private ActKeywords actionKeys;
	private static int rowNum, tcrowCount, testCaseCount, testStepsCount, tsRowNum, rowCounter;
	public static boolean tsresult;
	private static ExtentReports er;
	private static ExtentTest et;
	
	public driverScript() throws Exception{
		actionKeys = new ActKeywords();
		method = actionKeys.getClass().getMethods();
	}
	

	public static void main(String[] args) throws Exception{
		
				
		FileInputStream fs = new FileInputStream(Constants.OR_Path);
		OR = new Properties(System.getProperties());
		OR.load(fs);
		
		DOMConfigurator.configure("log4j.xml");
		er = new ExtentReports(Constants.html_Result);
		
		driverScript ds = new driverScript();
		ds.start_Testing();
		
	}
	
	public void start_Testing() throws Exception{
		
		int i=0;
		
		ExecUtils.setExcelUtils(Constants.filePath);
		
		tcrowCount = ExecUtils.getRowCount(Constants.sheet_TestCases);
		testStepsCount = ExecUtils.getRowCount(Constants.sheet_TestSteps);
		
		for (i=1;i<tcrowCount;i++){
			runMode = ExecUtils.getCellData(i,Constants.col_runMode,Constants.sheet_TestCases);
			
			//Check the status of Test case if set to execute
			if(runMode.equalsIgnoreCase("Yes")){

				testID = ExecUtils.getCellData(i,Constants.col_testCaseID,Constants.sheet_TestCases);
				et = er.startTest(testID);
				//Find the starting row of Test case in the Test Steps sheet
				rowNum = ExecUtils.getRowNum(testStepsCount, testID, Constants.sheet_TestSteps);
				
				//Find the number of test steps of a Test case in the Test Steps sheet
				testCaseCount = ExecUtils.getStepsCount(testID, Constants.sheet_TestSteps);
				
				//Calculate the ending row of a test case in the Test Steps sheet 
				tsRowNum = testCaseCount + rowNum;
				//Log to start the test case execution
				Log.startTestCase(testID);			
				
				//By default set to value True for test steps status
				tsresult = true;
				//Loop from starting row of test case till ending row of test cases as calculated above
				for(rowCounter = rowNum;rowCounter<tsRowNum;rowCounter++){
					
					//Pick up keyword from the excel sheet
					keyWord = ExecUtils.getCellData(rowCounter,Constants.col_Keywords,Constants.sheet_TestSteps);
					
					//Pick up page object from the excel sheet so that accordingly locator could be picked up from the object repository OR
					pageObj = ExecUtils.getCellData(rowCounter,Constants.col_pageObject, Constants.sheet_TestSteps);
					
					//Pick up the value to enter into the webpage
					txtVal = ExecUtils.getCellData(rowCounter,Constants.col_tsValue, Constants.sheet_TestSteps);
					
					//Call function to execute the keyword
					executeKeywords();
					if (tsresult){
						ExecUtils.setCellData(rowCounter, Constants.col_tsStatus, Constants.sheet_TestSteps, Constants.status_Pass);
						et.log(LogStatus.PASS, pageObj);
					}else{
						ExecUtils.setCellData(rowCounter, Constants.col_tsStatus, Constants.sheet_TestSteps, Constants.status_Fail);
						et.log(LogStatus.FAIL, pageObj);
						ActKeywords.close_Browser("","","");
						break;
					}
				}//innerloop is ended here

				if (tsresult)
					ExecUtils.setCellData(i, Constants.col_tcResult, Constants.sheet_TestCases, Constants.status_Pass);
				else{
					ExecUtils.setCellData(i, Constants.col_tcResult, Constants.sheet_TestCases, Constants.status_Fail);
				}
				er.endTest(et);
				Log.endTestCase(testID);
				ActKeywords.driver.quit();
			} //if condition is ended here
				
		}//outerloop is ended here
		er.flush();
	}
	
//	@Test (dependsOnMethods={"start_Testing"})
	public static void executeKeywords() throws Exception{
		
			//Checking if tResult is false
			for (int i=0;i<method.length;i++){
				if(keyWord.equals(method[i].getName())){
					method[i].invoke(keyWord, pageObj, txtVal, testID);
					break;
				}//if condition is ended here
				
			}//loop is ended here
			
	}
	

}

