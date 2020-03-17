package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class RoleCreation {
	public static WebDriver driver;
	public RoleCreation(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(xpath="//*[@id=\"Table_02\"]/tbody/tr[2]/td/table/tbody/tr[4]/td/a/img")
	WebElement clickRoles;
	@FindBy(name="btnRoles")
	WebElement clickNewRoles;
	@FindBy(xpath="txtRname")
	WebElement Enterrolename ;
	@FindBy(name="txtRDesc")
	WebElement EnterRoleDesc;
	@FindBy(name="lstRtypeN")
	WebElement SelectType;
	@FindBy(name="btninsert")
	WebElement ClickButton;


	public Boolean verifyrolecreation(String RoleName,String RoleDesc,String RoleType) throws Throwable {
		clickRoles.click();
		clickNewRoles.click();
		Enterrolename.sendKeys(RoleName);
		EnterRoleDesc.sendKeys(RoleDesc);
		SelectType.sendKeys(RoleType);
		ClickButton.click();
		Thread.sleep(2000);
		String alert=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		if(alert.toLowerCase().contains("New Role".toLowerCase())) {
			Reporter.log("New Role created successfully",true);
			return true;
		}
		else
		{
			Reporter.log("Failed",true);
			return false;
		}
	}
}