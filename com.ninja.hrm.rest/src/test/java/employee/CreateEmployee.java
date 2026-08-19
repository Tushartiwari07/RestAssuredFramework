package employee;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import pojoUtility.EmployeePOJO;
import restUtility.DeleteEmployeetUtil;
import restUtility.DeleteProjectUtil;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class CreateEmployee extends BaseAPI {
	public String empID;
	public String projID;
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
		 String projName =   JsonPath.read(projResponse.asString(), "projectName");
		  projID=JsonPath.read(projResponse.asString(),"projectId");
		 
		
		
		String designation=flib.getDataFromPropertiesFile("designation");
		String dob=flib.getDataFromPropertiesFile("dob");
		String email=flib.getDataFromPropertiesFile("email");
		String empName=flib.getDataFromPropertiesFile("empName");
		double experience= Double.parseDouble(flib.getDataFromPropertiesFile("experience"));
		String mobileNo=flib.getDataFromPropertiesFile("mobileNo");
		String role=flib.getDataFromPropertiesFile("role");
		String username=flib.getDataFromPropertiesFile("username");
		
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
