package restUtility;

import static io.restassured.RestAssured.given;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;

public class DeleteProjectUtil {
	public void deletePJT(String projectId)
	{
		

		given()
		 .spec(BaseAPI.specReqObj)
		 .pathParam("projectId", projectId)
		 .log().all()
		.when()
		 .delete(IEndPointForProject.Delete_Proj)
		.then()
		  .assertThat().statusCode(204)
		  .spec(BaseAPI.specRespObj)
		  .log().all();

	}
	

}
