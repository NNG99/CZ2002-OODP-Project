package Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;

/**
 * Represents a cinema. A cinema may show multiple movie but not at the same
 * time. Each cinema have its own class (whether it is Platinum or Cathay), Hall
 * number, cinema code (PLA stands for platinum, CAT stands for Cathay) and
 * cineplex name (Jurong Point, Bishan, Vivo City).
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 */

public class Cinema {
	/**
	 * The class of the cinema (Platinum or Cathay)
	 */
	private String classofCinema;
	/**
	 * The code of the particular cinema
	 */
	private String cinemaCode;
	/**
	 * The hall number of the particular cinema
	 */
	private String hallName;
	/**
	 * The name of the cineplex
	 */
	private String cineplexName;
	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Creates a new cinema with the given class of cinema, cinema code, hall number
	 * and cineplex name
	 * 
	 * @param classofCinema this is the cinema class
	 * @param cinemaCode    this is the cinema class code
	 * @param hallName      this is the cinema hall number
	 * @param cineplexName  this is the cineplex name
	 */
	public Cinema(String classofCinema, String cinemaCode, String hallName, String cineplexName) {
		this.classofCinema = classofCinema;
		this.cinemaCode = cinemaCode;
		this.hallName = hallName;
		this.cineplexName = cineplexName;

	}

	/**
	 * Get the type of cinema (Platinum or Cathay)
	 * 
	 * @return the type of cinema
	 */
	public String gettypeofCinema() {
		return classofCinema;
	}

	/**
	 * Get the cinema code (CAT for cathay, PLA for platinum)
	 * 
	 * @return the cinema code
	 */
	public String getcinemaCode() {
		return cinemaCode;
	}

	/**
	 * Get the hall number of the cinema
	 * 
	 * @return the hall number
	 */
	public String gethallName() {
		return hallName;
	}

	/**
	 * Get the cineplex name
	 * 
	 * @return cineplex name
	 */
	public String getcineplexName() {
		return cineplexName;
	}

	/**
	 * Read the cinema file which contains the details of the cinema and add it into
	 * an arraylist
	 * 
	 * @param filename this is the directory of the text file containing the details
	 *                 of the cinema
	 * @return an arraylist containing the details of the cinema
	 * @throws IOException if the input or output mismatch exception occurs
	 */

	public static ArrayList<String> readCinema(String filename) throws IOException {

		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>) read(filename);
		ArrayList<String> cinema = new ArrayList<String>();// to store cinema data
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			cinema.add(st);
		}
		return cinema;
	}

	/**
	 * Read a textfile and add each line into an arraylist
	 * 
	 * @param fileName this is the directory of the text file that we wish to read
	 * @return return an arraylist containing the each line in the text file
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
	 * Shows the seating arrangement of the particular cinema
	 * 
	 * @param filename this is the text file containing the seating arrangement of
	 *                 each cinema.
	 */
	public static void showSeats(String filename) {
		try {
			ArrayList<?> cinema = readCinema(filename);
			for (int i = 0; i < cinema.size(); i++) {
				System.out.println(cinema.get(i));
			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
}
