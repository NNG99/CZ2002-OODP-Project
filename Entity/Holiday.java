package Entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Represents a public holiday date. There can be multiple public holidays.
 * Movies showing on a public holiday or public holiday eve will be charged with
 * a different price
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 *
 */
public class Holiday {
	/**
	 * The name of the holiday
	 */
	private String holidayname;
	/**
	 * The date of the holiday
	 */
	private int day;
	/**
	 * The month of the holiday
	 */
	private int month;

	/**
	 * Creates a new holiday given the name, date and month
	 * 
	 * @param holidayname this is the name of the holiday.
	 * @param day         this is the date of the holiday.
	 * @param month       this is the month of the holiday
	 */
	public Holiday(String holidayname, int day, int month) {
		this.holidayname = holidayname;
		this.day = day;
		this.month = month;
	}

	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Read the holidayFile which is a text file that contains the various public
	 * holidays. Separate the various elements in the file line by line, such as
	 * day, month and holiday name and add each line into an arraylist
	 * 
	 * @param filename this is the directory of the file
	 * @return an arraylist containing all the elements in the text file
	 * @throws IOException if the input or output mismatch exception occurs
	 */
	public static ArrayList<Holiday> readHoliday(String filename) throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>) read(filename);
		ArrayList<Holiday> alr = new ArrayList<Holiday>();// to store holiday data
		for (int i = 0; i < stringArray.size(); i++) {
			String st = stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String holidayname = star.nextToken().trim(); // holidayname
			int day = Integer.parseInt(star.nextToken().trim()); // day
			int month = Integer.parseInt(star.nextToken().trim()); // month
			Holiday holiday = new Holiday(holidayname, day, month);
			// add to holiday list
			alr.add(holiday);
		}
		return alr;
	}

	/**
	 * Write a string into the text file
	 * 
	 * @param fileName the text file that is going to be written to.
	 * @param data     the string that is being written into the text file
	 * @throws IOException if the input or output mismatch exception occurs
	 */
	public static void write(String fileName, List<String> data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		try {
			for (int i = 0; i < data.size(); i++) {
				out.println(data.get(i));
			}
		} finally {
			out.close();
		}
	}

	/**
	 * Read a text file line by line and adds each line into the arraylist
	 * 
	 * @param fileName the file that is going to be read
	 * @return return an array list containing each line of the file
	 * @throws IOException if the input or output mismatch exception occurs
	 */
	public static List<String> read(String fileName) throws IOException {
		List<String> data = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return data;
	}

	/**
	 * Save a newly added holiday into a text file.
	 * 
	 * @param filename the text file that is being written to.
	 * @param al       an arraylist containing the new holiday to be added into the
	 *                 text file.
	 * @throws IOException if the input or output mismatch exception occurs
	 */
	public static void saveHoliday(String filename, List<?> al) throws IOException {
		List<String> alw = new ArrayList<String>();// to store data

		for (int i = 0; i < al.size(); i++) {
			Holiday holiday = (Holiday) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(holiday.getHolidayName().trim());
			st.append(SEPARATOR);
			st.append(holiday.getDay());
			st.append(SEPARATOR);
			st.append(holiday.getMonth());
			alw.add(st.toString());
		}
		write(filename, alw);
	}

	/**
	 * Get the name of the holiday
	 * 
	 * @return the name of the holiday
	 */
	public String getHolidayName() {
		return holidayname;
	}

	/**
	 * get the date of the holiday
	 * 
	 * @return the date of the holiday
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Get the month of the holiday
	 * 
	 * @return the month of the holiday
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Remove a line in the text file.
	 * 
	 * @param file         the file that a line is going to be removed.
	 * @param lineToRemove the line to remove.
	 */
	public void removeLine(String file, String lineToRemove) {
		// TODO Auto-generated method stub

		try {

			File inFile = new File(file);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.trim().equals(lineToRemove)) {

					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
