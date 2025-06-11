package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class MyAccount extends HomePage {

	public MyAccount(WebDriver driver) {
		super(driver);
		
	}
  
@FindBy(xpath="//div[@id='content']/h2[1]")
WebElement Myaccount;

@FindBy(xpath="((//a[text()='Logout'])[2])")
WebElement logout;



public  boolean myAccountText()

{
	try{
	return Myaccount.isDisplayed();
	}
	catch(Exception e)
	{return false;}
}

public void clickLogout()
{
	 logout.click();
	
}

public  String myAccountTexts()

{
	return Myaccount.getText();
}




}
