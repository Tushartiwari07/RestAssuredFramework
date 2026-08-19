package employee;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import pojoUtility.EmployeePOJO;
import pojoUtility.ReSetPasswordPOJO;
import restUtility.CreateProjectUtil;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ResetPasswordDEMO extends BaseAPI {
	
	@Test
	public void restPassword() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		
		// Create project
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
		String empName=flib.getDataFromPropertiesFile("empName")+"_"+javalib.generaterandromNumber(99);
		double experience= Double.parseDouble(flib.getDataFromPropertiesFile("experience"));
		String mobileNo=flib.getDataFromPropertiesFile("mobileNo");
		String role=flib.getDataFromPropertiesFile("role");
		String username=flib.getDataFromPropertiesFile("username")+"_"+javalib.generaterandromNumber(99);
		
		EmployeePOJO empPojo = new EmployeePOJO(designation, dob, email, empName, experience, mobileNo, projName, role, username);
		// Create Employe
		Response respo= given()
				         .spec(specReqObj)
				         .body(empPojo)
				         .log().all()
				       .when()
				          .post(EndPointsForEmployee.createEmployee);
		         respo.then()
//		                  .assertThat().statusCode(201)
//		                  .spec(specRespObj)
		                  .log().all();
			     System.out.println("Status Code: " + respo.getStatusCode());

			     System.out.println("Response Body BeforePassword change: " + respo.asPrettyString());
			     //Capture employee ID
			 		String empId =JsonPath.read(respo.asString(), "employeeId");

			    // to get the employee details
			 		respo =given()
			     .spec(specReqObj)
			     .pathParam("empId", empId)
			   .when()
			     .get(EndPointsForEmployee.getEmployeeByEmpID);
			 		respo.then()
			     .assertThat().statusCode(200)
			     .spec(specRespObj)
			     .log().all();
			 	System.out.println("Response Body of GET Employee details BeforePassword change: " + respo.asPrettyString());
			 	
			 // capture Old password 
		 		String empPassword =JsonPath.read(respo.asString(), "password");
		 		System.out.println("Old Password : "+ empPassword);
				String newPassword=flib.getDataFromPropertiesFile("newPassword");
				
			ReSetPasswordPOJO passRest= new ReSetPasswordPOJO(empId, designation, dob, email, empName, experience, mobileNo, projName, role, username);
				

				// Reset Password
		 		 respo= given()
				         .spec(specReqObj)
				         .queryParam("oldPassword", empPassword)
				         .queryParam("newPassword", newPassword)
				         .body(passRest)
				         .log().all()
				       .when()
				          .post(EndPointsForEmployee.resetPassword);
		         respo.then()
//		                  .assertThat().statusCode(200)
//		                  .spec(specRespObj)
		                  .log().all();
			     System.out.println("Response Body: " + respo.asPrettyString());

			    
			     // to get the employee details
			 		respo =given()
			     .spec(specReqObj)
			     .pathParam("empId", empId)
			   .when()
			     .get(EndPointsForEmployee.getEmployeeByEmpID);
			 		respo.then()
			     .assertThat().statusCode(200)
			     .spec(specRespObj)
			     .log().all();
			 	System.out.println("Response Body of GET Employee details AfterPassword change: " + respo.asPrettyString());

				
		
	}

}
