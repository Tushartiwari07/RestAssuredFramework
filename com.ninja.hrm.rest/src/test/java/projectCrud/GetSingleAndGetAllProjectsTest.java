package projectCrud;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;

import static io.restassured.RestAssured.*;

public class GetSingleAndGetAllProjectsTest extends BaseAPI {
	@Test
	public void getAllProjects()
	{
		given()
		 .spec(specReqObj)
		 .get(IEndPointForProject.Get_All_Proj)
	   .then()
	      .assertThat().statusCode(200)
	      .log().all();	
	}
	
	@Test
	public void getSinleProject()
	{
		given()
		 .spec(specReqObj)
		 .pathParam("projectId", "NH_PROJ_027")
		 .get(IEndPointForProject.Get_Single_Proj)
	   .then()
	      .assertThat().statusCode(200)
	      .spec(specRespObj)
	      .log().all();	
	}
	

}
