package projectCrud;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;

import static io.restassured.RestAssured.*;

public class DeleteProjectTest extends BaseAPI {
	
	@Test
	public void deleteProject()
	{
		given()
		 .spec(specReqObj)
		 .pathParam("projectId", "NH_PROJ_027")
		 .delete(IEndPointForProject.Delete_Proj)
		.then()
		  .assertThat().statusCode(204)
		  .spec(specRespObj)
		  .log().all();
	}

}
