package employee;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;

import static io.restassured.RestAssured.*;

public class GetEmployeeById extends BaseAPI {
	
	
	@Test
	public void getEmpById() throws FileNotFoundException, IOException
	{
		String empId = flib.getDataFromPropertiesFile("empId");
		
		   given()
		     .spec(specReqObj)
		     .pathParam("empId", empId)
		   .when()
		     .get(EndPointsForEmployee.getEmployeeByEmpID)
		   .then()
		     .assertThat().statusCode(200)
		     .spec(specRespObj)
		     .log().all();
		
	}
	
	

}
