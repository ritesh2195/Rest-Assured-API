 package TestCases;

  import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
	
  public class GET_All_EmployeesData extends TestBase {

  @BeforeClass
  public void getAllEmployees() throws InterruptedException {
	
   logger.info("......Test started....."); 
   
   RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
   
   httpRequest=RestAssured.given();
   
   response=httpRequest.request(Method.GET, "/employees");
   
   Thread.sleep(3000);
	   
  }
  @Test
  public void checkResponseBody() {
	  
  logger.info("verifying response body");  
  
  String responseBody=response.getBody().asString();
  
  logger.info("Response Body==>"+responseBody);
  
  Assert.assertTrue(responseBody!=null);
	    
  }
  @Test
  public void checkStatusCode() {
	  
  int statusCode=response.getStatusCode();
  
  logger.info("Response code is "+statusCode);
  
  Assert.assertEquals(statusCode, 200);
    
  }
  @Test
  public void checkResponseTime() {
	  
  logger.info("checking response time");
  
  long responseTime=response.getTime();
  
  logger.info("Response time is "+responseTime);
  
  if(responseTime>2000)
	  
	  logger.warn("Response time is greater than 2000");
	  
  Assert.assertTrue(responseTime<2000);
	  
  }
  @Test 
  public void checkStatusLine() {
	  
  logger.info("checking status line");	
  
  String statusLine=response.getStatusLine();
  
  logger.info("Status line is "+statusLine);
  
  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	  
  }
  @Test
  public void checkContentType() {
	  
  logger.info("checking Content type");  
  
  String contentType=response.getContentType();
  
  logger.info("Content Type is"+contentType);
  
  Assert.assertEquals(contentType, "application/json;charset=utf-8");
	  
  }
  @Test
  public void checkContentEncoding() {
	  
  logger.info("checking content encoding");	  
  
  String contentEncoding=response.getHeader("Content-Encoding");
  
  logger.info("Content Encoding is "+contentEncoding);
  
  Assert.assertEquals(contentEncoding, "gzip");
	  
  }
  @Test
  public void checkServerType() {
	  
  logger.info("checking server type");
  
  String serverType=response.getHeader("Server");
  
  logger.info("Server type is "+serverType);
  
  Assert.assertEquals(serverType, "nginx/1.16.0");
	  
  }
  @Test
  public void  checkContentLength() {
	  
  logger.info("checking content length");
  
  String contentLength=response.getHeader("Content-Length");
  
  logger.info("Content Length is "+contentLength);
  
  if(Integer.parseInt(contentLength)<100)
	  
	 logger.warn("content length is less than 100");
  
  Assert.assertTrue(Integer.parseInt(contentLength)>100); 
  
  }
  @Test
  public void checkCookies() {
	  
  logger.info("checking cookies"); 
  
  String cookies=response.getCookie("PHPSESSID");
	  
	  
  }
  @AfterClass
  public void tearDown() {
	  
  logger.info("test is completed");  
	  
  }
}
