/**
* Exception for days after current day
*/
public class NegativeDayException extends IllegalArgumentException
{
		public NegativeDayException() {}
		
		public NegativeDayException(String str) {
			super(str);
		}
}