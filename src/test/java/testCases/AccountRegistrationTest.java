package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AccountRegistratonPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class AccountRegistrationTest {
  
	
	public WebDriver driver;
	
	//WebDriver driver = new ChromeDriver();
	

	
  @BeforeTest
  public void f() 

  {
	    WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	  driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
  }
  
  @Test
  public void reg() throws InterruptedException
  {
	 Thread.sleep(5000);
	  HomePage hp = new HomePage(driver);
	 hp.clickMyAccount();
	 hp.clickRegister();
  }
  
  @Test
  
  public void register()
  {
	  AccountRegistratonPage arr = new AccountRegistratonPage(driver);
	  BaseClass bc = new BaseClass();
	  
	  arr.setFirstName(bc.randonString());
	  arr.setLastName(bc.randonString());
	  arr.setTelephone(bc.randonNumber());
  }
  
  
  
  
  
  
  @AfterClass
  public void down()
  {//driver.close();
	  }
  
  
  
  
}
