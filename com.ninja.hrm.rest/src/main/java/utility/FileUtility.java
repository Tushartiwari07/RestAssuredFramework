package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropertiesFile(String key) throws FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("./src/test/resources/TestData/Commondata.properties"));
		return prop.getProperty(key);	
	}

}
