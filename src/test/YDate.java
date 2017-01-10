package test;

import java.util.Calendar;
import java.util.Date;

public class YDate {
	

	
	public static String getAge( Date birthday ) {
		Calendar c1 = Calendar.getInstance();  
		long nowmillSeconds = c1.getTimeInMillis();  
		Calendar c2 = Calendar.getInstance();  
		c2.setTime(birthday);  
		long birmillSeconds = c2.getTimeInMillis();  
		Calendar c3 = Calendar.getInstance();  
		long millis = nowmillSeconds - birmillSeconds;  
		c3.setTimeInMillis(millis);  
		int year = c3.get(Calendar.YEAR);  
		int month = c3.get(Calendar.MONTH);  
		int day = c3.get(Calendar.DAY_OF_MONTH);  
		//int hour = c3.get(Calendar.HOUR_OF_DAY);  
		if (year > 1970) {  
		    return year - 1970 + "岁";  
		} else if (month > Calendar.JANUARY) {  
		    return month - Calendar.JANUARY + "月";  
		} else if (day > 1) {  
		    return day - 1 + "天";  
		}else{  
		    return "1天";  
		} 
    }

}
