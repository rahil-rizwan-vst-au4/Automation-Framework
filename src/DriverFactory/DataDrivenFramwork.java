package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class DataDrivenFramwork {
	WebDriver driver;
	File screens;
	ExtentReports report;
	ExtentTest test;
	String inputpath="F:\\seleniumproject\\Automation_Framework\\TestInput\\orangehrm.xlsx";
	String output="F:\\seleniumproject\\Automation_Framework\\TestOutput\\results.xlsx";
	@BeforeTest
	public void setup() {
		report=new ExtentReports("./Extent-report/reportorange.html");
		System.setProperty("webdriver.chrome.driver", "F:\\seleniumproject\\Automation_Framework\\CommonDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	@Test
	public void login() throws Throwable {
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int rc=xl.rowcount("loged");
		int cc=xl.colcount("loged");
		Reporter.log("no.of rows::"+rc+"    "+"no.of cols:::"+cc,true);
		for(int i=1;i<=rc;i++)
		{
			test=report.startTest("verify login");
			test.assignAuthor("rahil");
			test.assignCategory("datadriven");
			String username=xl.getCelldata("loged", i, 0);
			String password=xl.getCelldata("loged", i, 1);
			Reporter.log(username+"   "+ password,true);
			driver.get("http://orangehrm.qedgetech.com/");
			driver.manage().window().maximize();
			driver.findElement(By.name("txtUsername")).sendKeys(username);
			driver.findElement(By.name("txtPassword")).sendKeys(password);
			driver.findElement(By.name("Submit")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			if(driver.getCurrentUrl().contains("dash")) {
				test.log(LogStatus.PASS, "successfully login");
				Reporter.log("loged success",true);
				xl.setcelldata("loged", i, 2, "login sucess", output);
				xl.setcelldata("loged", i, 3, "pass", output);
			}
			else {
				screens=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screens,new File("./screens//iteration"+i+"shot.png"));
				String message=driver.findElement(By.id("spanMessage")).getText();
				test.log(LogStatus.FAIL, message);
				Reporter.log(message,true);
				xl.setcelldata("loged", i, 2, message, output);
				xl.setcelldata("loged", i, 3, "fail", output);;
			}
			report.endTest(test);
			report.flush();
		}	
	}
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
