package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	
	}
  
	@FindBy(xpath="//a[text()='Login']")
	WebElement loginlink;
	
	
@FindBy(id="input-email")
WebElement useremail;

@FindBy(name="password")
WebElement password;

@FindBy(xpath="//input[@value='Login']")
WebElement loginbutton;
	
	
public void enterUsermail(String username)
{
	
	useremail.sendKeys(username);
}

public void enterPassword(String pwd)
{
	
	password.sendKeys(pwd);
}	
	
public void clickLoginbtn()
{
	
	loginbutton.click();

}	

public void clickLogin()
{
	loginlink.click();	
}




	
}
