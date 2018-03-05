import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class DateTools
{
	
	public static boolean verifyDate(String date, LocalDate today) {
		try {
			// Great method to check if the date is entered correctly and is a valid date!
			
			LocalDate result = LocalDate.parse(date, DATE_FORMAT);
			
			if (result.getYear() > 3000 || result.getYear() < 1000)
				throw new BadYearException();
			
			if (ChronoUnit.DAYS.between(result, today) < 0)
				throw new NegativeDayException();
			
		}
		catch (DateTimeParseException dtpe) {
			//JOptionPane.showMessageDialog(null, "Please enter the date in the format dd/mm/yyyy");
			//return false;
		}
		catch (BadYearException bye) {
			JOptionPane.showMessageDialog(null, "Please enter a date between the year 1000 and 3000");
			return false;
		}
		catch (NegativeDayException nde) {
			JOptionPane.showMessageDialog(null, "Please enter a date before or on current day");
			return false;
		}
		
		return true;
	}
	
	public static class BadYearException extends IllegalArgumentException
	{
		public BadYearException() {}
	}
	
	public static class NegativeDayException extends IllegalArgumentException
	{
		public NegativeDayException() {}
	}
	
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
}