package CommonFunLibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BranchesPages {
@FindBy(xpath="//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")
WebElement ClickBranches;
public void navigateBranches()throws Throwable
{
	ClickBranches.click();
	Thread.sleep(5000);
}
}
