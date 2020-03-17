package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import ApplicationLayer.AddEmp;
import ApplicationLayer.AddUser;
import ApplicationLayer.LoginPage;
import ApplicationLayer.LogoutPage;

public class TestScript {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void generatereport() {
		report=new ExtentReports("./Extent-Report/useremp.html");
	}
	@BeforeMethod
	public void setup() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "F:\\seleniumproject\\Automation_Framework\\CommonDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://orangehrm.qedgetech.com/");
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		login.verifylogin("Admin", "Qedge123!@#");
	}
	@Test
	public void verifyAddEmp() throws Throwable{
		test=report.startTest("Empcreation");
		test.assignAuthor("rahil");
		test.assignCategory("datadriven");
		AddEmp emp=PageFactory.initElements(driver,AddEmp.class);
		emp.verifyAddEmp("Mohammad", "Rahil", "87328");
		if(driver.getCurrentUrl().contains("empNumber")) {
			test.log(LogStatus.PASS, "emp creation success");
			Reporter.log("sucess",true);
		}
		else {
			test.log(LogStatus.FAIL, "emp creation failed");
			Reporter.log("failed",true);
		}
	}
	@Test
	public void verifyuser()throws Throwable{
		test=report.startTest("verify user");
		AddUser user=PageFactory.initElements(driver, AddUser.class);
		user.verifyuser("Ajaytrq loiyssse", "rahil", "Qedge123!@#", "Qedge123!@#");
		if (driver.getCurrentUrl().contains("viewSystemUsers")) {
			test.log(LogStatus.PASS, "user created");
			Reporter.log("user created");
		}
		else
			{test.log(LogStatus.FAIL, "user not created");
		Reporter.log("user not created");
			}
	}
	@AfterMethod
	public void tearDown() throws Throwable {
		LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
		logout.verifylogout();
		driver.close();
	}
}
