package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddUser {
	WebDriver driver;
	Actions ac;
	public AddUser(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(id="menu_admin_viewAdminModule")
	WebElement clickadmin;
	@FindBy(id="menu_admin_UserManagement")
	WebElement clickusermang;
	@FindBy(id="menu_admin_viewSystemUsers")
	WebElement  clickuser;
	@FindBy(id="btnAdd")
	WebElement clickadd;
	@FindBy(name="systemUser[employeeName][empName]")
	WebElement Enterename;
	@FindBy(name="systemUser[userName]")
	WebElement Enterusername;
	@FindBy(name="systemUser[password]")
	WebElement Enterpassword;
	@FindBy(name="systemUser[confirmPassword]")
	WebElement Entercpassword;
	@FindBy(id="btnSave")
	WebElement clicksave;
	public void verifyuser(String ename,String username,String password,String cpassword) throws Throwable {
		ac=new Actions(driver);
		ac.moveToElement(clickadmin).perform();
		Thread.sleep(3000);
		ac.moveToElement(clickusermang).perform();
		Thread.sleep(3000);
		ac.moveToElement(clickuser).click().perform();
		Thread.sleep(3000);
		ac.moveToElement(clickadd).click().perform();
		Thread.sleep(3000);
		this.Enterename.sendKeys(ename);
		this.Enterusername.sendKeys(username);
		this.Enterpassword.sendKeys(password);
	    this.Entercpassword.sendKeys(cpassword);
	    this.clicksave.click();
		Thread.sleep(3000);
	    
}
}