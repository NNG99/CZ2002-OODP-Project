package Entity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Boundary.TicketInterface;
import Control.TicketMgr;

/**
 * Represents a ticket for an Senior Citizen. A transaction can have multiple
 * senior tickets. SeniorTicket implements the ticketInterface. The price of the
 * senior ticket is based on the day of the week, cinema class and whether it is
 * 2D or 3D.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 * @author Marcus
 *
 */
public class SeniorTicket implements TicketInterface {
	/**
	 * The name of the movie the user wishes to purchase.
	 */
	private String name;
	/**
	 * The PG rating of the movie that the user wishes to purchase.
	 */
	private String PGRating;
	/**
	 * The type of movie the user wishes to purchase.
	 */
	private static String typeofMovie;
	/**
	 * The class of cinema the user wishes to purchase.
	 */
	private static String classofCinema;
	/**
	 * The timing of the movie.
	 */
	private String timing;
	/**
	 * The day of the week which the user bought the ticket.
	 */
	private String movieDay;
	/**
	 * The hall number that the user wishes to purchase.
	 */
	private String hall;
	/**
	 * The seat numbers that the user wishes to purchase.
	 */
	private String seat;
	/**
	 * The transaction ID of the purchase.
	 */
	private String transactionID;
	/**
	 * The price of the the senior movie ticket.
	 */
	private double price;

	/**
	 * Get the name of the movie that the senior is buying.
	 * 
	 * @return the name of the movie that the user is buying.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the rating of the movie that the user is buying
	 * 
	 * @return the rating of the movie that the user is buying.
	 */
	public String getPGRating() {
		return PGRating;
	}

	/**
	 * Get the type of movie that the user is currently buying. Type of movie refers
	 * to if the movie is 2D or 3D
	 * 
	 * @return the type of movie, if it is 3D or 2D.
	 */
	public static String gettypeofMovie() {
		return typeofMovie;
	}

	/**
	 * Get the class of cinema that the user wishes to book. Class of cinema refers
	 * to if it is platinum class or cathay class.
	 * 
	 * @return the class of cinema for that particular booking
	 */
	public static String getclassofCinema() {
		return classofCinema;
	}

	/**
	 * Get the timings of the particular movie that the user wishes to buy
	 * 
	 * @return the timing of the particular movie
	 */
	public String gettiming() {
		return timing;
	}

	/**
	 * Get the day of the week that the movie is showing. This will determine if it
	 * is a weekend or weekday which will affect the prices of the ticket
	 * 
	 * @return the day of the movie
	 */
	public String getmovieDay() {
		return movieDay;
	}

	/**
	 * Returns the hall number for that particular movie the user wishes to book.
	 * Will be shown in the receipt.
	 * 
	 * @return the hall number of the particular movie for that particular time
	 */
	public String gethall() {
		return hall;
	}

	/**
	 * Get the seat numbers that the user have booked. For example seat numbers A1,
	 * A2, etc
	 * 
	 * @return the seat numbers that the user have booked
	 */
	public String getSeat() {
		return seat;
	}

	/**
	 * Gets the transaction ID for that particular transaction
	 * 
	 * @return the transaction ID
	 */
	public String gettransactionID() {
		return transactionID;
	}

	/**
	 * Gets the prices of the tickets that the user has bought.
	 * 
	 * @return price of tickets
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Creates a new SeniorTicket with the given movie name, movie rating, type of
	 * movie, movie timings, day of movie, hall number, seat numbers transactionID
	 * and prices.
	 * 
	 * @param name          This is the chosen moive name.
	 * @param PGRating      This is the chosen movie PGRating.
	 * @param typeofMovie   This is the type of the chosen movie.
	 * @param classofCinema This is the class of the cinema for that chosen movie.
	 * @param timing        This is the movie timing that the user chose.
	 * @param movieDay      This is the day of the week that the user chose.
	 * @param hall          This is the hall number where the movie will be shown.
	 * @param seat          This is the seat number(s) that the user chose.
	 * @param transactionID This is the transaction ID for the specific booking
	 * @param price         This is the price of the movie.
	 */
	public SeniorTicket(String name, String PGRating, String typeofMovie, String classofCinema, String timing,
			String movieDay, String hall, String seat, String transactionID, double price) {
		this.name = name;
		this.PGRating = PGRating;
		SeniorTicket.typeofMovie = typeofMovie;
		SeniorTicket.classofCinema = classofCinema;
		this.timing = timing;
		this.movieDay = movieDay;
		this.hall = hall;
		this.seat = seat;
		this.transactionID = transactionID;
		this.price = price;
	}

