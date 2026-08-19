package restUtility;

import static io.restassured.RestAssured.*;

import com.ninja.hrm.API.Base.BaseAPI;

import endPoints.EndPointsForEmployee;

public class DeleteEmployeetUtil {
	
	public void deleteEmployee(String empId) {	
	given()
    	.spec(BaseAPI.specReqObj)
		.pathParam("empId", empId)
		.log().all()
	.when()
		.delete(EndPointsForEmployee.deleteEmployee)
	.then()
	    .assertThat().statusCode(204)
	    .spec(BaseAPI.specRespObj)
	    .log().all();	
	}
}
