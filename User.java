package Entity;

import java.util.ArrayList;

/**
 * Represents a non-staff user who wishes to use the non-staff functions of
 * MoblimaApp.
 * 
 * @author Goh Shing Ling
 * @author Marcus Cheong Wai Loong
 * @author Ng Ziqi Natasha
 * @author Ravishankar Amrita
 * @author Wong Zhen Yan
 * @since 2019-11-14
 *
 */
public class User {
	/**
	 * The user ID.
	 */
	private String userID;
	/**
	 * The user password.
	 */
	private String userPassword;
	/**
	 * The user email.
	 */
	private String email;
	/**
	 * The user phone number.
	 */
	private String number;
	/**
	 * The user name.
	 */
	private String name;
	/**
	 * Separator that separates the different fields in the text file
	 */
	public static final String SEPARATOR = "|";
	/**
	 * An ArrayList containing the history of transaction.
	 */
	private ArrayList<Receipt> history;

	/**
	 * Create a new user given a userID and Password.
	 * 
	 * @param userID       This is the user ID.
	 * @param userPassword This is the user Password.
	 */
	public User(String userID, String userPassword) {
		this.userID = userID;
		this.userPassword = userPassword;
		email = "nil";
		number = "nil";
		name = "nil";
		history = new ArrayList<Receipt>();
	}

	/**
	 * Update the ArrayList of past history.
	 * 
	 * @param name          The name of movie
	 * @param PGRating      The movie PG rating.
	 * @param typeofMovie   The type of movie.
	 * @param typeofCinema  The type of cinema.
	 * @param timing        The movie showtime.
	 * @param movieDay      The day of the purchased movie.
	 * @param hall          The hall number.
	 * @param allSeats      The seat numbers booked.
	 * @param transactionID The transactionID of the transaction
	 * @param cost          The total cost.
	 */
	public void updateHistory(String name, String PGRating, String typeofMovie, String typeofCinema, String timing,
			String movieDay, String hall, String allSeats, String transactionID, double cost) {
		history.add(new Receipt(name, PGRating, typeofMovie, typeofCinema, timing, movieDay, hall, allSeats,
				transactionID, cost));
	}

	/**
	 * Get user ID.
	 * 
	 * @return user ID.
	 */
	public String getuserID() {
		return userID;
	}

	/**
	 * Get user password.
	 * 
	 * @return user password.
	 */
	public String getuserPassword() {
		return userPassword;
	}

	/**
	 * Get user email.
	 * 
	 * @return the user email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Get user phone number.
	 * 
	 * @return phone number.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Get user name.
	 * 
	 * @return user name.
	 */
	public String getuserName() {
		return name;
	}

	/**
	 * Get the array list of past transaction
	 * 
	 * @return the ArrayList of past transaction.
	 */
	public ArrayList<Receipt> getHistory() {
		return history;
	}

	/**
	 * Changes the email of this user
	 * 
	 * @param email This is the new user email.
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Changes the user name.
	 * 
	 * @param name This is the new user name.
	 */
	public void setuserName(String name) {
		this.name = name;
	}

	/**
	 * Changes the user number.
	 * 
	 * @param number This is the user number.
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Prints the list of past transaction.
	 */
	public void printHistory() {
		System.out.println("Past transactions:");
		ArrayList<Receipt> history = getHistory();
		int length = history.size();
		if (length == 0) {
			System.out.println("No history.");
		}
		for (int i = 0; i < length; i++) {
			System.out.print(i + 1 + ". ");
			Receipt Receipt = (Receipt) history.get(i);
			Entity.Receipt.printReceipt(Receipt);
		}
	}
}