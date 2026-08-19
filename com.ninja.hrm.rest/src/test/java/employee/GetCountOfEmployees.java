package employee;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;

import static io.restassured.RestAssured.*;

public class GetCountOfEmployees extends BaseAPI{
	
	@Test
	public void getCountOfEmployees()
	{
		
		given()
		  .spec(specReqObj)
		  .get(EndPointsForEmployee.getCountOfEmployees)
		.then()
		  .assertThat().statusCode(200)
		  .spec(specRespObj)
		  .log().all();
		
	}

}
