package Control;

import java.io.IOException;
import java.util.ArrayList;
import Boundary.MoblimaApp;
import Entity.Holiday;

/**
 * To manage the holiday details.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 */
public class HolidayManager {
	/**
	 * Prints out the list of holidays.
	 * 
	 * @throws IOException If an input or output exception occurred
	 */
	public void readHoliday() throws IOException {
		ArrayList<Holiday> al = Holiday.readHoliday(MoblimaApp.holidayFile);
		for (int i = 0; i < al.size(); i++) {
			Holiday holiday = (Holiday) al.get(i);
			System.out.println("Holiday Name: " + holiday.getHolidayName());
			System.out.println("Day/Month: " + holiday.getDay() + '/' + holiday.getMonth());
			// System.out.println("===================================");
		}
	}

	/**
	 * Add a holiday to the system.
	 * 
	 * @param name  Name of the holiday to be added
	 * @param day   Day of the holiday to be added
	 * @param month Month of the holiday to be added
	 * @throws IOException If an input or output exception occurred
	 */
	public void addHoliday(String name, int day, int month) throws IOException {
		ArrayList<Holiday> al = Holiday.readHoliday(MoblimaApp.holidayFile);
		Holiday h1 = new Holiday(name, day, month);
		// al is an array list containing holiday objs
		al.add(h1);
		if (day == 1) {
			if(month == 1) {
				String name2 = name + " Eve";
				Holiday h2 = new Holiday(name2, 31, 12);
				al.add(h2);
			}
			else {
			String name2 = name + " Eve";
			Holiday h2 = new Holiday(name2, 31, month - 1);
			al.add(h2);
			}
		} else {
			String name2 = name + " Eve";
			Holiday h2 = new Holiday(name2, day - 1, month);
			al.add(h2);
		}
		// write holiday record/s to file.
		Holiday.saveHoliday(MoblimaApp.holidayFile, al);
	}

	/**
	 * Remove a holiday from the system.
	 * 
	 * @param name Name of holiday to be removed
	 * @return Returns the status of holiday removal. If the removal of holiday is
	 *         successful, true will be returned. If the removal of holiday
	 *         unsuccessful, false will be returned.
	 * @throws IOException If an input or output exception occurred
	 */
	public boolean deleteHoliday(String name) throws IOException {
		ArrayList<Holiday> al = Holiday.readHoliday(MoblimaApp.holidayFile);
		for (int i = 0; i < al.size(); i++) {
			Holiday holiday = (Holiday) al.get(i);
			if (name.compareTo(holiday.getHolidayName()) == 0) {
				String line = holiday.getHolidayName() + "|" + holiday.getDay() + "|" + holiday.getMonth();
				Holiday holiday2 = (Holiday) al.get(i + 1);
				String line2 = holiday2.getHolidayName() + "|" + holiday2.getDay() + "|" + holiday2.getMonth();
				holiday.removeLine(MoblimaApp.holidayFile, line);
				holiday2.removeLine(MoblimaApp.holidayFile, line2);
				return true;
			}
		}
		return false;
	}

}
