package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage{
	public static WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(name="txtuId")
	WebElement EnterUserame;
	@FindBy(name="txtPword")
	WebElement EnterPassword;
	@FindBy(name="login")
	WebElement clicklogin;
	
public boolean verifyloginpage(String username,String password) throws Throwable {
	this.EnterUserame.sendKeys(username);
	this.EnterPassword.sendKeys(password);
	Thread.sleep(4000);
	clicklogin.click();
	Thread.sleep(2000);
	String expurl="adminflow";
	String acturl=driver.getCurrentUrl();
	if (acturl.toLowerCase().contains(expurl.toLowerCase())) {
		Reporter.log("login successfully",true);
		 return true;
		}
	else {
		Reporter.log("login failed",true);
		return false;
	}
        	  
  }
}

