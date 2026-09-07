package employee;

	import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import pojoUtility.EmployeePOJO;

import static io.restassured.RestAssured.*;

public class reset extends BaseAPI {
	
	@Test
	public void reset() throws FileNotFoundException, IOException {
		String empId= flib.getDataFromPropertiesFile("empId");
		String oldPassword = flib.getDataFromPropertiesFile("oldPassword");
		String newPassword=  flib.getDataFromPropertiesFile("newPassword");
		
		String designation=flib.getDataFromPropertiesFile("designation");
		String dob=flib.getDataFromPropertiesFile("dob");
		String email=flib.getDataFromPropertiesFile("email");
		String empName=flib.getDataFromPropertiesFile("empName");
		double experience= Double.parseDouble(flib.getDataFromPropertiesFile("experience"));
		String mobileNo=flib.getDataFromPropertiesFile("mobileNo");
		String role=flib.getDataFromPropertiesFile("role");
		String username=flib.getDataFromPropertiesFile("username");
		String projectName ="Fireflink__7911";
		
		EmployeePOJO emp = new EmployeePOJO(designation, dob, email, empName, experience, mobileNo, projectName, role, username);
		
		
		
		// Reset Password
		Response respo= given()
		         .spec(specReqObj)
		         .queryParam("oldPassword", "$2a$10$H1b1bNzy2O1qjI0TLgjme.KWWN44lj.WutsadwEyq0ASezSdb8cMe")
		         .queryParam("newPassword", newPassword)
		         .body(emp)
		         .log().all()
		       .when()
		          .post(EndPointsForEmployee.resetPassword);
        respo.then()
                 .assertThat().statusCode(200)
                 .spec(specRespObj)
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
