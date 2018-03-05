import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class DateTools
{
	
	public static void verifyDate(String date, LocalDate today) throws DateTimeParseException, NegativeDayException
	{
		// Great method to check if the date is entered correctly and is a valid date!
		int numFormatsFailed = 0;
			
		LocalDate result = LocalDate.now();
			
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
		
		if (ChronoUnit.DAYS.between(result, today) < 0) {
			throw new NegativeDayException("Please enter a date before or equal today");
		}
		
		if (numFormatsFailed == 4) {
			throw new DateTimeParseException("Please enter a properly formatted date between the years 1000 and 3000", date, 0);
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