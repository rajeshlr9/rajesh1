package utilities;

import java.awt.Desktop;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener  {
	
public ExtentSparkReporter sparkReporter;  // UI of the report
public ExtentReports extent; // populate common info on the report , like date , resource name, browser, OS..
public ExtentTest test; // creating test case entries in the report and update status of the test cases

String repName;



public void onStart(ITestContext context) {
	
	//Date now = new Date();
	//long time = now.getTime();
	
	//sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+time+".html");  // report name and path
	
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.FF.mm.ss").format(new Date());
	repName = "Test-Report-" + timeStamp + ".html";
	
	sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
	
	sparkReporter.config().setDocumentTitle("OpenKart Automation Report"); // title of the report
	sparkReporter.config().setReportName("OpenKart Functional Testing"); // name of the report
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	
	extent.setSystemInfo("Application", "OpenKart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");

String os = context.getCurrentXmlTest().getParameter("os");
extent.setSystemInfo("Operating System" ,  os);


String browser = context.getCurrentXmlTest().getParameter("browser");
extent.setSystemInfo("Operating System" ,  browser);

java.util.List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();

if(!((java.util.List<String>) includeGroups).isEmpty())
{
	
extent.setSystemInfo("Groups", includeGroups.toString());
}


}

	public void onTestSuccess(ITestResult result) {

	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups())	;
	test.log(Status.PASS, result.getName()+ "got successfully executed");
	}

	public void onTestFailure(ITestResult result) {

		
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		
		
		
		test.log(Status.FAIL,result.getName() + " Test case FAILED ");
		test.log(Status.INFO,result.getThrowable().getMessage());
	try
		{
		
	
		

		String imgPath=new BaseClass().captureScreen( result.getName());
		
		
		test.addScreenCaptureFromPath(imgPath);
	
		}
	catch(IOException e1)
	{e1.getMessage();
	}}



		
	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED is:"+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onFinish(ITestContext context) {

extent.flush(); //  this will write all the info on the report. this onFinish is mandatory
String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+ repName;
System.out.println(pathofExtentReport);

File extentReport = new File(pathofExtentReport);

try {
	
	Desktop.getDesktop().browse(extentReport.toURI());
	
	
}catch(IOException e)


{e.printStackTrace();}

/*
try{
	URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports"+repName);

	ImageHtmlEmail email = new ImageHtmlEmail();
	
	email.setDataSourceResolver(new DataSourceUrlResolver(url));
	
	
	
}
*/


	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	
	// display test case ID in the report
	
	
}

