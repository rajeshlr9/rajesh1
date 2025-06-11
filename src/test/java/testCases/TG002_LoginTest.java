package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TG002_LoginTest extends BaseClass   {
  
// adding some comments
	@Test(groups={"Sanity","Master"})
	public void loginTest()
	{
		try
		{
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		
		
		
		logger.info("Login Test Execution");
		
		hp.clickMyAccount();
		
		lp.clickLogin();
		
		String id = TG001_AccountRegistrationTest.newemail;
		System.out.println(id);
		if ((id==null) || (id==""))
		{
			id="no login data";
			
		}
		
		if (id.contains("@gmail.com"))	
		
	{		// get data from registration test.
				
				lp.enterUsermail(TG001_AccountRegistrationTest.newemail);
				lp.enterPassword(TG001_AccountRegistrationTest.newpwd);
				lp.clickLoginbtn();
		}
	else
	{
// get data from properties file
		lp.enterUsermail(p.getProperty("userId"));
		lp.enterPassword(p.getProperty("Password"));
		lp.clickLoginbtn();
		
		Thread.sleep(5000);
	}

	
		
		MyAccount ac = new MyAccount(driver);
	    String s = ac.myAccountTexts();
	    System.out.println(s);
	    Thread.sleep(5000);
	    ac.clickLogout();
	    
	    if (s.equals("My Account"))
	    {
	    	Assert.assertEquals(s, "My Account");
	    	logger.info("Login is successfull");
	    }
		}
		catch(Exception e)
		{
	    	//System.out.println(e.getMessage());
	    	logger.error("Login is not successfull");
	    	Assert.fail();
	    	
	    	
	    	
		}
		
		
		
	    }

	
	
	
	
	
	
	
}
