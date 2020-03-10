package Entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Represents the timings of the movies. The same movie can have multiple
 * timings but should not have the same timing.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 *
 */
public class Timings {
	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";
	/**
	 * The date of the movie show time.
	 */
	private int date;
	/**
	 * The month of the movie show time.
	 */
	private int month;
	/**
	 * The day of the week which the movie is showing.
	 */
	private String day;
	/**
	 * The type of the day (i.e. if it is a weekday or a weekend).
	 */
	private String typeofDay;
	/**
	 * Name of movie
	 */
	private String name;
	/**
	 * If the movie is 2D or 3D.
	 */
	private String typeofMovie;
	/**
	 * Show time of a movie
	 */
	private String timing;
	/**
	 * The hall number that is showing that particular movie
	 */
	private int hallNum;
	/**
	 * The type of cinema, if it is Cathay, Platinum or normal
	 */
	private String typeofCinema;
	/**
	 * The status of the moive (showing, not showing, coming soon, preview)
	 */
	private String status;
	/**
	 * The PG rating of the movie
	 */
	private String PGRating;
	/**
	 * The name of the cineplex
	 */
	private String cineplexName;

	/**
	 * Create a new Timing class with the given date, month, day, type of day, movie
	 * show time, hall number, type of cinema, movie status, PG rating and cineplex
	 * name.
	 * 
	 * @param date         This is the date of the movie show time.
	 * @param month        This is the month of the movie show time.
	 * @param day          This is the day of the week which the movie is showing.
	 * @param typeofDay    This is the type of day that the movie is showing.
	 * @param name         This is the name of the movie.
	 * @param typeofMovie  This is the type of movie.
	 * @param timing       This is the movie show time.
	 * @param hallNum      This is the movie hall number.
	 * @param typeofCinema This is the type of cinema.
	 * @param status       This is the showing status of the cinema.
	 * @param PGRating     This is the PG rating of the movie.
	 * @param cineplexName This is the name of the cineplex
	 */
	public Timings(int date, int month, String day, String typeofDay, String name, String typeofMovie, String timing,
			int hallNum, String typeofCinema, String status, String PGRating, String cineplexName) {
		this.date = date;
		this.month = month;
		this.day = day;
		this.typeofDay = typeofDay;
		this.name = name;
		this.typeofMovie = typeofMovie;
		this.timing = timing;
		this.hallNum = hallNum;
		this.typeofCinema = typeofCinema;
		this.status = status;
		this.PGRating = PGRating;
		this.cineplexName = cineplexName;
	}

	/**
	 * Gets the date of a particular movie.
	 * 
	 * @return the date.
	 */
	public int getDate() {
		return date;
	}

	/**
	 * Gets the month of a particular movie.
	 * 
	 * @return the month.
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Get the day of the week the movie is showing.
	 * 
	 * @return the day.
	 */
	public String getDay() {
		return day;
	}

	/**
	 * Get the type of day the movie is showing.
	 * 
	 * @return the type of day.
	 */
	public String gettypeofDay() {
		return typeofDay;
	}

	/**
	 * Get the name of the movie.
	 * 
	 * @return the name of movie.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the type of movie (2D or 3D).
	 * 
	 * @return the type of movie.
	 */
	public String gettypeofMovie() {
		return typeofMovie;
	}

	/**
	 * Get the show time of the movie.
	 * 
	 * @return the show time.
	 */
	public String getTiming() {
		return timing;
	}

	/**
	 * Get the hall number of the cinema showing the movie.
	 * 
	 * @return the hall number.
	 */
	public int gethallNum() {
		return hallNum;
	}

	/**
	 * Get the type of cinema (Cathay or Platinum).
	 * 
	 * @return the type of cinema.
	 */
	public String gettypeofCinema() {
		return typeofCinema;
	}

	/**
	 * Get showing status of the movie.
	 * 
	 * @return the showing status.
	 */
	public String getstatus() {
		return status;
	}

	/**
	 * Get PG rating of the movie.
	 * 
	 * @return the PG rating
	 */
	public String getPGRating() {
		return PGRating;
	}

	/**
	 * Get the name of the cineplex
	 * 
	 * @return the cineplex name
	 */
	public String getcineplexName() {
		return cineplexName;

	}

	/**
	 * Read an array list containing the various elements in the text file
	 * containing the timings such as date, month, day of showtime, type of day,
	 * name, type and show time of movies, the hall number, showing status, PG
	 * ratings and showtimes of a movie. Add these elements into an ArrayList
	 * 
	 * @param filename this is the file containing the timings of movies.
	 * @return an ArrayList containing the variables in the text file.
	 * @throws IOException if the input or output mismatch exception occurs
	 */
	public static ArrayList<Timings> readTimings(String filename) throws IOException {

		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>) read(filename);
		ArrayList<Timings> alr = new ArrayList<Timings>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			int date = Integer.parseInt(star.nextToken().trim());
			int month = Integer.parseInt(star.nextToken().trim());
			String day = star.nextToken().trim();
			String typeofDay = star.nextToken().trim();
			String name = star.nextToken().trim();
			String typeofMovie = star.nextToken().trim();
			String timing = star.nextToken().trim();
			int hallNum = Integer.parseInt(star.nextToken().trim());
			String typeofCinema = star.nextToken().trim();
			String status = star.nextToken().trim();
			String PGRating = star.nextToken().trim();
			String cineplexName = star.nextToken().trim();
			Timings timings = new Timings(date, month, day, typeofDay, name, typeofMovie, timing, hallNum, typeofCinema,
					status, PGRating, cineplexName);
			alr.add(timings);
		}
		return alr;
	}

	/**
	 * Read a text file and add each line into an ArrayList.
	 * 
	 * @param fileName This is the text file containing the timings.
	 * @return an ArrayList containing contents of the text file.
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

}
