package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Reporter;

public class RoleUpdation {
	public static WebDriver driver;
	public RoleUpdation(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(xpath="//*[@id=\"DGRoles\"]/tbody/tr[2]/td[4]/a/img")
	WebElement clickedit;
	@FindBy(name="")
	WebElement Enterusername;
	@FindBy(name="btnupdate")
   WebElement clicksubmit;	
	public Boolean verifyRoleUpdation(String RoleName) {
		Enterusername.sendKeys(RoleName);
		String alert=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		if(alert.toLowerCase().contains("Role Updated".toLowerCase())) {
			Reporter.log("RoleUpdated successfully",true);
			return true;
		}
		else {
			Reporter.log("Roleupdation failed");
			return false;
		}
	}
}
