package utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int generaterandromNumber(int range)
	{
		Random random = new Random();
		return random.nextInt(range);
	}
	
	public String getDate() {
		Date date = new Date();
		SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
		return dateformate.format(date);
	}
	
	public String getLocalTime()
	{
		return LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss"));
	}
	public String getDateAndTime() {
	    return LocalDateTime.now()
	            .format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss"));
	}
	

}
