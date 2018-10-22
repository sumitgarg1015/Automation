package config;

public interface Constants {
	
	public static final String chromeDriverProperty = "webdriver.chrome.driver";
	public static final String IEDriverProperty = "webdriver.ie.driver";
	public static final String chromeDriverPath = "C://Automation//Jars//chromedriver.exe";
	public static final String IEDriverPath = "C://Automation//Jars//IEDriverServer.exe";
	public static final String screenShotPath = "C://Automation//GoogleAccounts//screen//";
	
	public static final String url = "https://account.google.com";
	
	public static final String filePath = "C://Automation//GoogleAccounts//src//dataEngine//dataEngine.xlsx";
	public static final String filePath_Result = "C://Automation//GoogleAccounts//src//dataEngine//dataEngine_Result.xlsx";
 	public static final String html_Result= "C://Automation//Results//results.html";
	
	public static final String sheet_TestSteps = "TestSteps";
	public static final String sheet_TestCases = "TestCases";

	
	public static final int col_testCaseID = 0;
	public static final int col_testScenario = 1;
	public static final int col_runMode = 2;
	public static final int col_Keywords = 6;
	public static final int col_pageObject = 5;
	
	public static final int col_tcResult = 3;
	public static final int col_tsStatus = 8;
	public static final int col_tsValue = 7;
	
	public static final String OR_Path = "C://Automation//GoogleAccounts//src//config//OR";
	
	public static final String status_Fail = "Fail";
	public static final String status_Pass = "Pass";
	

}
