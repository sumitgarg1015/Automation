package testFunc;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.Constants;

public class SearchOps {
	
	private static ExtentReports er;
	private static ExtentTest et;
	

	public void generateReport(){
		er = new ExtentReports(Constants.html_Result, true);
		er.addSystemInfo("Sumit", "GoogleTestProject");

	}


	public void checkIf(){
		et = er.startTest("Test1");
		System.out.println("This is test 1");
		et.log(LogStatus.PASS, "Pass");
		er.endTest(et);
	}
	

	public void checkAfter(){
		et = er.startTest("Test2");
		System.out.println("This is test 1");
		et.log(LogStatus.FAIL, "Pass");
		er.endTest(et);
	}
	

	public void endTest(){
		er.flush();
		
	}

}
