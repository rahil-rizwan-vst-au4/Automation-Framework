package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddEmp {
	WebDriver driver;
	Actions ac;
	public AddEmp(WebDriver driver) {
	this.driver=driver;
}
@FindBy(id="menu_pim_viewPimModule")
WebElement clickpim;
@FindBy(id="btnAdd")
WebElement clickadd;
@FindBy(name="firstName")
WebElement enterfname;
@FindBy(name="lastName")
WebElement enterlname;
@FindBy(id="employeeId")
WebElement entereid;
@FindBy(id="btnSave")
WebElement clicksave;

public void verifyAddEmp(String firstname,String lastname,String eid) throws Throwable {
	ac=new Actions(driver);
	ac.moveToElement(clickpim).click().perform();
	Thread.sleep(3000);
	ac.moveToElement(clickadd).click().perform();
	Thread.sleep(3000);
	this.enterfname.sendKeys(firstname);
	this.enterlname.sendKeys(lastname);
	this.entereid.clear();
	this.entereid.sendKeys(eid);
	this.clicksave.click();
	Thread.sleep(3000);
}
}


