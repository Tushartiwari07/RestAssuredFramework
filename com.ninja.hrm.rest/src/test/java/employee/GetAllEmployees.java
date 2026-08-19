package employee;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;

import static io.restassured.RestAssured.*;
public class GetAllEmployees extends BaseAPI {
	
	@Test
	public void getAllEmployees()
	{
		given()
		  .spec(specReqObj)
		.when()
		   .get(EndPointsForEmployee.getAllEmployees)
		.then()
		   .assertThat().statusCode(200)
		   .spec(specRespObj)
		   .log().all();
	}

}
