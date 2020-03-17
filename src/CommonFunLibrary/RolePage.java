package CommonFunLibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RolePage {
@FindBy(xpath="//*[@id=\"Table_02\"]/tbody/tr[2]/td/table/tbody/tr[4]/td/a/img")
WebElement ClickRoles;
public void navigateRoles()throws Throwable
{
	ClickRoles.click();
	Thread.sleep(5000);
}
}