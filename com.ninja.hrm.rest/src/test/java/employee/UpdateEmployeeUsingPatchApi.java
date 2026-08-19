package employee;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import pojoUtility.PartialUpdateEmployeePojo;

import static io.restassured.RestAssured.*;
import restUtility.CreateEmployee_Util;
import restUtility.CreateProjectUtil;

public class UpdateEmployeeUsingPatchApi extends BaseAPI {
	
	@Test
	public void updateEmployeeUsingPatchApi() throws FileNotFoundException, ParseException, IOException, InterruptedException
	{
		String empID;

		String projectName = jsonlib.getDataFromJson("projectName");
		String createdBy = jsonlib.getDataFromJson("createdBy");
		int teamSize =Integer.parseInt(jsonlib.getDataFromJson("teamSize"));
		String status = jsonlib.getDataFromJson("status");
		CreateProjectUtil createPJT = new CreateProjectUtil();
		Response projectResponse = createPJT.createProject(projectName+"_"+javalib.generaterandromNumber(99), createdBy, teamSize, status);
		
		String projName = JsonPath.read(projectResponse.asString(), "projectName");
		
		
		String designation=flib.getDataFromPropertiesFile("designation");
		String dob=flib.getDataFromPropertiesFile("dob");
		String email=flib.getDataFromPropertiesFile("email");
		String empName=flib.getDataFromPropertiesFile("empName");
		double experience= Double.parseDouble(flib.getDataFromPropertiesFile("experience"));
		String mobileNo=flib.getDataFromPropertiesFile("mobileNo");
		String role=flib.getDataFromPropertiesFile("role");
		String username=flib.getDataFromPropertiesFile("username");
		CreateEmployee_Util createEmp = new CreateEmployee_Util();
		Response empResponse = createEmp.createEmployee(designation, dob, email, empName, experience, mobileNo, projName, role, username);
		empID = JsonPath.read(empResponse.asString(), "employeeId");
		Thread.sleep(4000);
		PartialUpdateEmployeePojo pacth = new PartialUpdateEmployeePojo("Automation Test Engineer", empName, experience+1);
		
		Response response = given()
		  .spec(specReqObj)
		  .pathParam("id", empID)
		  .body(pacth)
		  .log().all()
	   .when()
	      .patch(EndPointsForEmployee.updateEmployeeUsingPatchApi);
	   response.then()
	      .assertThat().statusCode(200)
	      .spec(specRespObj)
	      .log().all();
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.asPrettyString());
		 
		  
		  
		 
	}

}
