package it.mondogrua.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
	
	private final java.util.Date date;

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public Date(java.util.Date aDate) {
		this.date = aDate;
	}

	public Date() {
		this(new java.util.Date());
	}

	@Override
	public String toString() {
		return format.format(date.getTime());
	}

	public Date addDays(int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Date(cal.getTime());
    }
	
}
