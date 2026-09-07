package employee;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import pojoUtility.EmployeePOJO;
import restUtility.DeleteEmployeetUtil;
import restUtility.DeleteProjectUtil;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.HashMap;

public class CreateEmployee extends BaseAPI {
	public String empID;
	public String projID;
	public	String designation;
	public String dob;
	public String email;
	public String empName;
	public double experience;
	public String mobileNo;
	public String role;
	public String username;
	public String projName;
	@Test(priority = 1)
	public void createEmployee() throws FileNotFoundException, ParseException, IOException, InterruptedException
	{
		String projectName = jsonlib.getDataFromJson("projectName");
		String createdBy = jsonlib.getDataFromJson("createdBy");
		int teamSize =Integer.parseInt(jsonlib.getDataFromJson("teamSize"));
		String status = jsonlib.getDataFromJson("status");
		//Project Creation
//		String projName = createProj.createProject(projectName, createdBy, teamSize, status);
		  Response projResponse = createProj.createProject(projectName, createdBy, teamSize, status);
		  projName =   JsonPath.read(projResponse.asString(), "projectName");
		  projID=JsonPath.read(projResponse.asString(),"projectId");
		 
		
		
		designation=flib.getDataFromPropertiesFile("designation");
		dob=flib.getDataFromPropertiesFile("dob");
		email=flib.getDataFromPropertiesFile("email");
		empName=flib.getDataFromPropertiesFile("empName");
		experience= Double.parseDouble(flib.getDataFromPropertiesFile("experience"));
		mobileNo=flib.getDataFromPropertiesFile("mobileNo");
		role=flib.getDataFromPropertiesFile("role");
	    username=flib.getDataFromPropertiesFile("username");
		
		EmployeePOJO emp = new EmployeePOJO(designation, dob, email, empName+"_"+javalib.generaterandromNumber(99), experience, mobileNo, projName, role, username+"_"+javalib.generaterandromNumber(99));
		Response respo = given()
		 .spec(specReqObj)
		 .body(emp)
		 .log().all()
		.when()
		 .post(EndPointsForEmployee.createEmployee);
		respo.then()
		 .assertThat().statusCode(201)
	     .spec(specRespObj)
		 .log().all();
		empID=JsonPath.read(respo.asString(),"employeeId");
		Thread.sleep(10000);
	}
	
	@Test
	public void verifyEmployeeIsCreatedViaUi()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.29.4:8091/welcome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Login to application
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("inputPassword")).sendKeys("");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		
		driver.findElement(By.linkText("Employees")).click();
		
		Assert.assertEquals("http://49.249.29.4:8091/dashboard/users", driver.getCurrentUrl());
		
		assertEquals(empName, driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td[3])[1]")).getText());
		assertEquals(username, driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td[4])[1]")).getText());
		assertEquals(email, driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td[5])[1]")).getText());
		assertEquals(mobileNo, driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td[6])[1]")).getText());
		assertEquals(designation, driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td[7])[1]")).getText());
		assertEquals(experience, driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td[8])[1]")).getText());
		assertEquals(projName, driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td[9])[1]")).getText());
		
		for(int i=2;i<=9;i++)
		{
			System.out.println(driver.findElement(By.xpath("(//table[@class='table table-striped table-hover']/tbody/tr/td["+i+"])[1]")).getText());
		}		 
}
//	@Test(priority = 2)
//	public void deleteEmployee() throws InterruptedException
//	{
//		DeleteEmployeetUtil deleteEMP= new DeleteEmployeetUtil();
//		deleteEMP.deleteEmployee(empID);
//		Thread.sleep(10000);
//	}
//	
//	@Test(priority = 3)
//	public void deleteProject()
//	{
//		DeleteProjectUtil deletePJT = new DeleteProjectUtil();
//		deletePJT.deletePJT(projID);
//	}


}
