package testUtility;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import utility.FileUtility;
import utility.JavaUtility;
import utility.JsonUtility;

public class VerifyutilityMethods {
	
	@Test
	public void test() throws FileNotFoundException, IOException, ParseException
	{
		FileUtility flib = new FileUtility();
			System.out.println(flib.getDataFromPropertiesFile("baseUri"));
			JavaUtility jlib  = new JavaUtility();
			JsonUtility jsonlib = new JsonUtility();
			System.out.println(jlib.generaterandromNumber(5000));
			System.out.println(jlib.getDate());
			System.out.println(jlib.getLocalTime());
			System.out.println(jlib.getDateAndTime());
			System.out.println(jsonlib.getDataFromJson("projectName"));
			
			String projectName = jsonlib.getDataFromJson("projectName");
			String createdBy = jsonlib.getDataFromJson("createdBy");
			int teamSize =Integer.parseInt(jsonlib.getDataFromJson("teamSize"));
			String status = jsonlib.getDataFromJson("status");
			System.out.println(projectName+createdBy+teamSize+status);
	}

}
