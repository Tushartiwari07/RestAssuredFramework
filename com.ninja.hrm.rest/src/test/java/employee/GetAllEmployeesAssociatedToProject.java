package employee;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import restUtility.CreateProjectUtil;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GetAllEmployeesAssociatedToProject extends BaseAPI {
	
	@Test
	public void getAllEmployeesAssociatedToProject() throws FileNotFoundException, ParseException, IOException
	{
		String projectName = jsonlib.getDataFromJson("projectName");
		String createdBy = jsonlib.getDataFromJson("createdBy");
		int teamSize =Integer.parseInt(jsonlib.getDataFromJson("teamSize"));
		String status = jsonlib.getDataFromJson("status");
		CreateProjectUtil createPJT= new CreateProjectUtil();
		Response projResponse = createPJT.createProject(projectName, createdBy, teamSize, status);
		 String projectId=   JsonPath.read(projResponse.asString(), "projectId");

		given()
		   .spec(specReqObj)
		   .queryParam("projectId", projectId)
		   .log().all()
		   .get(EndPointsForEmployee.getAllEmployeesAssociatedToProject)
		.then()
		   .assertThat().statusCode(200)
		   .log().all();
		
	}
	
	
	

}