	/**
	 * Prints the details of the ticket. Which includes the movie name, day of the
	 * week, PG rating, type of movie, timing of movie, class of cinema, hall
	 * number, seat numbers, price of movie and transaction ID
	 * 
	 * @param ticket this is the ticket object that was created when a booking is
	 *               made
	 */
	public static void printTicket(SeniorTicket ticket) {
		System.out.println(
				"Title: " + ticket.getName() + " " + ticket.getPGRating() + "(" + SeniorTicket.gettypeofMovie() + ")");
		System.out.println("Date: " + ticket.getmovieDay());
		System.out.println("Timing: " + ticket.gettiming());
		System.out.println("Cinema: " + SeniorTicket.getclassofCinema());
		System.out.println(ticket.gethall());
		System.out.println("Seat(s): " + ticket.getSeat());
		System.out.println("Price: " + ticket.getPrice());
		System.out.println("Transaction ID: " + ticket.gettransactionID());
		System.out.println();
	}

	/**
	 * Get the price of a senior ticket for a 2D movie on a weekday for Cathay
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getCathay2DWeekdaySenior(ArrayList<TicketMgr> pricesAL) {
		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekdays") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Cathay") && TicketMgr.gettypeofMovie().equals("2D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt;
	}

	/**
	 * Get the price of a senior ticket for a 2D movie on a weekend for Cathay
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getCathay2DWeekendSenior(ArrayList<TicketMgr> pricesAL) {

		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekends") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Cathay") && TicketMgr.gettypeofMovie().equals("2D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt;
	}

	/**
	 * Get the price of a senior ticket for a 3D movie on a weekday for Cathay
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getCathay3DWeekdaySenior(ArrayList<TicketMgr> pricesAL) {

		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekdays") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Cathay") && TicketMgr.gettypeofMovie().equals("3D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt;
	}

	/**
	 * Get the price of a senior ticket for a 3D movie on a weekend for Cathay
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getCathay3DWeekendSenior(ArrayList<TicketMgr> pricesAL) {

		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekends") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Cathay") && TicketMgr.gettypeofMovie().equals("3D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt;
	}

	/**
	 * Get the price of a senior ticket for a 2D movie on a weekday for Platinum
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getPlat2DWeekdaySenior(ArrayList<TicketMgr> pricesAL) {

		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekdays") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Platinum") && TicketMgr.gettypeofMovie().equals("2D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt ;
	}

	/**
	 * Get the price of a senior ticket for a 2D movie on a weekend for Platinum
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getPlat2DWeekendSenior(ArrayList<TicketMgr> pricesAL) {

		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekends") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Platinum") && TicketMgr.gettypeofMovie().equals("2D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt; 
	}

	/**
	 * Get the price of a senior ticket for a 3D movie on a weekday for Platinum
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getPlat3DWeekdaySenior(ArrayList<TicketMgr> pricesAL) {

		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekdays") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Platinum") && TicketMgr.gettypeofMovie().equals("3D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt;
	}

	/**
	 * Get the price of a senior ticket for a 3D movie on a weekend for Platinum
	 * class
	 * 
	 * @param pricesAL This is an array list containing the various combination of
	 *                 prices.
	 * @return returns the price of the senior ticket
	 */
	public static String getPlat3DWeekendSenior(ArrayList<TicketMgr> pricesAL) {
		double amount ;
		String strAmt = null ; 
		for (int k = 0; k < pricesAL.size(); k++) {
			TicketMgr TicketMgr = (TicketMgr) pricesAL.get(k);

			if (TicketMgr.getdayofWeek().equals("Weekends") && TicketMgr.getageofmovieGoer().equals("Senior Citizen")
					&& TicketMgr.getclassofCinema().equals("Platinum") && TicketMgr.gettypeofMovie().equals("3D")) {
				amount = TicketMgr.getprice();
				DecimalFormat con = new DecimalFormat("#.00");
			   strAmt = con.format(amount);
			}
		}
		return strAmt;
	}

}
