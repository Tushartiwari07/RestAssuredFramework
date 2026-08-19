package projectCrudWithReqChaining;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;
import io.restassured.response.Response;
import pojoUtility.ProjectPojo;

import static io.restassured.RestAssured.*;

public class CRUD_PJT_Test extends BaseAPI {
	String pjtId;
	
	@Test(priority = 1)
	public void createProject()
	{
		ProjectPojo pjt = new ProjectPojo("FIREFLINK_"+javalib.generaterandromNumber(99), "Tushar", 0, "Created");
		Response respo = given()
		                   .spec(specReqObj)
		                   .body(pjt)
		                   .log().all()
	                     .when()
	                       .post(IEndPointForProject.Add_Proj);
		                    respo.then()
	                       .assertThat().statusCode(201)
	                       .spec(specRespObj)
	                       .log().all();
		        pjtId=JsonPath.read(respo.asString(),"projectId");
	}
	
	@Test(priority = 2, dependsOnMethods = "createProject")
	public void getProjectDetails()
	{
		System.out.println(pjtId);
	 given()
		 .spec(specReqObj)
		 .queryParam("projectId", pjtId)
		 .log().all()
	 .when()
		.post(IEndPointForProject.Get_Single_Proj_Via_FormParam)
	 .then()
	    .assertThat().statusCode(200)
	    .spec(specRespObj)
	    .log().all();	
	}
	
	@Test(priority = 3)
	public void updateProject()
	{
		ProjectPojo pjt = new ProjectPojo("FIREFLINK_"+javalib.generaterandromNumber(99), "Tiwari", 0, "On Going");
		given()
		  .spec(specReqObj)
		  .pathParam("projectId", pjtId)
		  .body(pjt)
		  .log().all()
		.when()
		  .put(IEndPointForProject.Update_Proj)
		.then()
		.assertThat().statusCode(200)
		.spec(specRespObj)
		.log().all();
	}
	
	@Test(priority = 4)
	public void deleteProject()
	{
		given()
		 .spec(specReqObj)
		 .pathParam("projectId", pjtId)
		 .log().all()
	   .when()
		 .delete(IEndPointForProject.Delete_Proj)
	  .then()
	    .assertThat().statusCode(204)
	    .spec(specRespObj)
	    .log().all();
		
	}

}
