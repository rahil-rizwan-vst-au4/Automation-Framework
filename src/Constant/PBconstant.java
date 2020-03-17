package Constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBconstant{
	public static Properties p;
	public static WebDriver driver;
	public static FileInputStream f;
	@BeforeMethod
	public void setup() throws Throwable {
		p=new Properties();
		f=new FileInputStream("F:\\seleniumproject\\Automation_Framework\\PropertyFile\\Environment.properties");
		p.load(f);
		if (p.getProperty("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "F:\\seleniumproject\\Automation_Framework\\CommonDrivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get(p.getProperty("url"));
			driver.manage().window().maximize();
		}
		else if (p.getProperty("Browser").equalsIgnoreCase("FireFox"))  {
			System.setProperty("webdriver.gecko.driver", "F:\\seleniumproject\\Automation_Framework\\CommonDrivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}
	}
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	
}
