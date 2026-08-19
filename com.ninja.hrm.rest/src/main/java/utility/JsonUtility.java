package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJson(String key) throws ParseException, FileNotFoundException, IOException
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./src/test/resources/TestData/project.json"));
		JSONObject jobj = (JSONObject)obj;
		return jobj.get(key).toString();
	}

}
