package employee;
import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import restUtility.CreateEmployee_Util;
import restUtility.CreateProjectUtil;

public class GetEmpByUsername extends BaseAPI {
	
	@Test
	public void getEmpByUsername() throws FileNotFoundException, ParseException, IOException
	{
		String projectName = jsonlib.getDataFromJson("projectName");
		String createdBy = jsonlib.getDataFromJson("createdBy");
		int teamSize =Integer.parseInt(jsonlib.getDataFromJson("teamSize"));
		String status = jsonlib.getDataFromJson("status");
		CreateProjectUtil createPJT = new CreateProjectUtil();
		Response projectResponse = createPJT.createProject(projectName+"_"+javalib.generaterandromNumber(99), createdBy, teamSize, status);
		
		String projName =  JsonPath.read(projectResponse.asString(), "projectName");

		String designation=flib.getDataFromPropertiesFile("designation");
		String dob=flib.getDataFromPropertiesFile("dob");
		String email=flib.getDataFromPropertiesFile("email");
		String empName=flib.getDataFromPropertiesFile("empName");
		double experience= Double.parseDouble(flib.getDataFromPropertiesFile("experience"));
		String mobileNo=flib.getDataFromPropertiesFile("mobileNo");
		String role=flib.getDataFromPropertiesFile("role");
		String username=flib.getDataFromPropertiesFile("username");
		CreateEmployee_Util createEmp = new CreateEmployee_Util();
		Response empResponse = createEmp.createEmployee(designation, dob, email, empName, experience, mobileNo, projName, role, username);
		String userName =
		        JsonPath.read(empResponse.asString(), "username");
		given()
		  .spec(specReqObj)
		  .pathParam("userName", userName)
	   .when()
	      .log().all()
	      .get(EndPointsForEmployee.getEmployeeByUserName)
	   .then()
	       .statusCode(200)
	       .spec(specRespObj)
	       .log().all();
	}
	
	

}
