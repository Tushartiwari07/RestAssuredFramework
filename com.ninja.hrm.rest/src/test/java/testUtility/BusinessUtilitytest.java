package testUtility;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.ninja.hrm.API.Base.BaseAPI;

import restUtility.CreateProjectUtil;
import utility.FileUtility;

public class BusinessUtilitytest extends BaseAPI {
	@Test
	public void createProjectTest() throws FileNotFoundException, ParseException, IOException
	{
		
//		String projectName = jsonlib.getDataFromJson("projectName");
//		String createdBy = jsonlib.getDataFromJson("createdBy");
//		int teamSize =Integer.parseInt(jsonlib.getDataFromJson("teamSize"));
//		String status = jsonlib.getDataFromJson("status");
//		CreateProjectUtil createPJT = new CreateProjectUtil();
//		createPJT.createProject(projectName, createdBy, teamSize, status);
		FileUtility flib = new FileUtility();

		String designation=flib.getDataFromPropertiesFile("designation");
		String dob=flib.getDataFromPropertiesFile("dob");
		String email=flib.getDataFromPropertiesFile("email");
		String empName=flib.getDataFromPropertiesFile("empName");
		double experience= Double.parseDouble(flib.getDataFromPropertiesFile("experience"));
		String mobileNo=flib.getDataFromPropertiesFile("mobileNo");
		String role=flib.getDataFromPropertiesFile("role");
		String username=flib.getDataFromPropertiesFile("username");
		
		System.out.println(designation);
		System.out.println(dob);
		System.out.println(email);
		System.out.println(empName);
		System.out.println(experience);
		System.out.println(mobileNo);
		System.out.println(role);
		System.out.println(username);


	}

}
