package employee;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
public class GetEmployeeExperiences extends BaseAPI {
	
	@Test
	public void getEmployeeExperiences()
	{
		given()
		 .spec(specReqObj)
		.when()
		   .get(EndPointsForEmployee.ApiTogetEmployeeExperiences)
		.then()
	       .assertThat().statusCode(200)
		   .spec(specRespObj)
	       .log().all();
		   
		 
	}

}
