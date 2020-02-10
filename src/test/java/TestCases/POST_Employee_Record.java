  package TestCases;

	import org.json.simple.JSONObject;
	import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import Base.TestBase;
	import Utilities.RestUtils;
	import io.restassured.RestAssured;
	import io.restassured.http.Method;
	import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
	
	public class POST_Employee_Record extends TestBase {
		
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	
	String empSalary=RestUtils.empSal();
	
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	public void createEmployee() throws InterruptedException {
	
	logger.info("post employee test started");
		
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	
	httpRequest=RestAssured.given();
	
	JSONObject obj=new JSONObject();
	
	obj.put("name", empName);
	obj.put("salary", empSalary);
	obj.put("age", empAge);
	
	httpRequest.header("Content-Type", "application/json");
	
	httpRequest.body(obj.toJSONString());
	
	response=httpRequest.request(Method.POST, "/create");
	
	Thread.sleep(3000);
		
	}
	
	@Test
	public void checkResponseBody() {
		
	String responseBody=response.getBody().asString();
	
	Assert.assertEquals(responseBody.contains(empName), true);
	
	Assert.assertEquals(responseBody.contains(empSalary), true);
	
	Assert.assertEquals(responseBody.contains(empAge), true);
		
	}
	@Test
	public void checkStatusCode() {
		
	int statusCode=	response.getStatusCode();
	
	System.out.println("Status code is "+statusCode);
	
	Assert.assertEquals(statusCode, 200);
		
	}
	
	 @Test 
	 public void checkStatusLine() {
		  
	 String statusLine=response.getStatusLine();
	 
	 Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
	}
	 
	 @Test
	 public void checkContentType() {
	  	  
	 String contentType=response.getContentType();
	    
	 Assert.assertEquals(contentType, "application/json;charset=utf-8");
	  	  
	}
	 
	 @Test
	 public void checkServerType() {
	  	  
	 String serverType=response.getHeader("Server");
	    
	 Assert.assertEquals(serverType, "nginx/1.16.0");
	 
   }
	 
	 @Test
	 public void checkContentEncoding() {
		 
	 String contentEncoding=response.getHeader("Content-Encoding");
	 	 
	 Assert.assertEquals(contentEncoding, null);
		 
	 }
	 
	 @AfterClass
	 public void tearDown() {
	  	  
	 logger.info("test is completed");  
	  	  
  }
	 
	}