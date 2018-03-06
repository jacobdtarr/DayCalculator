import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

/**
* Provides a tool to verify dates for their format and parameters
*/
public class DateTools
{
	/**
	* Verifies the date entered is in a valid format and meets requirments for assignment
	* @param date date to verify
	* @param today todays date
	*/
	public static void verifyDate(String date, LocalDate today) throws DateTimeParseException, NegativeDayException
	{
		// Great method to check if the date is entered correctly and is a valid date!
		int numFormatsFailed = 0;
			
		LocalDate result = LocalDate.now();
		
		// Check for proper format
		for (DateTimeFormatter format : DATE_FORMAT)
		{
			try {
				result = LocalDate.parse(date, format);
				
				correctFormatter = format;
			}
			catch (DateTimeParseException e) {
				numFormatsFailed++;
			}
		}
		
		// Check if all the formats failed
		if (numFormatsFailed == 4) {
			throw new DateTimeParseException("Please enter a properly formatted date between the years 1000 and 3000", date, 0);
		}
		
		// Check the date is before today
		if (ChronoUnit.DAYS.between(result, today) < 0) {
			throw new NegativeDayException("Please enter a date before or equal today");
		}
		
			
	}
	
	public static final DateTimeFormatter[] DATE_FORMAT = 
	{
		DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT),
		DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT),
		DateTimeFormatter.ofPattern("dd/M/uuuu").withResolverStyle(ResolverStyle.STRICT),
		DateTimeFormatter.ofPattern("d/MM/uuuu").withResolverStyle(ResolverStyle.STRICT)
	};
	
	public static DateTimeFormatter correctFormatter;
	
}