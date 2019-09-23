package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date_CurrentDate_FutureDate
{
	/*public static void main(String[] args) throws Exception 
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		 Date date = new Date();
		 String date1= dateFormat.format(date);
		 System.out.println(date1);
	}*/
	public static void main(String[] args) throws Exception 
	{
	Calendar calendar = Calendar.getInstance();
	java.util.Date today = calendar.getTime();
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
	 String date1= dateFormat.format(today);
	 
	System.out.println("CurrentDate:    " + date1);
	
	calendar.add(Calendar.DAY_OF_YEAR, -1);

	// now get Future Date
	 java.util.Date tomorrow = calendar.getTime();
	 DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy ");
	 String date2= dateFormat1.format(tomorrow);
	 System.out.println("FutureDate: " + date2);
	}
}
