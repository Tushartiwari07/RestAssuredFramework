package restUtility;

import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import com.ninja.hrm.API.Base.BaseAPI;
import endPoints.EndPointsForEmployee;
import io.restassured.response.Response;
import pojoUtility.EmployeePOJO;
import utility.JavaUtility;
import utility.JsonUtility;

public class CreateEmployee_Util {
	
	@Test
	public Response createEmployee(String designation,String dob, String email, String empName, double experience, String mobileNo, String projectName,String role, String username) throws FileNotFoundException, ParseException, IOException
	{
;
		JavaUtility javalib = new JavaUtility();
//	
		
		EmployeePOJO emp = new EmployeePOJO(designation, dob, email, empName+"_"+javalib.generaterandromNumber(99), experience, mobileNo, projectName, role, username+"_"+javalib.generaterandromNumber(99));
		Response respo = given()
		 .spec(BaseAPI.specReqObj)
		 .body(emp)
		 .log().all()
		.when()
		 .post(EndPointsForEmployee.createEmployee);
		respo.then()
		 .assertThat().statusCode(201)
	     .spec(BaseAPI.specRespObj)
		 .log().all();
		return respo;
	}



}
