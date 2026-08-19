package projectCrud;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;

import static io.restassured.RestAssured.*;

public class GetProjectCountTest extends BaseAPI {
	
	@Test
	public void getProjectCount() {
		given()
		 .spec(specReqObj)
		 .get(IEndPointForProject.GetProjectCount)
		.then()
		 .assertThat().statusCode(200)
		 .spec(specRespObj)
		 .log().all();
		
	}

}
