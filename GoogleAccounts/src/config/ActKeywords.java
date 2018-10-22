package config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driverEngine.driverScript;
import utility.Log;
import utility.TakeSnapShot;

import static driverEngine.driverScript.OR;;

public class ActKeywords {
		
		public static WebDriver driver;
		public static WebDriverWait ww;
		
		public static void open_Browser(String obj, String val, String testID){
			
			try{
					Log.info("Opening Browser");
					switch (val){
						case "IE":
							System.setProperty(Constants.IEDriverProperty, Constants.IEDriverPath);
							driver = new InternetExplorerDriver();
							break;
						case "Chrome":
							System.setProperty(Constants.chromeDriverProperty, Constants.chromeDriverPath);
							driver = new ChromeDriver();
							break;
						default:
							driver = new FirefoxDriver();
							break;
					}
					
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}catch(Exception e){
					Log.info("Unable to open the browser: "+e.toString());
					driverScript.tsresult = false;
			}
			
		}

		public static void open_Url(String obj, String val, String testID){
			
			try{
					Log.info("Opening Application UnderTest: " + Constants.url);
					driver.get(Constants.url);
			}catch(Exception e){
				Log.info("Unable to open the URL: "+e.toString());
				driverScript.tsresult = false;
			}
			TakeSnapShot.takeScreenShot(Constants.screenShotPath + "//"+ testID + "//" + "open_Url.jpg");
		}
		
		public static void click_AnotherAccount(String obj, String val, String testID){
			
			try{
					Log.info("Click to Open Page for " + obj);
					if(driver.findElements(By.xpath(OR.getProperty(obj))).size()>0){
						driver.findElement(By.xpath(OR.getProperty(obj))).click();
					}
					else{
							driver.findElement(By.xpath(".//*[text()='Use another account' or text()='Add account']")).click();
					}
			}catch(Exception e){
				Log.info("Unable to open the Sign in Page: "+e.toString());
				driverScript.tsresult = false;
			}
			TakeSnapShot.takeScreenShot(Constants.screenShotPath + "//"+ testID + "//" + obj + ".jpg");
		}
		

		
		public static void 	input(String obj, String val, String testID){
			try{
				Log.info("Enter text in " + obj);
				ww = new WebDriverWait(driver,10);
				ww.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(obj))));
				driver.findElement(By.xpath(OR.getProperty(obj))).sendKeys(val);
			}catch(Exception e){
				Log.info("Unable to enter text in " + obj + ": " + e.toString());
				driverScript.tsresult = false;
			}
			TakeSnapShot.takeScreenShot(Constants.screenShotPath + "//"+ testID + "//" + obj + ".jpg");
		}
		
		public static void 	click(String obj, String val, String testID){
			try{
				Log.info("Click on " + obj);
				driver.findElement(By.xpath(OR.getProperty(obj))).click();
			}catch(Exception e){
				Log.info("Unable to click on "+obj+": "+e.toString());
				driverScript.tsresult = false;
			}
			TakeSnapShot.takeScreenShot(Constants.screenShotPath + "//"+ testID + "//" + obj + ".jpg");
		}


		
		public static void submit_text(String obj, String val, String testID){
			try{
				Log.info("Click on " + obj);
				driver.findElement(By.xpath(OR.getProperty(obj))).submit();
			}catch(Exception e){
				Log.info("Unable to submit "+obj+": "+e.toString());
				driverScript.tsresult = false;
			}
			TakeSnapShot.takeScreenShot(Constants.screenShotPath + "//"+ testID + "//" + obj + ".jpg");

		}
		
		public static void count_Links(String obj, String val, String testID){
			try{
				Log.info("Counting Number of Link in Search Results");
				List<WebElement> lst = driver.findElements(By.partialLinkText(val));
				System.out.println("Number of links found: " + lst.size());
			}catch(Exception e){
				Log.info("Unable to count links: "+e.toString());
				driverScript.tsresult = false;
			}
			TakeSnapShot.takeScreenShot(Constants.screenShotPath + "//"+ testID + "//" + "countLinks.jpg");

		}
		
		public static void close_Browser(String obj, String val, String testID){
			try{
				Log.info("Closing Browser");
				driver.close();
			}catch(Exception e){
				Log.info("Unable to close the Browser: "+e.toString());
				driverScript.tsresult = false;
			}

		}
		
		public static void thread_sleep(String obj, String val, String testID) throws InterruptedException{
			try{
				Log.info("Thread is sleeping");
				Thread.sleep(1000);
			}catch(Exception e){
				Log.info("Unable to wait: "+e.toString());
				driverScript.tsresult = false;
			}

		}
		


}
