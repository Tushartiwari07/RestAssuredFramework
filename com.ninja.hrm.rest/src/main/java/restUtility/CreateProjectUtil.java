package restUtility;

import static io.restassured.RestAssured.given;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.IEndPointForProject;
import io.restassured.response.Response;
import pojoUtility.ProjectPojo;
import utility.JavaUtility;

public class CreateProjectUtil {
	public Response createProject(String projectName,String createdBy, int teamSize,String status)
	{
		JavaUtility jlib = new JavaUtility();
		jlib.generaterandromNumber(99);
		ProjectPojo pObj = new ProjectPojo(projectName+jlib.generaterandromNumber(99), createdBy, teamSize, status);
		Response respo = given()
		 .spec(BaseAPI.specReqObj)
		 .body(pObj)
		 .log().all()
	    .when()
	      .post(IEndPointForProject.Add_Proj);
	    respo.then()
	      .spec(BaseAPI.specRespObj)
	      .assertThat().statusCode(201)
	      .log().all();
	    return respo;


	}

}
