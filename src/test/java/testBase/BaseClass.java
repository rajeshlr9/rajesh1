package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager; // log4j
import org.apache.logging.log4j.Logger; // log4j


public class BaseClass {
  
	public static WebDriver driver;
	public Properties p;
	

	
public Logger logger;	// Log4j
	
	@BeforeClass(groups={"Sanity","Regression","Master","datadriven"})
	@Parameters({"os","browser"})
		public void setup(String os , String br) throws IOException

	{
		
		
		
		WebDriverManager.chromedriver().setup();

// Loading config.properties file
		
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		String url = p.getProperty("appURL");
		
		
		
		
		logger = LogManager.getLogger(this.getClass());
// to run in the grid-remo
		
if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		
{

	DesiredCapabilities cap = new DesiredCapabilities();
// os
	if(os.equalsIgnoreCase("windows"))
	{
		cap.setPlatform(Platform.WINDOWS);	
	}

	// browser
	
	switch(br.toLowerCase())
	{

	case "chrome" : cap.setBrowserName("chrome"); break;
	case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
	case "firefox" : cap.setBrowserName("chrome"); break;

	}	
	
	 driver = new RemoteWebDriver(new URL("http://192.168.29.131:4444/wd/hub"),cap);
	
	
}	



if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		
{		

	
switch(br.toLowerCase())
{

case "chrome" : driver = new ChromeDriver(); break;
case "edge" : driver = new EdgeDriver(); break;
case "firefox" : driver = new ChromeDriver(); break;

}
}
    
	ChromeOptions options = new ChromeOptions();
options.addArguments("--remote-allow-origins=*");
ChromeDriver driver = new ChromeDriver(options);
		
		
		
		
		
		driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
	
	driver.get(url);
	
	driver.manage().window().maximize();

	}

	@AfterClass(groups={"Sanity","Regression","Master","datadriven"})
	public void tearDown()
	{
	driver.close();	
	}
	
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
	
	
	public String captureScreen(String tname) throws IOException {
	
		
	
		 
	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot takesScreeshot = (TakesScreenshot) driver;
	File sourceFile = takesScreeshot.getScreenshotAs(OutputType.FILE);
	
	String taggetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+ "_" + timeStamp +".png";
	File targetFile = new File(taggetFilePath);
	
	sourceFile.renameTo(targetFile);
	
	return taggetFilePath;
	}
	

	
	
	
	
	
}
