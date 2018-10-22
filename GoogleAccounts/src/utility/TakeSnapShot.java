package utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import config.ActKeywords;

public class TakeSnapShot {
	
	public static void takeScreenShot(String fileName){
		
		TakesScreenshot scrShot = ((TakesScreenshot)ActKeywords.driver);
		try {
			File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
			File desFile = new File(fileName);
			FileUtils.copyFile(scrFile, desFile);
		} catch (Exception e) {
			Log.info("Unable to take a screenshot: " + e.toString());
		}
		
	}

}
