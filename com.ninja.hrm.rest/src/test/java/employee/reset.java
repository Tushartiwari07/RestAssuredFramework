package employee;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class reset extends BaseAPI {
	
	@Test
	public void reset() throws FileNotFoundException, IOException {
		String empId= flib.getDataFromPropertiesFile("empId");
//		String oldPassword = flib.getDataFromPropertiesFile("oldPassword");
//		String newPassword=  flib.getDataFromPropertiesFile("newPassword");
		
//		// Reset Password
//		Response respo= given()
//		         .spec(specReqObj)
//		         .queryParam("oldPassword", oldPassword)
//		         .queryParam("newPassword", newPassword)
//		         .body(passRest)
//		         .log().all()
//		       .when()
//		          .post(EndPointsForEmployee.resetPassword);
//        respo.then()
////                 .assertThat().statusCode(200)
////                 .spec(specRespObj)
//                 .log().all();
//	     System.out.println("Response Body: " + respo.asPrettyString());
//
//	    
	     // to get the employee details
		Response respo =given()
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
