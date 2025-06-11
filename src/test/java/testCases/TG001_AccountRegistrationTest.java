package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistratonPage;
import pageObjects.HomePage;
import testBase.BaseClass;
public class TG001_AccountRegistrationTest extends BaseClass {
 /* sepup and teardown
	public WebDriver driver;
	
@BeforeClass
	public void setup()

{
	WebDriverManager.chromedriver().setup();
driver = new ChromeDriver();
driver.manage().deleteAllCookies();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
driver.manage().window().maximize();

}

@AfterClass
public void tearDown()
{
driver.close();	
}
*/
	
	public static String newemail;
	public static String newpwd;


@Test(groups={"Regression","Master"})
public void verify_Account_Registration()
{

	logger.info("****** Starting TG001_AccountRegistrationTest **** ");
	
	
try
{
	
	HomePage hp = new HomePage(driver);
	logger.info("Open Application");
	
	hp.clickMyAccount();
	logger.info("Open Register Page");
	hp.clickRegister();

	AccountRegistratonPage ar = new AccountRegistratonPage(driver);
     
	/* ar.setFirstName("test08");
	ar.setLastName("last008");
	ar.setEmail("RJAM008@gmail.com");
    ar.setTelephone("1234560008");
ar.setPassword("test008");
ar.setConfirmPassword("test008"); */
	logger.info("provide data in Register Page");
	ar.setFirstName(randonString().toUpperCase());
	ar.setLastName(randonString().toUpperCase());
	Thread.sleep(5000);
	String email = randonString()+"@gmail.com";
	ar.setEmail(email);
	System.out.println(email);
	
	newemail=email;
	
	ar.setTelephone(randonNumber());
   
    String pwd = randompass();
    newpwd=pwd;
    System.out.println(pwd);
    ar.setPassword(pwd);
    ar.setConfirmPassword(pwd);
    ar.selectPrivacyPolicy();
    ar.clickContinue();
    
  
    
  String s = "Your Account Has Been Created!";
  String ss = ar.getmsgConfirmation();
System.out.println(ss);
System.out.println(s);
logger.info("Validate registration");
Assert.assertEquals(ss, s);
}
catch (Exception e)
{
logger.error("registration failed");
logger.debug("debug logs... ");
Assert.fail();
}




}
















 /*
	public String randonString()
	{
			String x = RandomStringUtils.randomAlphabetic(5);
			return x;

	}
	
	public String randonNumber()
	{
			String d = RandomStringUtils.randomNumeric(10);
			return d;

	}
	
	public String randompass()
	{
		
		String passtr = RandomStringUtils.randomAlphabetic(3);
		String pasnum = RandomStringUtils.randomNumeric(3);
		
		return (passtr+"@"+pasnum);
		
		
	}
*/
	
}
