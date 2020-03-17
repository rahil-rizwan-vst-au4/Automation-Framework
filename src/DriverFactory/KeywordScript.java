package DriverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.BranchUpdation;
import CommonFunLibrary.BranchesPages;
import CommonFunLibrary.LoginPage;
import CommonFunLibrary.LogoutPage;
import CommonFunLibrary.NewBranchCreation;
import CommonFunLibrary.RoleCreation;
import CommonFunLibrary.RolePage;
import CommonFunLibrary.RoleUpdation;
import Constant.PBconstant;
import Utilities.ExcelFileUtil;

public class KeywordScript extends PBconstant {
	String inputpath="F:\\seleniumproject\\Automation_Framework\\TestInput\\testcases.xlsx";
	String outputpath="F:\\seleniumproject\\Automation_Framework\\TestOutput\\testresult.xlsx";
	ExtentReports report;
	ExtentTest test;
	String TCsheet="TestCases";
	String TSsheet="TestSteps";
	
	@Test
	public void startest()throws Throwable {
		report=new ExtentReports("./Extent-report/testres.html");
		LoginPage  login=PageFactory.initElements(driver,LoginPage.class);
		BranchesPages branch=PageFactory.initElements(driver, BranchesPages.class);
		NewBranchCreation branchc=PageFactory.initElements(driver,NewBranchCreation.class);
		BranchUpdation branchup=PageFactory.initElements(driver, BranchUpdation.class);
		RolePage role=PageFactory.initElements(driver, RolePage.class);
		RoleCreation rolec=PageFactory.initElements(driver, RoleCreation.class);
		RoleUpdation roleup=PageFactory.initElements(driver, RoleUpdation.class);
		LogoutPage logout=PageFactory.initElements(driver,LogoutPage.class);
		boolean result=false;
		String tcresult="";
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int TCcount=xl.rowcount(TCsheet);
		int TScount=xl.rowcount(TSsheet);
		Reporter.log(TCcount+"       "+TScount,true);
		for(int i=1;i<=TCcount;i++) {
			test=report.startTest("Keyword Framework");
			test.assignAuthor("Rahil","QA");
			test.assignCategory("Keyword Driven Framework");
			String Execute=xl.getCelldata(TCsheet, i, 2);
			if(Execute.equalsIgnoreCase("Y")) {
				String Tcid=xl.getCelldata(TCsheet, i, 0);
				for(int j=1;j<=TScount;j++) {
					String Description=xl.getCelldata(TSsheet, j, 2);
					String Tsid=xl.getCelldata(TSsheet, j, 0);
					if(Tcid.equalsIgnoreCase(Tsid)) {
						String keyword=xl.getCelldata(TSsheet, j, 3);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							result=login.verifyloginpage("Admin", "Admin");
							test.log(LogStatus.INFO, Description);
							}
							else if(keyword.equalsIgnoreCase("NewBranchCreation"))
							{
								branch.navigateBranches();
								result=branchc.verfiyNewBranchCreation("Mehdipatnam12", "Rethibowlisignal22", "12345","INDIA", "GOA", "GOA");
								test.log(LogStatus.INFO, Description);
							}
						else if(keyword.equalsIgnoreCase("UpdateBranch"))
						{
							branch.navigateBranches();
							result=branchup.verifyBranchUpdation("Ladakh9", "Secunderabadrailwaysation");
							test.log(LogStatus.INFO, Description);	
						}
						else if (keyword.equalsIgnoreCase("NewRoleCreation")) {
							role.navigateRoles();
							result=rolec.verifyrolecreation("Rahil", "Developer", "E");
							test.log(LogStatus.INFO, Description);
							
						}
						else if(keyword.equalsIgnoreCase("RoleUpdation")) {
							role.navigateRoles();
							result=roleup.verifyRoleUpdation("Rizwan");
							test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							result=logout.verifylogout();
							test.log(LogStatus.INFO, Description);
						}
						String tsres="" ;
						if(result) {
							tsres="pass";
						xl.setcelldata(TSsheet, j, 4, tsres, outputpath);
						test.log(LogStatus.PASS,Description);
						}
						else {
							tsres="Fail";
							xl.setcelldata(TSsheet, j, 4, tsres, outputpath);
							test.log(LogStatus.FAIL, Description);
						}
						if(!tsres.equalsIgnoreCase("Fail")) {
							tcresult=tsres;
						}}
						report.endTest(test);
						report.flush();
						}
					xl.setcelldata(TCsheet, i, 3, tcresult, outputpath);
					}
				else
				{
					xl.setcelldata(TCsheet, i, 3, "blocked", outputpath);
				}
				}
			}
			
	}
		
		
	
		
	

