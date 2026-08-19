package projectCrud;

import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;

import static io.restassured.RestAssured.*;
import pojoUtility.ProjectPojo;

public class CreatProjectTest extends BaseAPI{
	
	@Test
	public void createProject()
	{
		
		ProjectPojo pObj = new ProjectPojo("Fire"+javalib.generaterandromNumber(300), "Tushar", 0, "Created");
		given()
		 .spec(specReqObj)
		 .body(pObj)
	    .when()
	      .post(IEndPointForProject.Add_Proj)
	    .then()
	      .spec(specRespObj)
	      .assertThat().statusCode(201)
	      .log().all();
		
	}

}
