package com.shutterfly.marketing;

import java.util.Calendar;
import java.util.Date;
import java.util.NavigableMap;
import java.util.TreeMap;

/*
 * TimeFrame implements generateWeekRanges method to generate ranges of dates
 * with a key. This will be used to groups Orders & Site_Visits of a customer
 */
public class TimeFrame {
	NavigableMap<Date, Integer> generateWeeks(Date startDate, Date endDate) {
		if (startDate.after(endDate))
			return null;
		NavigableMap<Date, Integer> weekRanges = new TreeMap<Date, Integer>();
		int week_no = 1;
		weekRanges.put(startDate, week_no);
		Calendar timeFrameCal = Calendar.getInstance();
		timeFrameCal.setFirstDayOfWeek(Calendar.SUNDAY);
		timeFrameCal.setTime(startDate);
		timeFrameCal.set(Calendar.HOUR_OF_DAY, 0);
		timeFrameCal.set(Calendar.MINUTE, 0);
		timeFrameCal.set(Calendar.SECOND, 0);
		timeFrameCal.set(Calendar.MILLISECOND, 0);

		Calendar first = (Calendar) timeFrameCal.clone();
		first.add(Calendar.DAY_OF_WEEK, first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
		Calendar last = (Calendar) first.clone();
		Date last_date = last.getTime();
		while (last_date.before(endDate)) {
			week_no++;
			last.add(Calendar.DAY_OF_YEAR, 7);
			last_date = last.getTime();
			weekRanges.put(last_date, week_no);
		}
		if (last_date.before(endDate))
			weekRanges.put(endDate, week_no);

		return weekRanges;
	}
	
/*
	public static void main(String args[]) {
		TimeFrame timeframe = new TimeFrame();
		Calendar first = Calendar.getInstance();
		Calendar last = (Calendar) first.clone();

		Calendar test = (Calendar) first.clone();
		last.add(Calendar.DAY_OF_YEAR, 366);
		System.out.println(last.getTime());
		NavigableMap<Date, Integer> ranges = timeframe.generateWeeks(first.getTime(), last.getTime());
		test.add(Calendar.DAY_OF_YEAR, 365);
		System.out.println("########################");
		System.out.println(test.getTime());
		System.out.println(ranges.floorKey(test.getTime()));
	}*/
}
