package DriverFactory;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ApplicationLayer.AddEmp;
import ApplicationLayer.AddUser;
import ApplicationLayer.LoginPage;
import ApplicationLayer.LogoutPage;
import Utilities.ExcelFileUtil;

public class DataDrivenPOM {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	String inputpath="F:\\seleniumproject\\Automation_Framework\\TestInput\\emp.xlsx";
	String outputpath="F:\\seleniumproject\\Automation_Framework\\TestOutput\\add.xlsx";
	@BeforeTest
	public void setup() throws Throwable {
		report=new ExtentReports("./Extent-report/addemp.html");
		System.setProperty("webdriver.chrome.driver", "F:\\seleniumproject\\Automation_Framework\\CommonDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://orangehrm.qedgetech.com/");
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		login.verifylogin("Admin","Qedge123!@#");
	}
	@Test(enabled=false)
	public void empcreation() throws Throwable {
		AddEmp emp=PageFactory.initElements(driver, AddEmp.class);
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int rc=xl.rowcount("Emp");
		int cc=xl.colcount("Emp");
		Reporter.log(rc+"     "+cc,true );
		for(int i=1;i<=rc;i++) {
			test=report.startTest("datadrivenwithpom");
			String firstname=xl.getCelldata("Emp", i, 0);
			String lastname=xl.getCelldata("Emp", i, 1);
			String empid=xl.getCelldata("Emp", i, 2);
			emp.verifyAddEmp(firstname, lastname, empid);
			if (driver.getCurrentUrl().contains("empNumber")) {
				test.log(LogStatus.PASS, "empid created successfully");
				Reporter.log("empid created");
				xl.setcelldata("Emp", i, 3, "pass", outputpath);
			}	  
			else {
				test.log(LogStatus.FAIL, "creation failed");
				xl.setcelldata("Emp", i, 3, "Fail", outputpath);
			}
			report.endTest(test);
			report.flush();
		}

	}
	@Test
	public void usercreation() throws Throwable {
		AddUser user=PageFactory.initElements(driver, AddUser.class);
		ExcelFileUtil xl= new ExcelFileUtil("F:\\seleniumproject\\Automation_Framework\\TestInput\\usercreate.xlsx");
		int rc=xl.rowcount("user");
		int cc=xl.colcount("user");
		Reporter.log(rc+"   "+cc);
		for(int i=1;i<=rc;i++) {
			test=report.startTest("create useremp");
			String empname=xl.getCelldata("user", i, 0);
			String username=xl.getCelldata("user", i, 1);
			String password=xl.getCelldata("user", i, 2);
			String cpassword=xl.getCelldata("user", i, 3);
			user.verifyuser(empname, username, password, cpassword);
			if(driver.getCurrentUrl().contains("")) {
				test.log(LogStatus.PASS, "user created successfully");
				Reporter.log("user created",true);
				xl.setcelldata("user", i, 4, "pass","F:\\seleniumproject\\Automation_Framework\\TestOutput\\user.xlsx");
			}
			else {
				test.log(LogStatus.FAIL, "user doesnot created");
				Reporter.log("user not creatd",true);
				xl.setcelldata("user", i,3, "Fail", "F:\\seleniumproject\\Automation_Framework\\TestOutput\\user.xlsx");
			}
			report.endTest(test);
			report.flush();	
		}
	}
	@AfterTest
	public void teardown() throws Throwable {
		LogoutPage logout=PageFactory.initElements(driver,LogoutPage.class);
		logout.verifylogout();
		driver.close();
	}
}
