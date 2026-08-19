package projectCrud;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;
import pojoUtility.ProjectPojo;

import static io.restassured.RestAssured.*;

public class UpdateProjectTest extends BaseAPI{
	
	@Test
	public void updateProject()
	{
		ProjectPojo pjt = new ProjectPojo("TEST_007", "Tushar", 0, "Created");
		given()
		 .spec(specReqObj)
		 .pathParam("projectId","NH_PROJ_027" )
		 .body(pjt)
	   .when()
	    .put(IEndPointForProject.Update_Proj)
	   .then()
	   .assertThat().statusCode(200)
	   .spec(specRespObj)
	   .log().all();
	      
	}

}
