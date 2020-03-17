package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdation {
	public WebDriver driver;
	public BranchUpdation(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
	WebElement ClickEdit;
	@FindBy(name="txtbnameU")
	WebElement Enterbname;
	@FindBy(name="txtadd1u")
	WebElement EnterAddress1;
	@FindBy(name="btnupdate")
	WebElement ClickUpdate;
	public Boolean verifyBranchUpdation(String bname,String Address1) throws Throwable {
		ClickEdit.click();
		Thread.sleep(2000);
		Enterbname.sendKeys(bname);
		Thread.sleep(2000);
		EnterAddress1.sendKeys(Address1);
		ClickUpdate.click();
		Thread.sleep(2000);
		String updatemsg=driver.switchTo().alert().getText();
		System.out.println(updatemsg);
		driver.switchTo().alert().accept();
		String exptext="Branch upd";
		if(updatemsg.toLowerCase().contains(exptext.toLowerCase())) {
			Reporter.log("Branch updated successfully",true);
			return true;
		}
		else {
			Reporter.log("branch updation failed",true);
			return false;
		}
		
	}

}
