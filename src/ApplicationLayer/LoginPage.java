package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
WebDriver driver;
Actions ac;
public LoginPage(WebDriver driver) {
	this.driver=driver;
}
	@FindBy(css="#txtUsername")
	WebElement username;
	@FindBy(css="#txtPassword")
	WebElement password;
	@FindBy(css="#btnLogin")
	WebElement login;
	public void verifylogin(String username,String password) throws Throwable{
		ac=new Actions(driver);
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		ac.moveToElement(login).click().perform();
		Thread.sleep(3000);
	}
}
