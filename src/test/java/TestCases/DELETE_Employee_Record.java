  package TestCases;

	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import Base.TestBase;
	import io.restassured.RestAssured;
	import io.restassured.http.Method;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	
	public class DELETE_Employee_Record extends TestBase {
 		
 	RequestSpecification httpRequest;
 	Response response;
 	@BeforeClass
 	public void deleteRecord() throws InterruptedException {
 		
 	logger.info("Delete Record Test is started");
 	
 	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
 	
 	httpRequest=RestAssured.given();
 	
 	response=httpRequest.request(Method.GET, "/employees");
 	
 	JsonPath jsonPathEvaluator=response.jsonPath();
 	
 	String empID=jsonPathEvaluator.getString("[0].id");
 	
 	response=httpRequest.request(Method.DELETE, "/delete/"+empID);
 	
 	Thread.sleep(5000);
 		
 	}
 	@Test
 	public void checkResponseBody() {
 		
 	String responseBody=response.getBody().asString();
 	
 	System.out.println(responseBody);
 	
 	Assert.assertEquals(responseBody.contains("Error! Not able to delete record"), true);
 		
 	}
 	@Test
 	public void checkStatusCode() {
 		
 	int statusCode=response.getStatusCode();
 	
 	Assert.assertEquals(statusCode, 200);
 		
 	}
 	@Test
 	public void checkStatusLine() {
 		
 	String statusLine=response.getStatusLine();
 	
 	Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
 	
 	}
 	@Test
 	public void checkContentType() {
 		
 	String contentType=response.header("Content-Type");
 	
 	Assert.assertEquals(contentType, "application/json;charset=utf-8");
 			
 	}
 	
 	@Test
 	public void checkServerType() {
 		
 	String serverType=response.header("Server");
 	
 	Assert.assertEquals(serverType, "nginx/1.16.0");
 		
 	}
 	@Test
 	public void checkContentEncoding() {
 		
 	String contentEncoding=response.header("Content-Encoding");
 	
 	Assert.assertEquals(contentEncoding, null);
 		
 	}
 	@AfterClass
 	public void tearDown() {
 		
 	logger.info("Finished Test");	
 		
 	}

}
