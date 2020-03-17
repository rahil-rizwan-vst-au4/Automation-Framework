package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LogoutPage {
public  WebDriver driver;
public LogoutPage(WebDriver driver) {
	this.driver=driver;
}
@FindBy(xpath="//td//td//td//td[3]//a[1]//img[1]")
WebElement Clicklogout;
@FindBy(name="login")
WebElement Clicklogin;
public Boolean verifylogout() throws Throwable {
	Clicklogout.click();
	Thread.sleep(3000);
	if (Clicklogin.isDisplayed()) {
		Reporter.log("logout success",true);
		return true;
	}
	else {
		Reporter.log("logout failed",true);
		return false;
	}
}
}
