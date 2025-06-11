package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TG002_LoginDDT  extends BaseClass {
 // getting dataprovider is in another class then we need to provide dataprovider name an class details
	@Test (dataProvider="LoginData" ,dataProviderClass=DataProviders.class ,groups="datadriven")
public void verify_loginDDT(String email, String pwd, String exp)
	{
	
	// home Page
	
	try
	{
	HomePage hp = new HomePage(driver);
	
	hp.clickMyAccount();
	
LoginPage lp = new LoginPage(driver);

	lp.clickLogin();
	lp.enterUsermail(email);
	lp.enterPassword(pwd);;
	lp.clickLoginbtn();
	Thread.sleep(5000);
	boolean data=false;
	MyAccount mac = new MyAccount(driver);
	 data = mac.myAccountText();
	
	
	
	System.out.println(data);
  boolean res = exp.equals("Valid");
  
  System.out.println("reslu " + res);
	
  if(exp.equalsIgnoreCase("Valid"))
  {
	  if(data==true)
	
		  {mac.clickLogout();
		  logger.info("Login is success with valid data");
	  Assert.assertTrue(true);
  }
  else
  {
	  logger.error("Login is NOT success with valid data");
	  Assert.assertTrue(false);
	
  }
  }
  
  if(exp.equalsIgnoreCase("Invalid"))
  {
	  if(data==true)
	
		  {mac.clickLogout();
		  logger.error("Login is success with INVALID data");
	  Assert.assertTrue(false);
	 
	  
  }
  else
  {
	  Assert.assertTrue(true);
	  logger.info("Login is NOT success with Invalid data");
  }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /*
  if((data==true) && (res==true))
			{
		
		System.out.println("Login is success with valid data");
	//logger.info("Login is success with valid data");
		Assert.assertTrue(true);	
	mac.clickLogout();
	
	}
	 if ((data==false) && (res==true))
	{
		{System.out.println("Login is NOT success with valid data");}
		//logger.error("Login is NOT success with valid data");
		Assert.assertTrue(false);
	}
	 if ((data==false) && (res==false))
	{
		{System.out.println("Login is NOT success with INVALID data");}
		//logger.info("Login is NOT success with INVALID data");
		Assert.assertTrue(true);
	}
	 if ((data==true) && (res==false))
	{
		System.out.println("Login is  SUCCESS with INVALID data");}
	//logger.error("Login is SUCCESS with INVALID data");
	 Assert.assertTrue(false);
		mac.clickLogout();
	}
	*/
	}
	catch (Exception e)
	{System.out.println(e.getMessage());}
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
