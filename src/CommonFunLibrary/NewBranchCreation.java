package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class NewBranchCreation {
	public WebDriver driver;
	public NewBranchCreation(WebDriver  driver) {
		this.driver=driver;
	}
	@FindBy(xpath="//input[@id='BtnNewBR']")
	WebElement ClicknewBranch;
	@FindBy(name="txtbName")
	WebElement EnterBname;
	@FindBy(name="txtAdd1")
	WebElement EnterAddress1;
	@FindBy(name="txtZip")
	WebElement EnterZipcode;
	@FindBy(name="lst_counrtyU")
	WebElement SelectCountry;
	@FindBy(name="lst_stateI")
	WebElement Selectstate;
	@FindBy(name="lst_cityI")
	WebElement SelectCity;
	@FindBy(name="btn_insert")
	WebElement ClickSubmit;
	public Boolean verfiyNewBranchCreation(String bname,String Address1,String zipcode,String country,String state,
			String city ) throws Throwable
	{
		ClicknewBranch.click();
		Thread.sleep(2000);
		EnterBname.sendKeys(bname);
		Thread.sleep(2000);
		EnterAddress1.sendKeys(Address1);
		Thread.sleep(2000);
		EnterZipcode.sendKeys(zipcode);
		Thread.sleep(2000);
		SelectCountry.sendKeys(country);
		Thread.sleep(2000);
		Selectstate.sendKeys(state);
		Thread.sleep(2000);
		SelectCity.sendKeys(city);
		Thread.sleep(2000);
		ClickSubmit.click();
		Thread.sleep(2000);
		String alertmessage=driver.switchTo().alert().getText();
		System.out.println(alertmessage);
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		String exptext="new Branch";
		if(alertmessage.toLowerCase().contains(exptext.toLowerCase())) {
			Reporter.log("New Branch Created successfully",true);
			return true;
		}
		else {
			Reporter.log("New Branch Creation Failed",true);
			return false;
		}
	}

}
