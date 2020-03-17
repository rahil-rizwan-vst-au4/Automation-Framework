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

import Utilities.ExcelFileUtil;

public class Using_excel {
	static WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	String inputpath="F:\\seleniumproject\\Automation_Framework\\TestInput\\dataloginxl.xlsx";
	String outputpath="F:\\seleniumproject\\Automation_Framework\\TestOutput\\dataloginresult.xlsx";
	@BeforeTest
	public void setup() {
		report=new ExtentReports("./Reports/dataprovidr.html");
		System.setProperty("webdriver.chrome.driver", "F:\\seleniumproject\\Automation_Framework\\CommonDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	@Test(dataProvider="Supplydata")
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
public Object[][] Supplydata() throws Throwable{
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	int rc=xl.rowcount("login");
	Object[][] login =new Object[rc][2];
	for(int i=1;i<=rc;i++) {
	login[i-1][0]=xl.getCelldata("login", i, 0);
	login[i-1][1]=xl.getCelldata("login", i,1);
	if(driver.getCurrentUrl().contains("dash")) {
		xl.setcelldata("login", i,2, "pass", outputpath);
	}
	else {
		xl.setcelldata("login", i, 2, "fail", outputpath);
	}
	
}
	return login;  
}
@AfterTest
public void teardown() {
	report.endTest(test);
	report.flush();
	driver.close();
}
}




