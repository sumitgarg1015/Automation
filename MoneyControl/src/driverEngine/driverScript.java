package driverEngine;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.Select;

import config.Constants;

public class driverScript {
	
	public static WebDriver driver;
	public static Select slt;
	public static void main(String[] args) {
	
			try {
/*				File fl = new File(Constants.browserPath);
				
				FirefoxBinary fb = new FirefoxBinary(fl);
				ProfilesIni pf = new ProfilesIni();
				
				FirefoxProfile fp = pf.getProfile("SeleniumQA");
				
				FirefoxOptions fopt = new FirefoxOptions();
				
				fopt.setProfile(fp);
				fopt.setBinary(fb);*/
				
				System.setProperty("webdriver.chrome.driver", Constants.driverPath);
				
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.manage().window().maximize();
				
				
				driver.get(Constants.appUrl);
				
				driverActions();
				
			}catch(Exception e){
				System.out.println("Unable to open the browser!");
			}finally {
				driver.quit();
			}

	}
	
	public static void driverActions() {
		driver.findElement(By.linkText("Mutual Funds")).click();
		driver.findElement(By.xpath("//a[contains(@href,'mutualfundreturns')]")).click();
		
		slt = new Select(driver.findElement(By.id("ff_id")));
		
		slt.selectByValue("BA");
		
		slt = new Select(driver.findElement(By.id("im_id")));
		slt.selectByValue("MBA028");
		
		driver.findElement(By.xpath("//input[@name='fdt1']")).sendKeys("2018-10-22");
		driver.findElement(By.xpath("//input[@name='fdt2']")).sendKeys("2030-09-21");
		
		driver.findElement(By.xpath("//input[@title='Calculate']")).click();
		
		List<WebElement> rowLst = driver.findElements(By.xpath("//table[@class='tblcalc']/tbody/tr"));
		
		for (WebElement row : rowLst) {
			List<WebElement> colLst = row.findElements(By.tagName("td"));
			System.out.println("Period    	AnnualisedReturns (%)    AbsoluteReturns (%)    Performance Rank(within fund classes)");
			System.out.println(colLst.get(0).getText()+"    "+colLst.get(1).getText()+"    "+colLst.get(2).getText()+"    "+colLst.get(3).getText());
		}
		
		
	}
	
	
	

}
