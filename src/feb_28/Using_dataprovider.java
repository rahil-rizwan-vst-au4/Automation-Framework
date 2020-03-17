package feb_28;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Using_dataprovider{
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void setup() {
		report=new ExtentReports("./Reports.dataprovider.html");
		System.setProperty("webdriver.chrome.driver", "F:\\seleniumproject\\Automation_Framework\\CommonDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	@Test(dataProvider="supplydata")
	public void verifylogin(String username,String password) {
		test=report.startTest("verifylogin");
		driver.get("http://orangehrm.qedgetech.com/");
		driver.findElement(By.name("txtUsername")).sendKeys(username);
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.name("Submit")).click();
		if(driver.getCurrentUrl().contains("dash")) {
			test.log(LogStatus.PASS, "login success");
			Reporter.log("success",true);
		}
		else {
			test.log(LogStatus.FAIL, "login fail");
			Reporter.log("fail",true);
		}
	}
	@DataProvider
	public Object[][] supplydata() {
		Object[][] login=new Object[3][2];
		login[0][0]="Admin";
		login[0][1]="Qedge123!@#";
		login[1][0]="test";
		login[1][1]="Qedge123!@#";
        return login;	
	}
	@AfterTest
	public void teardown() {
		report.endTest(test);
		report.flush();
		driver.close();
	}
	 
}