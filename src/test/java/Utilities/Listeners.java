  package Utilities;

	import org.testng.ITestContext;
	import org.testng.ITestResult;
	import org.testng.TestListenerAdapter;
	import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;
	
	public class Listeners extends TestListenerAdapter {
		
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReport;
	public ExtentTest test;
  		
  	public void onStart(ITestContext testContext) {
  		
  	htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
  	
  	htmlReporter.config().setDocumentTitle("Rest API Report");
  	
  	htmlReporter.config().setReportName("API Testing");
  	
  	htmlReporter.config().setTheme(Theme.DARK);
  	
  	
  	extentReport=new ExtentReports();
  	
  	extentReport.attachReporter(htmlReporter);
  	
  	extentReport.setSystemInfo("Project Name", "API Framework");
  	
  	extentReport.setSystemInfo("Host name", "localhost");
  	
  	extentReport.setSystemInfo("Enviroment", "QA");
  	
  	extentReport.setSystemInfo("user", "Ritesh");
  		
  	}
  	
  	public void onTestSuccess(ITestResult result) {
  		
  	test=extentReport.createTest(result.getName());
  	
  	test.log(Status.PASS, "Test Case Passed is "+result.getName());
  		
  	}
  
  	public void onTestFailure(ITestResult result) {
  		
  	test=extentReport.createTest(result.getName());
  	
  	test.log(Status.FAIL, "Test case failed is "+result.getName());
  	
  	test.log(Status.FAIL, "Test case failed is "+result.getThrowable());
  		
  	}
  	
  	public void onTestSkipped(ITestResult result) {
  		
  	test=extentReport.createTest(result.getName());	
  	
  	test.log(Status.SKIP, "Test case Skipped is "+result.getName());
  		
  	}
  	
  	public void onFinish(ITestContext testContext) {
  		
  	extentReport.flush();	
  		
  	}

}
