package employee;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import pojoUtility.EmployeePOJO;
import restUtility.CreateEmployee_Util;
import restUtility.CreateProjectUtil;

public class UpdateProject extends BaseAPI {
	String empId;
	
    @Test
    public void updateProject() throws FileNotFoundException, ParseException, IOException, InterruptedException
    {
    	
    	//First project
    	String projectName = jsonlib.getDataFromJson("projectName");
		String createdBy = jsonlib.getDataFromJson("createdBy");
		int teamSize =Integer.parseInt(jsonlib.getDataFromJson("teamSize"));
		String status = jsonlib.getDataFromJson("status");
		CreateProjectUtil createPJT = new CreateProjectUtil();
		Response projectResponse = createPJT.createProject(projectName+"_"+javalib.generaterandromNumber(99), createdBy, teamSize, status);
		
		String projName =	JsonPath.read(projectResponse.asString(), "projectName");
    	
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
		 empId =JsonPath.read(empResponse.asString(), "employeeId");
		 Thread.sleep(4000);
		 
		 //Second Project for updateEmployee Project
		 
			Response project2Response = createPJT.createProject(projectName+"_"+javalib.generaterandromNumber(99), createdBy, teamSize, status);
			
			String projName2 =	JsonPath.read(project2Response.asString(), "projectName");
			
			EmployeePOJO emp = new EmployeePOJO(designation, dob, email, empName, experience, mobileNo, projName2, role, username);
			
Response respo=given()
			    .spec(specReqObj)
			    .pathParam("empId", empId)
			    .body(emp)
			  .when()
			    .put(EndPointsForEmployee.updateProject);
	     respo.then()
		        .assertThat().statusCode(200)
			    .spec(specRespObj)
			    .log().all();
	     System.out.println("Response Body: " + respo.asPrettyString());
	     
	    String empProjectName = JsonPath.read(respo.asString(), "project");
	    
	    Assert.assertEquals(projName2, empProjectName, "Project Name not Updated");
			
	    	
    }

}
